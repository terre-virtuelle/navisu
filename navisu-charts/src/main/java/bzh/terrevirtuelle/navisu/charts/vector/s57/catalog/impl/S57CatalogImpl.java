package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.ddriver.DDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.S57Catalog;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.S57CatalogServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.impl.controller.S57CatalogController;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.ChartS57Controller;
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
import java.util.List;
import java.util.Map;
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
    static private int i = 0;
    protected S57CatalogController S57CatalogController;
    protected List<Layer> layers;
    protected static final Logger LOGGER = Logger.getLogger(S57CatalogImpl.class.getName());
    protected WorldWindow wwd;
    protected Scene scene;
    private boolean first = true;
    private Map<String, String> environment;

    @Override
    public void componentInitiated() {
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
        new File("data/shp").mkdir();
        new File("data/shp/catalog").mkdir();
        try {
            Path directory = Paths.get(fileName);
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.toFile().getName().toLowerCase().endsWith(EXTENSION)) {
                      //  System.out.println(file.toFile().getName() + " open");
                        openFile(file.toFile().getAbsolutePath());
                    }
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

    private void openFile(String fileName) {
        
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);

        Path inputFile = Paths.get(fileName);

        String cmd;
        cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";
        try {
            Path tmp = Paths.get(inputFile.toString());
            Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ").addArg("-f \"ESRI Shapefile\" ")
                    .addArg("data/shp/catalog/shp_" + i)// + "/out.shp ")
                    .addArg(tmp.toString())
                    .addArg("layer M_COVR")
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);
            inputFile = tmp;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        S57CatalogController = S57CatalogController.getInstance();
        S57CatalogController.init("data/shp/catalog/shp_" + i++);
        layers = S57CatalogController.getLayers();
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

    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("M_COVR"))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("M_COVR"))).forEach((l) -> {
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
}
