/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Serge Morvan
 */
public class Widget
        extends Group {

    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    protected int click = 0;

    public Widget() {
        initEvt();
    }

    public void setScale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

    private void initEvt() {
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
