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
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    protected ChartS57Controller chartS57Controller;
    protected static final Logger LOGGER = Logger.getLogger(S57ChartImpl.class.getName());

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
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
        LOGGER.log(Level.INFO, "Opening {0} ...", fileName);

        Path inputFile = Paths.get(fileName);
        final String cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/ogr2ogr";

        try {
            Path tmp = Paths.get(inputFile.toString());

            Proc.builder.create()
                    .setCmd(cmd)
                    .addArg("-skipfailures ")
                    .addArg("data/shp/out.shp ")
                    .addArg(tmp.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec();
            inputFile = tmp;
        }catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
/*
        ImageryInstaller installer = ImageryInstaller.factory.newImageryInstaller();
        installer.setImageFormat(ImageryInstaller.ImageFormatEnum.PNG);

        Layer layer = installer.installSurfaceImage(inputFile, pHandle);
        if (layer != null) {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }

        if (inputFile.toString().endsWith(EXTENSION)) {
            try {
                Files.delete(inputFile);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, null, e);
            }
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
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ } 

}
