package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.ddriver.DDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.S57Catalog;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.S57CatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl.controller.S57CatalogController;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import javafx.scene.Scene;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57CatalogImpl
        implements S57Catalog, S57CatalogServices, DDriver, ComponentState {

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
    protected S57CatalogController S57CatalogController;
    protected List<Layer> layers;
    protected Set<Layer> layersSet;
    protected static final Logger LOGGER = Logger.getLogger(S57CatalogImpl.class.getName());
    protected WorldWindow wwd;
    protected Scene scene;
    private boolean first = true;
    private Map<String, String> environment;
    private boolean enabled;

    @Override
    public void componentInitiated() {
        i = 0;
        layerTreeServices.createGroup(GROUP);
        wwd = GeoWorldWindViewImpl.getWW();
        wwd.addPositionListener((PositionEvent event) -> {
            float altitude = ((int) wwd.getView().getCurrentEyePosition().getAltitude());
            if (altitude >= 3000) {
                clip();
            } else {
                unClip();
            }
        });
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
        System.out.println("fileName " + fileName);
        openFile(fileName);
    }

    private void openFile(String fileName) {

        LOGGER.log(Level.INFO, "Opening {0} ..." + fileName, fileName);

        Path inputFile = Paths.get(fileName);
        Proc p = null;
        String cmd;
        cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";
        try {
            Path tmp = Paths.get(inputFile.toString());
            p = Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ").addArg("-f \"ESRI Shapefile\" ").addArg("-append")
                    .addArg("data/shp/catalog/shp_" + i)// + "/out.shp ")
                    .addArg(tmp.toString())
                    .addArg("M_COVR")
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);
            //   inputFile = tmp;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        try {
            if (p != null) {
                p.waitFor();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(S57CatalogImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        S57CatalogController = S57CatalogController.getInstance();
        S57CatalogController.init("data/shp/catalog/shp_" + i);
        System.out.println("appel openFile");
        layers = S57CatalogController.getLayers();
       // System.out.println("layersSet " + layersSet);
       // layersSet.stream().forEach((l) -> {
         //   layers.add(l);
        //});
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            String name = l.getName();
            if (name.contains("M_COVR")) {
                l.setPickEnabled(true);
            }
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
        i++;
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
            Logger.getLogger(S57CatalogImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void componentStopped() {

    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
