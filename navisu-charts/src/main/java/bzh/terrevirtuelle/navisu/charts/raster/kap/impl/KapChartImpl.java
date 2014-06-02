package bzh.terrevirtuelle.navisu.charts.raster.kap.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChart;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.kap.impl.imageryinstaller.ImageryInstaller;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.Layer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class KapChartImpl implements KapChart, KapChartServices, Driver, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(KapChartImpl.class.getName());

    protected static final String EXTENSION = ".kap";
    protected static final String GROUP = "BSB/KAP Charts";

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    LayerTreeServices layerTreeServices;

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

    protected void handleOpenFile(ProgressHandle pHandle, String file) {
        LOGGER.log(Level.INFO, "Opening {0} ...", file);

        Path inputFile = Paths.get(file);
        final String cmd = "bin/" + (OS.isMac() ? "osx" : "win") + "/gdal_translate";

        try {

            Path tmpTif = Paths.get(inputFile.toString() + ".tif");

            Proc.builder.create()
                    .setCmd(cmd)
                   // .addArg("-ot Int64 ")
                    .addArg("-of GTiff ")
                    .addArg("-expand rgb ")
                    .addArg("-co compress=lzw ")
                    .addArg(file)
                    .addArg(tmpTif.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec();

            inputFile = tmpTif;
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }

        ImageryInstaller installer = ImageryInstaller.factory.newImageryInstaller();
        installer.setImageFormat(ImageryInstaller.ImageFormatEnum.PNG);

        Layer layer = installer.installSurfaceImage(inputFile, pHandle);
        if (layer != null) {
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }

        if (inputFile.toString().endsWith(".tif")) {
            try {
                Files.delete(inputFile);
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, null, e);
            }
        }
    }

    @Override
    public String getName() {
        return "BSB/KAP";
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
