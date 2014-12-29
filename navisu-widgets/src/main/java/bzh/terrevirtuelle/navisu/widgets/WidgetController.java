/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Serge Morvan
 */
public class WidgetController
        extends Group
        implements Widget, EventHandler<KeyEvent> {

    
    private final KeyCombination keyComb;
    private boolean first = true;
    protected double initX;
    protected double initY;
    protected Point2D dragAnchor;
    protected int click = 0;

    public WidgetController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        keyComb = new KeyCodeCombination(keyCode, keyCombination);
        initEvt();
    }

    @Override
    public void handle(KeyEvent event) {
        if (keyComb.match(event)) {
            if (first == true) {
                //  startFadeTransition(this, 1.0, 0.0);
                first = false;
                setVisible(first);
                stop();
            } else {
                //  startFadeTransition(this, 0.0, 1.0);
                first = true;
                setVisible(first);
                start();
            }
        }
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
                scale(1.5);
                click++;
            } else {
                if (me.isMetaDown() && click == 1) {
                    scale(0.5);
                    click++;
                } else {
                    if (me.isMetaDown() && click == 2) {
                        scale(1);
                        click = 0;
                    }
                }
            }
        });
    }

    @Override
    public void scale(double scale) {
        setScaleX(scale);
        setScaleY(scale);
    }

    public void startFadeTransition(Group group, double start, double end) {
        FadeTransition fade = new FadeTransition(Duration.millis(1000), this);
        fade.setFromValue(start);
        fade.setToValue(end);
        fade.play();
    }

    public void startScaleTransition(Group group) {
        ScaleTransition scaleTransition
                = new ScaleTransition(Duration.millis(2000), this);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(Transition.INDEFINITE);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

    public void startRotateTransition(Group group) {
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(3000), group);
        rotateTransition.setByAngle(180);
        rotateTransition.setCycleCount(Transition.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();
    }

    public void startParallelTransition(Group group,
            float x, float y, float xx, float yy,
            float scaleX, float scaleY,
            float scaleXX, float scaleYY) {
        final Duration SEC_1 = Duration.millis(2000);
        final Duration SEC_2 = Duration.millis(3000);
        final Duration SEC_3 = Duration.millis(1000);

        group.setScaleX(scaleX);
        group.setScaleY(scaleY);

        FadeTransition fade = new FadeTransition(SEC_1);
        fade.setFromValue(0.0f);
        fade.setToValue(1.0f);

        TranslateTransition translate = new TranslateTransition(SEC_3);
        translate.setFromX(x);
        translate.setToX(xx);
        translate.setFromY(y);
        translate.setToY(yy);

        ScaleTransition scale = new ScaleTransition(SEC_2);
        scale.setToX(scaleXX);
        scale.setToY(scaleYY);
        ParallelTransition pt = new ParallelTransition(group, fade, translate, scale);
        pt.play();
    }
    // Define an event handler


}
