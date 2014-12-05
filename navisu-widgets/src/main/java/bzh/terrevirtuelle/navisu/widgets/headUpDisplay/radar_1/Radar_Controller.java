/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay.radar_1;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import java.io.IOException;
import static java.lang.Math.PI;
import static java.lang.Math.sin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

/**
 *
 * @author Serge
 * modifs Dom : variables public
 */
public class Radar_Controller 
extends Widget 
implements Initializable {
    
    @FXML
    public ImageView faisceau;
    @FXML
    public Circle spot1;
    public Circle spot2;
    @FXML
    public double route=0.0;
    public double angle;
    public double spotInitX=20.0;
    public double spotInitY=20.0;
    public double spotX;
    public Text couleur;
    final Rotate rotationTransform = new Rotate(0, 0, 0);
    
    
    public Radar_Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_Radar-faisceau.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    

    
    public void schedule() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.03), new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
            // System.out.println("heading " + heading+"route "+route);
                //spotX= spotInitX+(sin(route*PI/360)*25);
                angle =(route*PI/360);
                //spot1.setTranslateX(spotInitX+(angle*25));
                //spot1.setTranslateY(spotInitY+route/2);
                faisceau.getTransforms().add(new Rotate(angle, 0, 150, 0, Rotate.Z_AXIS));
                //faisceau.setRotate(route);
                //faisceau.setLength(60.0);
                    route++;
                    route %= 360;
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
