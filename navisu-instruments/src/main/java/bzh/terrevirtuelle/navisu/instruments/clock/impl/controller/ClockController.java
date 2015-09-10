/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.clock.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.clock.impl.ClockImpl;
import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class ClockController
        extends Widget2DController
        implements Initializable {

    private final String FXML = "Clock.fxml";
    @FXML
    public Slider slider;
    @FXML
    public Group view;
    @FXML
    public ImageView quit;

    protected ClockImpl instrument;
    protected ComponentManager cm = ComponentManager.componentManager;
    /*
     Events subscribe zone
    
     */

    public ClockController(ClockImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    private void subscribe() {

    }
}
