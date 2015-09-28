/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.clock.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.clock.impl.ClockImpl;
import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
public class ClockController
        extends InstrumentController
        implements Initializable {

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
    private final String FXML = "clock.fxml";
    @FXML
    public Text daydate;
    @FXML
    public Text hours;
    @FXML
    public Text minutes;
    @FXML
    public Text seconds;

    protected ClockImpl instrument;
    Timeline digitalTime;

    public ClockController(ClockImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        load(FXML);
        // the digital clock updates once a second.
        /*
         final Timeline digitalTime = new Timeline(
         new KeyFrame(Duration.seconds(0),
         new EventHandler<ActionEvent>() {
         @Override
         public void handle(ActionEvent actionEvent) {
         Calendar calendar = GregorianCalendar.getInstance();
         daydate.setText((Integer.toString(calendar.get(Calendar.DATE))) + " / " + ((Integer.toString(calendar.get(Calendar.MONTH) + 1))) + " / " + (Integer.toString(calendar.get(Calendar.YEAR))));
         hours.setText(Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)));
         minutes.setText(Integer.toString(calendar.get(Calendar.MINUTE)));
         seconds.setText(Integer.toString(calendar.get(Calendar.SECOND)));

         }
         }
         ),
         new KeyFrame(Duration.seconds(1))       
         );   
         */
        digitalTime = new Timeline(
                new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                    daydate.setText(LocalDate.now(Clock.systemUTC()).format(dateFormatter));
                    hours.setText(LocalTime.now(Clock.systemUTC()).format(timeFormatter));
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
