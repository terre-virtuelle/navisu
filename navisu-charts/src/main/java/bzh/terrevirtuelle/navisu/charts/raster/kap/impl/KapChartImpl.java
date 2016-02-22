package bzh.terrevirtuelle.navisu.charts.raster.kap.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.core.util.OS;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChart;
import bzh.terrevirtuelle.navisu.charts.raster.kap.KapChartServices;
import bzh.terrevirtuelle.navisu.charts.raster.kap.impl.imageryinstaller.ImageryInstaller;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class KapChartImpl implements KapChart, KapChartServices, Driver, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(KapChartImpl.class.getName());

    protected static final String EXTENSION_0 = ".kap";
    private static final String EXTENSION_1 = ".KAP";
    protected static final String GROUP = "BSB/KAP charts";

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

        if (file.toLowerCase().endsWith(EXTENSION_0)
                || file.toLowerCase().endsWith(EXTENSION_1)) {
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
        String cmd = null;
        Map<String, String> environment = new HashMap<>(System.getenv());
        String options
                = "\"BSB_IGNORE_LINENUMBERS=true\"";
        environment.put("GDAL_DATA", options);

        if (OS.isWindows()) {
            cmd = "gdal/win/gdal_translate";
        } else {
            if (OS.isLinux()) {
                cmd = "/usr/bin/gdal_translate";
            } else {
                if (OS.isMac()) {
                    cmd = "gdal/osx/gdal_translate";
                } else {
                    System.out.println("OS not found");
                }
            }
        }
        try {

            Path tmpTif = Paths.get(inputFile.toString() + ".tif");
            System.out.print(tmpTif);
            Proc.BUILDER.create()
                    .setCmd(cmd)
                    .addArg("-b 1")
                    .addArg(file)
                    .addArg(tmpTif.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);

            inputFile = tmpTif;
            System.out.print(tmpTif);
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }

        ImageryInstaller installer = ImageryInstaller.factory.newImageryInstaller();
        installer.setImageFormat(ImageryInstaller.ImageFormatEnum.PNG);

        Layer layer = installer.installSurfaceImage(inputFile, pHandle);
        if (layer != null) {
            layer.setName("BSB");
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
        return new String[]{"*" + EXTENSION_0,
            "*" + EXTENSION_1
        };
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
