/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay.hud_3;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import java.io.IOException;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Serge
 * modifs Dom : variables public
 */
public class HUD_3_2_1_Controller 
extends Widget 
implements Initializable {

    @FXML
    

    public Text heading;
    public Double route=0.0;
    
    public HUD_3_2_1_Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML_HUD_simple.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    public void schedule() {
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.2), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               // System.out.println("heading " + heading+"route "+route);
                heading.setText(route.toString());
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
