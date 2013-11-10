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
public class StopWidget
        extends Widget {

    private ImageView background;
    private Button button;

    public StopWidget() {
        createScene();
    }

    @Override
    protected final void createScene() {
        background = ImageViewBuilder.create()
                .image(new Image(getClass().getResourceAsStream("resources/images/cancel-icon.png")))
                .build();
        button = ButtonBuilder.create()
                .graphic(background)
                .layoutX(55)
                .layoutY(80)
                .build();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        getChildren().add(button);
    }
}
