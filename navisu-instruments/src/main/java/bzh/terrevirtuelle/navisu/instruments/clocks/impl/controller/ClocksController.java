/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.clocks.impl.controller;

import bzh.terrevirtuelle.navisu.instruments.clocks.impl.ClocksImpl;
import bzh.terrevirtuelle.navisu.instruments.common.controller.InstrumentController;
import java.nio.file.Paths;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
public class ClocksController
        extends InstrumentController {

    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    private static ClocksController INSTANCE;
    private final DateTimeFormatter utcdateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter utctimeFormatter = DateTimeFormatter.ofPattern("KK:mm:ss a");
    private final DateTimeFormatter onboarddateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter onboardtimeFormatter = DateTimeFormatter.ofPattern("kk:mm:ss");
    private final DateTimeFormatter localdateFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter localtimeFormatter = DateTimeFormatter.ofPattern("KK:mm:ss a");
    private final String FXML = "clocks.fxml";

    @FXML
    public Group clocksgroup;
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
    @FXML
    public Button hourup;
    @FXML
    public Button hourdown;
    public static Clock Boardclock;
    public ZoneId zoneid = ZoneId.systemDefault();
    protected String viewgroupstyle = "clocks.css";
    protected ClocksImpl instrument;
    protected Timeline timeline;

    public static ClocksController getInstance(ClocksImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        if (INSTANCE == null) {
            INSTANCE = new ClocksController(instrument, keyCode, keyCombination);
        }
        return INSTANCE;
    }

    private ClocksController(ClocksImpl instrument, KeyCode keyCode, KeyCombination.Modifier keyCombination) {
        super(keyCode, keyCombination);
        this.instrument = instrument;
        load(FXML);
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        clocksgroup.getStylesheets().add(uri);
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), (ActionEvent actionEvent) -> {
                    utcdaydate.setText(LocalDate.now(Clock.systemUTC()).format(utcdateFormatter));
                    utchours.setText(LocalTime.now(Clock.systemUTC()).format(utctimeFormatter));
                    onboarddaydate.setText(LocalDate.now(Boardclock.systemDefaultZone()).format(onboarddateFormatter));
                    onboardhours.setText(LocalTime.now(Boardclock.systemDefaultZone()).format(onboardtimeFormatter));
                    localdaydate.setText(LocalDate.now(Clock.system(zoneid)).format(localdateFormatter));
                    localhours.setText(LocalTime.now(Clock.system(zoneid)).format(localtimeFormatter));

                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
        hourup.setOnMouseClicked((MouseEvent event) -> {
            
        });
         
        hourdown.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
        quit.setOnMouseClicked((MouseEvent event) -> {
            instrument.off();
        });
    }

    @Override
    public void stop() {
        timeline.stop();
    }
}
