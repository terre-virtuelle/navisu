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
import bzh.terrevirtuelle.navisu.database.relational.impl.DatabaseImpl;
import com.sun.jna.Library;
import com.sun.jna.Native;
import gov.nasa.worldwind.layers.Layer;
import java.io.File;
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

    @SuppressWarnings("unchecked")
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
           // System.out.print(tmpTif);
            cmd+=" -b 1 "+file+" "+tmpTif.toString();
            String cmd0=createCmdSh(cmd);
            Proc.BUILDER.create()
                    .setCmd(cmd0)
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec();
            /*
            Proc.BUILDER.create()
                    .setCmd(cmd)
                    .addArg("-b 1")
                    .addArg(file)
                    .addArg(tmpTif.toString())
                    .setOut(System.out)
                    .setErr(System.err)
                    .exec(environment);
*/
            
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

    private String createCmdSh(String cmd) {
        String cmdFile = "cmd/cmd.sh";
        try {
            Files.write(Paths.get(cmdFile), cmd.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(DatabaseImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
        chmodCmd(cmdFile);
        return cmdFile;
    }

    private String chmodCmd(String cmdFile) {
        String cmd = null;
        if (OS.isWindows()) {
            LinkedOSLibrary linkedLibrary
                    = (LinkedOSLibrary) Native.loadLibrary("c", LinkedOSLibrary.class);
            linkedLibrary.chmod(cmdFile, 0777);
        } else if (OS.isLinux()) {
            File file = new File(cmdFile);
            file.setReadable(true, false);
            file.setWritable(true, false);
            file.setExecutable(true, false);
        } else {
            System.out.println("OS not found");
        }
        return cmd;
    }

    interface LinkedOSLibrary extends Library {

        public int chmod(String path, int mode);
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
    public void componentStarted() {
        /* Nothing to do here */ }

    @Override
    public void componentStopped() {
        /* Nothing to do here */ }
}
