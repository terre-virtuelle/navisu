/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.zoneclock.impl.controller;

import antlr.collections.AST;
import bzh.terrevirtuelle.navisu.instruments.zoneclock.impl.ZoneClockImpl;
import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class ZoneClockController
        extends InstrumentController
        implements Initializable {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("KK:mm:ss a");
    private final String FXML = "clock.fxml";
    @FXML
    public Text daydate;
    @FXML
    public Text hours;
    @FXML
    public Text minutes;
    @FXML
    public Text seconds;
    
    public ZoneId zoneid = ZoneId.of("UTC+06:00");
    
   
    
    protected ZoneClockImpl instrument;
    Timeline digitalTime;
    

    public ZoneClockController(ZoneClockImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        load(FXML);
        
        digitalTime = new Timeline(
                new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                    daydate.setText(LocalDate.now(Clock.system(zoneid)).format(dateFormatter));
                    hours.setText(LocalTime.now(Clock.system(zoneid)).format(timeFormatter));
                }),
                new KeyFrame(Duration.seconds(1))
        );
        // time never ends.
        digitalTime.setCycleCount(Animation.INDEFINITE);
        // start the Clock.
        digitalTime.play();

        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
    
    }
    @Override
    public void stop() {
        digitalTime.stop();
    }

}
