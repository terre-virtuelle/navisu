/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.view.button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class ButtonWidget
        extends Widget {

    private ImageView background;
    private Button button;

    public ButtonWidget() {
        createScene();
    }

    @Override
    protected final void createScene() {
        background = ImageViewBuilder.create()
                .image(new Image(getClass().getResourceAsStream("resources/images/buttonWidgetBG.png")))
                .build();
        getChildren().add(background);
        button = ButtonBuilder.create()
                .text("Widget Test")
                .layoutX(55)
                .layoutY(80)
                .prefWidth(100)
                .prefHeight(15)
                .build();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Button");
            }
        });
        getChildren().add(button);
    }
}
