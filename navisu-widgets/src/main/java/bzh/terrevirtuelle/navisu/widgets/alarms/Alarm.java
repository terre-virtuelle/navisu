/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.alarms;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @date 26 avril 2015
 * @author Serge Morvan
 */
public class Alarm extends Group
        implements Widget {

    protected String onImgName;
    protected String offImgName;
    protected final ImageView onImg = new ImageView();
    protected final ImageView offImg = new ImageView();
    private boolean first = true;

    
    public Alarm(String onImgName, String offImgName) {
        onImg.setImage(new Image(getClass().getResourceAsStream(onImgName)));
        offImg.setImage(new Image(getClass().getResourceAsStream(offImgName)));
        initEvt();
        getChildren().add(offImg);
    }

    @Override
    public final void initEvt() {
        setOnMouseClicked((MouseEvent me) -> {
            if (first == true) {
                first = false;
                getChildren().remove(offImg);
                getChildren().add(onImg);
            } else {
                first = true;
                getChildren().remove(onImg);
                getChildren().add(offImg);
            }
        });
    }

    @Override
    public void setScale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

}
