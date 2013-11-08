package bzh.terrevirtuelle.navisu.app;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18n;
import bzh.terrevirtuelle.navisu.app.guiagent.impl.GuiAgentImpl;

import java.util.Locale;
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

        String result = componentManager.startApplication(
                GuiAgentImpl.class
        );

        LOGGER.info(result);

        GuiAgentServices guiAgentServices = componentManager.getComponentService(GuiAgentServices.class);
        guiAgentServices.showGui(stage, 500, 500);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
