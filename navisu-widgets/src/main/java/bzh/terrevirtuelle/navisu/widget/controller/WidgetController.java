/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widget.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.util.Duration;

/**
 *
 * @author Serge Morvan
 */
public class WidgetController {
    
  
    public WidgetController() {

    }
    
    public void startFadeTransition(Group group) {
        FadeTransition fade = new FadeTransition(Duration.millis(3000), group);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setCycleCount(Transition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();
    }
    
    public void startScaleTransition(Group group) {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(2000), group);
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
}
