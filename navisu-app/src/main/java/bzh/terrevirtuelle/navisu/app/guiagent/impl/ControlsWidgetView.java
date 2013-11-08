package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.InputStream;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/11/2013 00:21
 */
public class ControlsWidgetView {

    AnchorPane root;

    public ControlsWidgetView() {

        FXMLLoader loader = new FXMLLoader();
        InputStream in = this.getClass().getResourceAsStream("ControlsWidget.fxml");

        try {
            root = loader.load(in);
        } catch (IOException e) {}
    }

    Display<Node> getDisplay() {
        return Display.factory.newDisplayNode(this.root);
    }
}
