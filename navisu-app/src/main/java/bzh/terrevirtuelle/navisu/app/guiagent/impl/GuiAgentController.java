package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.logging.Logger;

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

    @FXML public BorderPane centerBorderPane;

    @FXML public BorderPane statusBorderPane;

    public GuiAgentController() {}
}
