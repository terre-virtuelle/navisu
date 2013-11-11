package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.app.charts.impl.ChartsManagerImpl;
import bzh.terrevirtuelle.navisu.app.drivers.DriverManagerServices;
import bzh.terrevirtuelle.navisu.app.drivers.impl.DriverManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nServices;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.impl.I18nImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import java.util.logging.Logger;

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

        final ComponentManager componentManager = ComponentManager.componentManager;

        LOGGER.info(
                componentManager.startApplication(

                        GuiAgentImpl.class,
                        DriverManagerImpl.class,
                        ChartsManagerImpl.class
                )
        );

        I18nServices translationServices = componentManager.getComponentService(I18nServices.class);
        translationServices.setLang(I18nLangEnum.FRENCH);

        GuiAgentServices guiServices = componentManager.getComponentService(GuiAgentServices.class);
        guiServices.showGui(stage, 800, 500);

        ChartsManagerServices chartsServices = componentManager.getComponentService(ChartsManagerServices.class);

        DriverManagerServices driverServices = componentManager.getComponentService(DriverManagerServices.class);
        driverServices.registerNewDriver(chartsServices.getDriver());
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
