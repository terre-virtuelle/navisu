package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobDisplayController;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.driver.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalog;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.controller.S57GlobalCatalogController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57GlobalCatalogImpl
        implements S57GlobalCatalog, S57GlobalCatalogServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;
    @UsedService
    S57ChartServices s57ChartServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    private static final String NAME = "S57 catalog";
    private static final String EXTENSION_0 = ".kmz";
    private static final String EXTENSION_1 = ".kml";
    private static final String CHART_EXT = ".000";
    private static final String SEP = Pattern.quote(System.getProperty("file.separator"));
    protected static final String GROUP = "S57 catalog";
    protected Map<String, Path> files;
    protected List<Layer> layers;
    protected Layer layer;
    protected List<Layer> enabledLayers;
    protected List<CheckBoxTreeItem<GeoLayer>> rootItems;
    protected static final Logger LOGGER = Logger.getLogger(S57GlobalCatalogImpl.class.getName());
    protected WorldWindow wwd;
    protected float altitude;
    protected Map<String, Pair<Integer, Integer>> clipConditions;
    protected Set<String> clipConditionsKeySet;

    @Override
    public void componentInitiated() {
        enabledLayers = new ArrayList<>();
        clipConditions = new HashMap<>();
        clipConditions.put("ENC_NP1", new Pair(7400000, 25000000));
        clipConditions.put("ENC_NP2", new Pair(550000, 7400000));
        clipConditions.put("ENC_NP3", new Pair(370000, 7400000));
        clipConditions.put("ENC_NP4", new Pair(150000, 7400000));
        clipConditions.put("ENC_NP5", new Pair(100000, 370000));
        clipConditions.put("ENC_NP6", new Pair(100000, 370000));
        clipConditionsKeySet = clipConditions.keySet();
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            //  System.out.println("altitude : " + ((int) wwd.getView().getCurrentEyePosition().getAltitude()));
            filter();
        });
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/user.properties"));
        } catch (IOException ex) {
            Logger.getLogger(DriverManagerImpl.class.getName()).log(Level.SEVERE, "Erreur lecture : properties/user.properties", ex);
        }
        String userS57ChartsDirectory = properties.getProperty("s57ChartsDir");
        if (!"".equals(userS57ChartsDirectory)) {
            String[] userS57ChartsDirectories = userS57ChartsDirectory.split(",");
            int l = userS57ChartsDirectories.length;
            for (int i = 0; i < l; i++) {
                if (userS57ChartsDirectories[i] != null) {
                    try {
                        files = listSourceFiles(Paths.get(userS57ChartsDirectories[i]));
                    } catch (IOException ex) {
                        Logger.getLogger(S57GlobalCatalogImpl.class.getName()).log(Level.INFO, "Loading charts DB", ex);
                    }
                }
            }
        }
    }

    Map<String, Path> listSourceFiles(Path dir) throws IOException {
        Map<String, Path> result = new HashMap<>();
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) {
                DirectoryStream.Filter<Path> filter = (Path entry) -> (entry.toString().contains(CHART_EXT));
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(file, filter)) {
                    for (Path path : stream) {
                        String[] f = path.toString().split(SEP);
                        result.put(f[f.length - 1], path);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(S57GlobalCatalogImpl.class.getName()).log(Level.INFO, "Loading charts DB", ex);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return result;
    }

    @Override
    public boolean canOpen(String category, String file) {
        boolean canOpen = false;
        if (category.contains(NAME)
                && (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1))) {
            canOpen = true;
        }
        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {
        if (files != null) {
            for (String file : files) {
                this.handleOpenFile(pHandle, file);
            }
        }
    }

    public void handleOpenFile(ProgressHandle pHandle, String fileName) {

        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        S57GlobalCatalogController s57GlobalCatalogController = S57GlobalCatalogController.getInstance();
        s57GlobalCatalogController.setS57GlobalCatalogImpl(this);
        layers = s57GlobalCatalogController.init(fileName);

        if (!layers.isEmpty()) {
            layer = layers.get(layers.size() - 1);
            layer.setEnabled(false);
            geoViewServices
                    .getLayerManager()
                    .insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices
                    .addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }
    }

    @Override
    public void loadFile(String filename) {
        File file = new File(filename);
        guiAgentServices.getJobsManager().newJob(filename, (progressHandle) -> {
            s57ChartServices.getDriver().open(progressHandle, file.getAbsolutePath());
        });
    }

    @Override
    public void load(String... filenames) {
        
        guiAgentServices.getJobsManager().newJob("", (progressHandle) -> {
            for (String f : filenames) {
                File file = new File(f);
                this.open(progressHandle, file.getAbsolutePath());
            }
        });
    }

    private void filter() {
        enabledLayers.clear();
        CheckBoxTreeItem<GeoLayer> i = (CheckBoxTreeItem) layerTreeServices.search("S57 catalog");
        List<TreeItem<GeoLayer>> childrens = i.getChildren();

        childrens.stream().forEach((t) -> {
            String value = t.getValue().getName().trim();
            if (clipConditionsKeySet.contains(value)) {
                layer = (Layer) t.getValue().getDisplayLayer();
                if (((CheckBoxTreeItem<GeoLayer>) t).isSelected()) {
                    enabledLayers.add(layer);
                }
            }
        });
        altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
        enabledLayers.stream().forEach((l) -> {
            clip(l, clipConditions.get(l.getName()));
        });
    }

    private void clip(Layer layer, Pair<Integer, Integer> minMax) {
        if (altitude < minMax.getX() || altitude > minMax.getY()) {
            layer.setEnabled(false);
        } else {
            layer.setEnabled(true);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION_0, "*" + EXTENSION_1};
    }

    @Override
    public void openFile(String file) {
        this.open(null, file);
    }

    public Map<String, Path> getFiles() {
        return files;
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }

}
