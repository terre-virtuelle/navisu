package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalog;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.S57GlobalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global.impl.controller.S57GlobalCatalogController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.Layer;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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

    private static final String NAME = "S57 catalog";
    private static final String EXTENSION_0 = ".kmz";
    private static final String EXTENSION_1 = ".kml";
    private static final String CHART_EXT = ".000";
    private static final String SEP = Pattern.quote(System.getProperty("file.separator"));
    protected static final String GROUP = "S57 catalog";
    protected Map<String, Path> files;
    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(S57GlobalCatalogImpl.class.getName());
    protected WorldWindow wwd;

    @Override
    public void componentInitiated() {
        // layerTreeServices.createGroup(GROUP);
        // Alternative: use Pattern.quote(File.separator)
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude <= 50000 || altitude >= 400000) {
                clip();
            } else {
                unClip();
            }
            // System.out.println("altitude " + altitude);
        });

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/user.properties"));
        } catch (IOException ex) {
            Logger.getLogger(DriverManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String userS57ChartsDirectory = properties.getProperty("s57ChartsDir");
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
     //   Set<String> keys = files.keySet();
        //   keys.stream().forEach((s) -> {
        //      System.out.println(s + "  " + files.get(s));
        //   });

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
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION_0) || file.toLowerCase().endsWith(EXTENSION_1)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
       
    }

    public void handleOpenFile(ProgressHandle pHandle, String fileName) {

        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);
        S57GlobalCatalogController s57GlobalCatalogController = S57GlobalCatalogController.getInstance();
        s57GlobalCatalogController.setS57GlobalCatalogImpl(this);
        layers = s57GlobalCatalogController.init(fileName);
        if (!layers.isEmpty()) {
            geoViewServices
                    .getLayerManager()
                    .insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layers.get(layers.size() - 1)));
            layerTreeServices
                    .addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layers.get(layers.size() - 1)));
        }
    }

    public void loadFile(String filename) {
        s57ChartServices.openChart(filename);
    }

    private void clip() {
        if (layers != null) {
            layers.stream().forEach((l) -> {
                l.setEnabled(false);
            }); 
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().forEach((l) -> {
                l.setEnabled(true);
            }); 
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
