/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.mob;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @date 21 mars 2015
 * @author Serge Morvan
 */
public final class Mob extends Group
        implements Widget {

    protected final ImageView mobOffImg = new ImageView();
    protected final ImageView mobOnImg = new ImageView();
    private boolean first = true;
 
    public Mob() {
        mobOffImg.setImage(new Image(getClass().getResourceAsStream("MOB_Off.png")));
        mobOnImg.setImage(new Image(getClass().getResourceAsStream("MOB_On.png")));
        initEvt();
        getChildren().add(mobOffImg);
    }

    @Override
    public void initEvt() {
        setOnMouseClicked((MouseEvent me) -> {
            if (first == true) {
                first = false;
                getChildren().remove(mobOffImg);
                getChildren().add(mobOnImg);
            } else {
                first = true;
                getChildren().remove(mobOnImg);
                getChildren().add(mobOffImg);
            }
        });
    }

    @Override
    public void setScale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

}
