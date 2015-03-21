/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay;

import bzh.terrevirtuelle.navisu.widgets.impl.WidgetImpl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Serge Morvan
 */
public class HeadUpDisplay
        extends WidgetImpl {

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
                .build();
        getChildren().add(background);
    }

    public void initEvt() {
        setOnMouseDragged((MouseEvent me) -> {
            if (me != null && dragAnchor != null) {
                setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
            }
        });
    }
  
   
}
