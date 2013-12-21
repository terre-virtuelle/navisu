package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 21/12/2013 16:45
 */
public class GuiAgentController {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentController.class.getName());

    @FXML MenuBar menuBar;

    @FXML BorderPane leftBorderPane;

    @FXML BorderPane centerBorderPane;

    public GuiAgentController() {
        LOGGER.info("");
    }
}
