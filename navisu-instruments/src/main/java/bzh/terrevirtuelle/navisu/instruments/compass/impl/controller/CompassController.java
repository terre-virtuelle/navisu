/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.compass.impl.controller;

import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.instruments.compass.impl.CompassImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class CompassController
        extends Widget2DController
        implements Initializable {

    private final String FXML = "Compass.fxml";
    @FXML
    public Group view;
    @FXML
    public ImageView quit;
    @FXML
    public Slider slider;
    @FXML
    public Text headingValue;
    @FXML
    public Text headingValue2;
    @FXML
    public Text headingDeviation;
    @FXML
    public Text headingVariation;
    @FXML
    public ImageView couronne;

    protected CompassImpl instrument;
    protected ComponentManager cm = ComponentManager.componentManager;
    double org = 0;
    /*
     Events subscribe zone
    
     */

    public CompassController(CompassImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        subscribe();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        view.setOpacity(0.8);
        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });

        slider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            Platform.runLater(() -> {
                view.setOpacity(slider.getValue());
            });
        });

    }

    public void notifyNmeaMessageChanged(HDG data) {
        HDG hdg = (HDG) data;

        Platform.runLater(() -> {
            headingValue2.setText(Float.toString(hdg.getHeading()));
            headingValue.setText(Integer.toString((int) hdg.getHeading()));
            headingDeviation.setText(Float.toString(hdg.getMagneticDeviation()));
            headingVariation.setText(Float.toString(hdg.getMagneticVariation()));
          //  RotateTransition rt = new RotateTransition(Duration.millis(10), couronne);
            // rt.setByAngle((hdg.getHeading() - org));
            //  rt.play();
            couronne.setRotate(360 - hdg.getHeading());
        });
        org = hdg.getHeading();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void subscribe() {

    }
}
