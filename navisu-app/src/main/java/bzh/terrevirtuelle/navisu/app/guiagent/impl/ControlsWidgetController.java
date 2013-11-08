package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;


/**
 * NaVisu
 *
 * @author tibus
 * @date 07/11/2013 23:35
 */
public class ControlsWidgetController {

    @FXML Button exitBtn;

    @FXML Button optionsBtn;

    @FXML Button helloBtn;

    @FXML
    public void onExit() {
        System.exit(0);
    }
}
