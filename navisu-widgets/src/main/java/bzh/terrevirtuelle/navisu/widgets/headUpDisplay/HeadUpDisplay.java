/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Serge Morvan
 */
public class HeadUpDisplay
        extends Widget {

    private ImageView background;
    public HeadUpDisplay() {
        createScene();
    }

    protected final void createScene() {

        background = ImageViewBuilder.create()
                .image(new Image(getClass().getResourceAsStream("u25.png")))
                .scaleX(.6)
                .scaleY(.6)
                .opacity(.7)
                //.layoutX(-200)
                //.layoutY(-100)
                .build();
        getChildren().add(background);
    }

    protected void initEvt() {
        setOnMouseDragged((MouseEvent me) -> {
            if (me != null && dragAnchor != null) {
                setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
            }
        });
    }
}
