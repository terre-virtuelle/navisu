package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.charts.impl.ChartsManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import bzh.terrevirtuelle.navisu.app.grib.impl.GribImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import java.util.logging.Logger;

import bzh.terrevirtuelle.navisu.app.guiagent.utilities.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public class AppMain extends Application {

    private static final Logger LOGGER = Logger.getLogger(AppMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        Translator.setLang(I18nLangEnum.FRENCH);

        final ComponentManager componentManager = ComponentManager.componentManager;

        LOGGER.info(
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
        driverServices.registerNewDriver(chartsServices.getDriver());
        driverServices.registerNewDriver(gribServices.getDriver());
    }

    public static void main(String[] args) {
        Application.launch();

        //test
    }
}
