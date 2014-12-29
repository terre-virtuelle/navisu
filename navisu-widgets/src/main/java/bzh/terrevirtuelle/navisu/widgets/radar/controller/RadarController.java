/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.radar.controller;

import bzh.terrevirtuelle.navisu.widgets.WidgetController;

import java.io.IOException;
import static java.lang.Math.PI;
import static java.lang.Math.sin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Serge modifs Dom : variables public
 */
public class RadarController
        extends WidgetController
        implements Initializable {

    @FXML
    public Group radar;
    @FXML
    public ImageView faisceau;
    @FXML
    public Circle spot1;
    public Circle spot2;
    boolean first = true;
    @FXML
    public double route = 0.0;
    public double angle;
    public double spotInitX = 20.0;
    public double spotInitY = 20.0;
    public double spotX;
    public Text couleur;
    final Rotate rotationTransform = new Rotate(0, 0, 0);
    Timeline fiveSecondsWonder;

    public RadarController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Radar-fullscreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        setPlot();
    }

    private void schedule() {
        fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.03), (ActionEvent event) -> {
            angle = (route * PI / 360);
            spot1.setTranslateX(spotInitX + (sin(angle) * 25));
            spot1.setTranslateY(spotInitY + route / 2);
            faisceau.setRotate(route);
            route++;
            route %= 360;
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void start() {
        schedule();
    }

    @Override
    public void stop() {
        fiveSecondsWonder.stop();
    }

    public void setPlot() {

        Circle circle = new Circle();
        circle.setCenterX(600.0f);
        circle.setCenterY(500.0f);
        circle.setRadius(3.0f);
        circle.setStroke(Paint.valueOf("#00ff22"));
        radar.getChildren().add(circle);

        circle.setOnMouseClicked((MouseEvent me) -> {
            
            if (first) {
                circle.setRadius(5.0f);
                first = false;
            } else {
                circle.setRadius(3.0f);
                first = true;
            }
        });
    }
}
