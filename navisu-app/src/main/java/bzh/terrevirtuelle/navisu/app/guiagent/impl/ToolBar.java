package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/11/2013 17:50
 */
public class ToolBar extends JFXAbstractDisplay {

    protected HBox container;

    protected int nbActions = 0;

    public ToolBar() {

        this.container = new HBox();
        this.container.setAlignment(Pos.CENTER_LEFT);
        this.container.setMinHeight(40d);
        this.container.setMaxHeight(40d);
        this.container.setStyle("-fx-background-color: rgba(0,0,0,0.20)");
    }

    public void addAction(Image icon, EventHandler<ActionEvent> callback) {

        Button button = new Button();
        button.setGraphic(new ImageView(icon));
        button.setOnAction(callback);
        HBox.setMargin(button, new Insets(4));

        this.container.getChildren().add(button);

        //this.container.setMaxWidth(this.nbActions++ * 40);
    }

    @Override
    public Node getDisplayable() {
        return this.container;
    }
}
