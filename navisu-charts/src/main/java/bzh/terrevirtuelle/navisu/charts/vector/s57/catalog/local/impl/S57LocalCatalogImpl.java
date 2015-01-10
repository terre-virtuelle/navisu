package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.ddriver.DDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.S57LocalCatalog;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.S57LocalCatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local.impl.controller.S57LocalCatalogController;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57LocalCatalogImpl
        implements S57LocalCatalog, S57LocalCatalogServices, DDriver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "S57 catalog";
    private static final String EXTENSION = ".000";
    protected static final String GROUP = "S57 catalog";
    private int i = 0;
    private int j = 1;
    protected S57LocalCatalogController s57CatalogController;
    protected List<Layer> layers;
    protected Set<Layer> layersSet;
    protected static final Logger LOGGER = Logger.getLogger(S57LocalCatalogImpl.class.getName());
    protected WorldWindow wwd;
    private Map<String, String> environment;
    private boolean enabled;
    private List<String> files;
    String root;

    @Override
    public void componentInitiated() {
        files = new ArrayList<>();
        i = 0;
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        /*
         wwd.addPositionListener((PositionEvent event) -> {
         float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
         if (altitude >= 3000) {
         clip();
         } else {
         unClip();
         }
         });
         */
        environment = new HashMap<>(System.getenv());
        String options
                = "\"RECODE_BY_DSSI=ON, "
                + "ENCODING=UTF8, "
                + "RETURN_PRIMITIVES=ON, "
                + "RETURN_LINKAGES=ON, "
                + "LNAM_REFS=ON, "
                + "SPLIT_MULTIPOINT=ON, "
                + "ADD_SOUNDG_DEPTH=ON\" \n";
        environment.put("OGR_S57_OPTIONS", options);
        options = System.getProperty("user.dir") + "/bin/data";
        environment.put("GDAL_DATA", options);
        layersSet = new HashSet();
        File f = new File("test");
        root = f.getAbsolutePath().replace("test", "");
    }

    @Override
    public boolean canOpen(String file) {
        boolean canOpen = true;
        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            if (file.toLowerCase().endsWith(EXTENSION)) {
                this.handleOpenFile(pHandle, file);
            }
        }
    }

    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        if (!new File("data/shp").isDirectory()) {
            new File("data/shp").mkdir();
            new File("data/shp/catalog").mkdir();
        }
        files.add(fileName);
        openFile(fileName);
    }

    private void openFile(String fileName) {

        LOGGER.log(Level.INFO, "Opening {0} ..." + fileName, fileName);
//Task 
        Path inputFile = Paths.get(fileName);
        Proc p = null;
        String cmd;
        cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";
        try {
            Path tmp = Paths.get(inputFile.toString());
            p = Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ").addArg("-f \"ESRI Shapefile\" ").addArg("-append")
                    .addArg("data/shp/catalog/shp_" + i++)
                    .addArg(tmp.toString())
                    .addArg("M_COVR")
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void parse() {
        s57CatalogController = S57LocalCatalogController.getInstance();
        layers = s57CatalogController.getLayers();
        files.stream().forEach((s) -> {
            layers.addAll(s57CatalogController.parse(root + "data\\shp\\catalog\\shp_" + j++));
        });
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            String name = l.getName();
            if (name.contains("M_COVR")) {
                l.setPickEnabled(false);
            }
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    private void clip() {
        /*
         if (layers != null) {
         layers.stream().filter((l) -> (l.getName().contains("M_COVR"))).forEach((l) -> {
         l.setEnabled(false);
         });
         }
         */
    }

    private void unClip() {
        /*
         if (layers != null) {
         layers.stream().filter((l) -> (l.getName().contains("M_COVR"))).forEach((l) -> {
         l.setEnabled(true);
         });
         }
         */
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION};
    }

    @Override
    public void openChart(String file) {
        this.open(null, file);
    }

    @Override
    public DDriver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {
        try {
            Path directory = Paths.get("data/shp/catalog");
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    // Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(S57LocalCatalogImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void componentStopped() {

    }
}
