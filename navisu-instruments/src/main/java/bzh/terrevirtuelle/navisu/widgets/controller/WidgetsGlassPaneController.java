/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.controller;

import bzh.terrevirtuelle.navisu.widgets.model.WidgetsGlassPane;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import bzh.terrevirtuelle.navisu.widgets.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class WidgetsGlassPaneController {
    
    protected WidgetsGlassPane widgetsGlassPane;
    
    public WidgetsGlassPaneController(int height, int width) {
        widgetsGlassPane = new WidgetsGlassPane(height, width);
    }
    
    public void add(Widget widget) {
        widgetsGlassPane.add(widget);
        widget.setWidgetsGlassPane(widgetsGlassPane);
    }

    public void remove(Widget widget) {
        widgetsGlassPane.remove(widget);
    }

    public JFXPanel getPanel() {
        return widgetsGlassPane.getPanel();
    }
    public Scene getScene(){
        return widgetsGlassPane.getScene();
    }
    public WidgetsGlassPane getWidgetsGlassPane() {
        return widgetsGlassPane;
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
