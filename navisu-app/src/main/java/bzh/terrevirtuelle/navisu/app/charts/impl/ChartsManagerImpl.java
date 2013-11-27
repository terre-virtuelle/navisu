package bzh.terrevirtuelle.navisu.app.charts.impl;

import bzh.terrevirtuelle.navisu.app.OS;
import bzh.terrevirtuelle.navisu.app.Proc;
import bzh.terrevirtuelle.navisu.app.charts.ChartsManager;
import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.charts.impl.imageryinstaller.ImageryInstaller;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParserFactory;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import com.jogamp.common.os.Platform;
import gov.nasa.worldwind.layers.Layer;
import javafx.stage.FileChooser;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class ChartsManagerImpl implements ChartsManager, ChartsManagerServices, Driver, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(ChartsManagerImpl.class.getName());

    protected static final String EXTENSION = ".kap";

    @UsedService GeoViewServices geoViewServices;

    @Override
    public void componentInitiated() {}

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if(file.toLowerCase().endsWith(EXTENSION)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void open(String... files) {

        for(String file : files) {
            this.handleOpenFile(file);
        }
    }

    protected void handleOpenFile(String file) {
        LOGGER.info("Opening " + file + " ...");

        String inputFile = file;

        if(OS.isMac()) {

            String tifFile = file.concat(".tif");

            try {
                Proc.builder.create()
                        .setCmd("navisu-app/bin/osx/gdal_translate")
                        .addArg("-of GTiff")
                        .addArg("-expand rgb")
                        .addArg(file)
                        .addArg(tifFile)
                        .setOut(System.out)
                        .setErr(System.err)
                        .exec();

                inputFile = tifFile;

            } catch (IOException | InterruptedException e) {
                LOGGER.log(Level.SEVERE, null, e);
            }
        }

        ImageryInstaller installer = ImageryInstaller.factory.newImageryInstaller();
        installer.setImageFormat(ImageryInstaller.ImageFormatEnum.PNG);

        Layer layer = installer.installSurfaceImage(inputFile);
        geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layer));
    }

    @Override
    public String getName() {
        return "BSB/KAP";
    }

    @Override
    public String[] getExtensions() {
        return new String[] { "*" + EXTENSION };
    }

    @Override
    public void openChart(String file) {
        this.open(file);
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
