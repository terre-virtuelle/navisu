/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.surveyZone;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

/**
 *
 * @author Serge
 */
public class SurveyZoneDisplayController extends Group implements Initializable {

    @FXML
    TextArea surveyZoneDisplay;
    private Integer route = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public SurveyZoneDisplayController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SurveyZoneDisplay.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void schedule() {

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(.1), (ActionEvent event) -> {
            surveyZoneDisplay.appendText(route.toString() + "\n");
            route++;
            route %= 360;
            // System.out.println("schedule");
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }
}
