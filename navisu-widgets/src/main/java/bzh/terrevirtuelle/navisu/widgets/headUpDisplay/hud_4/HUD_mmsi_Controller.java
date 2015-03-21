/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.headUpDisplay.hud_4;

import bzh.terrevirtuelle.navisu.widgets.impl.WidgetImpl;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Serge
 * modifs Dom : variables public
 */
public class HUD_mmsi_Controller
extends WidgetImpl 
implements Initializable {

    @FXML
    public Text mmsi;
    @FXML
    public Text acronym;
    public Text objectname;
    public Integer mmsitest;
    public Integer route=0;
    
    public HUD_mmsi_Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HUD_MMSI.fxml"));
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
                mmsitest = 227483000 + route;
                mmsi.setText(mmsitest.toString());
                acronym.setText("SEAARE");
                objectname.setText("CHAUSSÉE DE SEIN");
                
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
