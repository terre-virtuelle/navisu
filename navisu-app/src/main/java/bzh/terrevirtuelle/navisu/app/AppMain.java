package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.charts.impl.ChartsManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import bzh.terrevirtuelle.navisu.app.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import gov.nasa.worldwind.util.gdal.GDALUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;
import org.gdal.gdal.gdal;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class AppMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(AppMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        // initialize translation
        Translator.setLang(I18nLangEnum.FRENCH);

        // initialize logging
        LogManager.getLogManager().readConfiguration(new FileInputStream("conf/logging.properties"));

        // configure GDAL_DATA path
        //GDALUtils.canOpen(null);
        //gdal.SetConfigOption("GDAL_DATA", "lib-external/gdal/data");
        //System.setProperty("GDAL_DATA", Paths.get("lib-external/gdal/data").toAbsolutePath().toString());

        final ComponentManager componentManager = ComponentManager.componentManager;

        // deploy components
        LOGGER.info("\n" +
                componentManager.startApplication(

                        GuiAgentImpl.class,
                        DriverManagerImpl.class,
                        ChartsManagerImpl.class,
                        GribImpl.class
                )
        );

        GuiAgentServices guiServices = componentManager.getComponentService(GuiAgentServices.class);
        guiServices.showGui(stage, 800, 500);

        ChartsManagerServices chartsServices = componentManager.getComponentService(ChartsManagerServices.class);
        GribServices gribServices = componentManager.getComponentService(GribServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.init();

        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());

        //chartsServices.openChart("data/101.KAP");
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}













