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
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    protected static final String GROUP = "S57Charts";
    static private int i = 0;
    protected ChartS57Controller chartS57Controller;
    protected List<Layer> layers;
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
                unClip1();unClip();
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
        new File("data/shp_ " + i).mkdir();
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);

        Path inputFile = Paths.get(fileName);
        final String cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";

        try {
            Path tmp = Paths.get(inputFile.toString());

            Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ")
                    .addArg("data/shp_" + i + "/out.shp ")
                    .addArg(tmp.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec();
            inputFile = tmp;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        chartS57Controller = ChartS57Controller.getInstance();
        layers = chartS57Controller.init("data/shp_" + i++);

        layers.stream().filter((l) -> (l != null)).map((l) -> {
            String name = l.getName();
            if (name.contains("BCNCAR")
                    || name.contains("BCNLAT")) {
                l.setPickEnabled(true);
            } else {
                l.setPickEnabled(false);
            }
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(l));
            return l;
        }).forEach((l) -> {
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(l));
        });

        /*
         File index = new File("data/shp");
         for(File file: index.listFiles()) file.delete();
         */
    }

    private void clip() {
        if (layers != null) {
            layers.stream().filter((l) -> (l.getName().contains("BCN"))).forEach((l) -> {
                        System.out.println("name " + l.getName());
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
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }

}
