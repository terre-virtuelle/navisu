/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.impl;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Serge Morvan
 */
public class WidgetImpl
        extends Group
        implements Widget {

    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    protected int click = 0;
    protected Scene scene;

    public WidgetImpl() {
         initEvt();
    }

    public WidgetImpl(Scene scene) {
        this.scene = scene;
        initEvt();
    }

    @Override
    public void setScale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

    @Override
    public void initEvt() {
        setOnMouseEntered((MouseEvent me) -> {
            toFront();
        });
        setOnMousePressed((MouseEvent me) -> {
            initX = getTranslateX();
            initY = getTranslateY();
            dragAnchor = new Point2D(me.getSceneX(), me.getSceneY());
        });
        setOnMouseDragged((MouseEvent me) -> {
            if (me != null && dragAnchor != null) {
                setTranslateX((int) (initX + me.getSceneX() - dragAnchor.getX()));
                setTranslateY((int) (initY + me.getSceneY() - dragAnchor.getY()));
            }
        });
        setOnMouseClicked((MouseEvent me) -> {
            if (me.isMetaDown() && click == 0) {
                setScale(1.5);
                click++;
            } else {
                if (me.isMetaDown() && click == 1) {
                    setScale(0.5);
                    click++;
                } else {
                    if (me.isMetaDown() && click == 2) {
                        setScale(1);
                        click = 0;
                    }
                }
            }
        });

    }

}
