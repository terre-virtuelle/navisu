package bzh.terrevirtuelle.navisu.charts.vector.s57.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.S57Chart;
import bzh.terrevirtuelle.navisu.charts.vector.s57.S57ChartServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.ChartS57Controller;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.layers.AirspaceLayer;
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

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class S57ChartImpl
        implements S57Chart, S57ChartServices, Driver, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayerTreeServices layerTreeServices;

    private static final String NAME = "S57";
    private static final String EXTENSION = ".000";
    protected static final String GROUP = "S57 charts";
    static private int i = 0;
    protected ChartS57Controller chartS57Controller;
    protected List<Layer> layers;
    protected List<AirspaceLayer> airspaceLayers;
    protected static final Logger LOGGER = Logger.getLogger(S57ChartImpl.class.getName());
    protected WorldWindow wwd;

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
            if (altitude >= 48000) {
                clip1();
            } else {
                unClip1();
                unClip();
            }
        });
    }

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION)) {
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

    protected void handleOpenFile(ProgressHandle pHandle, String fileName) {
        new File("data/shp").mkdir();
        new File("data/shp/shp_" + i).mkdir();
        new File("data/shp/shp_" + i + "/soundg").mkdir();

        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);

        Path inputFile = Paths.get(fileName);
        Map<String, String> environment = new HashMap<>(System.getenv());
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
        //  System.out.println("environment " + environment);

        String cmd;
        cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";
        try {
            Path tmp = Paths.get(inputFile.toString());
            Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ").addArg("-overwrite ")
                    .addArg("data/shp/shp_" + i)// + "/out.shp ")
                    .addArg(tmp.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);
            inputFile = tmp;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr -nlt POINT25D";
        try {
            Path tmp = Paths.get(inputFile.toString());
            Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ").addArg("-append ")
                    .addArg("data/shp/shp_" + i + "/soundg/SOUNDG.shp")
                    .addArg(tmp.toString())
                    .addArg("SOUNDG")
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec();
            inputFile = tmp;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        chartS57Controller = ChartS57Controller.getInstance();
        chartS57Controller.init("data/shp/shp_" + i++);
        layers = chartS57Controller.getLayers();
        layers.stream().filter((l) -> (l != null)).map((l) -> {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            String name = l.getName();
            if (name.contains("BCNCAR")
                    /*
                    || name.contains("BCNLAT")
                    || name.contains("BCNISD")
                    || name.contains("OBSTRN")
                    || name.contains("LIGHTS")
                    || name.contains("SOUNDG")
                    || name.contains("NAVLNE")
                    || name.contains("WRECK")
            */
                    ) {
                l.setPickEnabled(true);
            }
            if (name.contains("DEPARE")
                    || name.contains("BCNLAT")
                    || name.contains("BCNISD")
                    || name.contains("OBSTRN")
                    || name.contains("LIGHTS")
                    || name.contains("SOUNDG")
                    || name.contains("NAVLNE")
                    || name.contains("WRECK")
                    
                    || name.contains("DEPCNT")
                    || name.contains("OBSTRN_CNT")
                    || name.contains("WRECKS_CNT")
                    || name.contains("UWTROC")) {
                l.setPickEnabled(false);
            }
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });

        airspaceLayers = chartS57Controller.getAirspaceLayers();
        airspaceLayers.stream().filter((l) -> (l != null)).map((l) -> {
            String name = l.getName();
            if (name.contains("LIGHTS")) {
                l.setPickEnabled(true);
            } else {
                l.setPickEnabled(false);
            }
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });
    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("BCN"))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("BCN"))).forEach((l) -> {
                l.setEnabled(true);
            });
        }
    }

    private void clip1() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("DEPCNT"))).forEach((l) -> {
                l.setEnabled(false);
            });
        }
    }

    private void unClip1() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("DEPCNT"))).forEach((l) -> {
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
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() {
        try {
            Path directory = Paths.get("data/shp");
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (IOException ex) {
            Logger.getLogger(S57ChartImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void componentStopped() {

    }
}
