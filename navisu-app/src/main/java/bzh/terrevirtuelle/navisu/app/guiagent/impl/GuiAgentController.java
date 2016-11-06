package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

import java.util.logging.Logger;
import javafx.scene.layout.StackPane;

/**
 * NaVisu
 *
 * @author tibus
 * @date 21/12/2013 16:45
 */
public class GuiAgentController {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentController.class.getName());

    @FXML public MenuBar menuBar;

    @FXML public BorderPane leftBorderPane;

    @FXML public StackPane centerStackPane;
    
    @FXML public BorderPane statusBorderPane;

    public GuiAgentController() {}

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public BorderPane getLeftBorderPane() {
        return leftBorderPane;
    }

    public StackPane getCenterStackPane() {
        return centerStackPane;
    }

    public BorderPane getStatusBorderPane() {
        return statusBorderPane;
    }
    
}
