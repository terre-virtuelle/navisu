/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.boardclock.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.boardclock.impl.BoardClockImpl;
import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import java.net.URL;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
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
public class BoardClockController
        extends InstrumentController
        implements Initializable {

    private final DateTimeFormatter utcdateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter utctimeFormatter = DateTimeFormatter.ofPattern("KK:mm:ss a");
    private final DateTimeFormatter onboarddateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter onboardtimeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
    private final DateTimeFormatter localdateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter localtimeFormatter = DateTimeFormatter.ofPattern("KK:mm:ss a");
    private final String FXML = "clock.fxml";
    
    @FXML
    public Text utcdaydate;
    @FXML
    public Text utchours;
    @FXML
    public Text onboarddaydate;
    @FXML
    public Text onboardhours;
    @FXML
    public Text localdaydate;
    @FXML
    public Text localhours;
        
    public ZoneId zoneid = ZoneId.of("UTC+06:00");

    protected BoardClockImpl instrument;
    Timeline digitalTime;

    public BoardClockController(BoardClockImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        load(FXML);
        
        digitalTime = new Timeline(
                new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                    
                    utcdaydate.setText(LocalDate.now(Clock.systemUTC()).format(utcdateFormatter));
                    utchours.setText(LocalTime.now(Clock.systemUTC()).format(utctimeFormatter));
                    onboarddaydate.setText(LocalDate.now(Clock.systemDefaultZone()).format(onboarddateFormatter));
                    onboardhours.setText(LocalTime.now(Clock.systemDefaultZone()).format(onboardtimeFormatter));
                    localdaydate.setText(LocalDate.now(Clock.system(zoneid)).format(localdateFormatter));
                    localhours.setText(LocalTime.now(Clock.system(zoneid)).format(localtimeFormatter));

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
