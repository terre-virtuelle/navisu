/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.view.gps;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.CustomMenuItemBuilder;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;
import bzh.terrevirtuelle.navisu.instruments.misc.IntField;
import bzh.terrevirtuelle.navisu.instruments.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class AlarmPane
        extends GPSPane {

    private ImageView anchorOn;
    private ImageView anchorOff;
    private Button anchorButton;
    private Button resetButton;
    private ImageView resetView;
    private Circle alarmCircle;
    private boolean alarmOn = false;
    private Path path;
    private boolean first = true;
    private LineTo lineTo;
    protected static final String REAL_SLIDER_TOOLTIP =
            "A slider ranging in meters whose values are bound to the fields to the left.";
    protected static final String VIRTUAL_SLIDER_TOOLTIP =
            "A slider ranging in meters whose values are bound to the fields to the left.";
    protected static final String EDIT_FIELD_TOOLTIP =
            "Click on me to edit the slider's value,\n"
            + "but you will only be able to enter numeric values within the slider range.\n"
            + "The slider will be updated as you type\n"
            + "and there is no need to commit the edit with an enter key.";
    protected AudioClip alarmSound;
    protected int distanceAlarm = 0;
    protected Text distanceAlarmText;
    protected Text distanceText;
    private double distance;
    private double azimut;
    private Text azimutText;
    double angle;
    private GeodeticCalculator geodeticCalculator;
    private final Ellipsoid ELLPSOID_REF = Ellipsoid.WGS84;
    private GlobalCoordinates startPosition;
    private GeodeticCurve geoCurve;
    private double x;
    private double y;
    private final double HALF_PI = Math.PI / 2;
    private final int X = 136;
    private final int Y = 138;
    private Slider alarmSlider;

    public AlarmPane(Display display) {
        super(display);
        setId("alarmPane");
        
        backgroundFileName = "gpsAlarmBackground.png";
        foregroundFileName = "verre.png";
        backgroundNightFileName = "night_gpsAlarmBackground.png";

        LAYOUT_X = 28;
        LAYOUT_Y = 28;
        path = new Path();

        path.setStrokeWidth(1);
        path.setStroke(Color.GREEN);

        alarmSound = new AudioClip(AlarmPane.class.getResource("resources/sounds/9248.mp3").toString());
        alarmSound.setCycleCount(1);
        geodeticCalculator = new GeodeticCalculator();
        createScene();
    }

    private void createScene() {
        backgroundImage = new Image(rootDir + IMAGES + backgroundFileName);
        background = ImageViewBuilder.create()
                .id("posSat")
                .layoutX(LAYOUT_X - 2)
                .layoutY(LAYOUT_Y)
                .pickOnBounds(true)
                .image(backgroundImage)
                .build();
        getChildren().add(background);
        anchorOn = ImageViewBuilder.create()
                .pickOnBounds(true)
                .image(new Image(rootDir + IMAGES + "anchorOn.png"))
                .build();
        anchorOff = ImageViewBuilder.create()
                .pickOnBounds(true)
                .image(new Image(rootDir + IMAGES + "anchorOff.png"))
                .build();
        resetView = ImageViewBuilder.create()
                .pickOnBounds(true)
                .image(new Image(rootDir + IMAGES + "reset.png"))
                .build();
        anchorButton = ButtonBuilder.create()
                .layoutX(35)
                .layoutY(35)
                .prefHeight(25)
                .prefWidth(25)
                .minWidth(Control.USE_PREF_SIZE)
                .minHeight(Control.USE_PREF_SIZE)
                .graphic(anchorOn)
                .tooltip(new Tooltip("Set Anchor"))
                .build();
        getChildren().add(anchorButton);

        anchorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (alarmOn == false) {
                    anchorButton.setGraphic(anchorOff);
                    alarmOn = true;
                } else {
                    anchorButton.setGraphic(anchorOn);
                    alarmOn = false;
                    if (alarmSound.isPlaying()) {
                        alarmSound.stop();
                    }
                }
            }
        });

        resetButton = ButtonBuilder.create()
                .layoutX(212)
                .layoutY(218)
                .prefHeight(25)
                .prefWidth(25)
                .minWidth(Control.USE_PREF_SIZE)
                .minHeight(Control.USE_PREF_SIZE)
                .graphic(resetView)
                .tooltip(new Tooltip("Reset Alarm"))
                .build();
        getChildren().add(resetButton);

        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                alarmOn = false;
                distanceAlarm = 0;
                azimut = 0;
                alarmSound.stop();
                alarmSlider.setValue(0);
                distanceAlarmText.setText(new Integer(distanceAlarm).toString());
                path.getElements().clear();
                first = true;
                alarmCircle.resize(0, 0);
                anchorButton.setGraphic(anchorOn);
                distanceText.setText(new Integer(distanceAlarm).toString() + " m");
                azimutText.setText(new Integer((int) azimut).toString() + " °");
            }
        });

        getChildren().add(path);
        foregroundImage = new Image(rootDir + IMAGES + foregroundFileName);
        foreground = ImageViewBuilder.create()
                .id("verreSat")
                .layoutX(LAYOUT_X)
                .layoutY(LAYOUT_Y)
                .mouseTransparent(true)
                .image(foregroundImage)
                .build();
        getChildren().add(foreground);

        Text driftLimitText = TextBuilder.create()
                .layoutX(185)
                .layoutY(LAYOUT_Y + 12)
                .fill(Color.RED)
                .text("Drift Limit")
                .font(Font.font(null, FontWeight.BOLD, 12))
                .build();
        getChildren().add(driftLimitText);

        distanceAlarmText = TextBuilder.create()
                .layoutX(205)
                .layoutY(LAYOUT_Y + 25)
                .fill(Color.RED)
                .text(new Integer(distanceAlarm).toString() + " m")
                .font(Font.font(null, FontWeight.BOLD, 12))
                .build();
        getChildren().add(distanceAlarmText);

        distanceText = TextBuilder.create()
                .layoutX(35)
                .layoutY(230)
                .fill(Color.LAWNGREEN)
                .text(new Integer(distanceAlarm).toString() + " m")
                .font(Font.font(null, FontWeight.BOLD, 12))
                .build();
        getChildren().add(distanceText);

        azimutText = TextBuilder.create()
                .layoutX(35)
                .layoutY(240)
                .fill(Color.LAWNGREEN)
                .text(new Integer((int) azimut).toString() + " °")
                .font(Font.font(null, FontWeight.BOLD, 12))
                .build();
        getChildren().add(azimutText);
        
        alarmCircle = CircleBuilder.create()
                .centerX(X)
                .centerY(Y)
                .radius(0)
                .fill(new Color(1, 1, 1, 0))
                .stroke(Color.RED)
                .build();
        getChildren().add(alarmCircle);
    }

    public void initMenu() {
            ObservableList<MenuItem> items = display.getMenuButton().getItems();
            if (items.size() != 0) {
                items.clear();
            }
            Menu alarmMenu = new Menu("Alarm");
            CustomMenuItem alarmMenuItem = CustomMenuItemBuilder.create()
                    .build();
            // create a slider.
            alarmSlider = new Slider(0, 100, 1);
            alarmSlider.setTooltip(new Tooltip(REAL_SLIDER_TOOLTIP));
            alarmSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                    distanceAlarm = (int) new_val.floatValue();
                    distanceAlarmText.setText(new Integer(distanceAlarm).toString() + " m");
                    alarmCircle.setRadius(distanceAlarm);
                }
            });
            // bind the slider bidirectionally to an editable text field restricted to the slider range.
            final IntField alarmIntField = new IntField(0, 100, 1);
            alarmIntField.setTooltip(new Tooltip(EDIT_FIELD_TOOLTIP));
            alarmIntField.setStyle("-fx-background-color: white; -fx-font-size: 20");
            alarmIntField.valueProperty().bindBidirectional(alarmSlider.valueProperty());
            alarmIntField.setPrefWidth(50);

            HBox alarmControls = new HBox(10);
            alarmControls.getChildren().addAll(alarmIntField, alarmSlider);
            alarmControls.setStyle(" -fx-alignment: baseline-left;");
            alarmMenuItem.setContent(alarmControls);

            alarmMenu.getItems().add(alarmMenuItem);
            items.addAll(alarmMenu);
    }

    public void setLocation(final float lat, final float lon) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (alarmOn == true) {
                    if (first == true) {
                        startPosition = new GlobalCoordinates(lat, lon);
                        first = false;
                        MoveTo moveTo = new MoveTo();
                        moveTo.setX(X);
                        moveTo.setY(Y);
                        path.getElements().add(moveTo);
                    } else {
                        lineTo = new LineTo();
                        geoCurve = geodeticCalculator.calculateGeodeticCurve(ELLPSOID_REF, startPosition, new GlobalCoordinates(lat, lon));
                        distance = geoCurve.getEllipsoidalDistance();
                        distanceText.setText(new Integer((int) distance).toString() + " m");
                        azimut = geoCurve.getAzimuth();
                        azimutText.setText(new Integer((int) azimut).toString() + " °");
                        angle = HALF_PI - Math.toRadians(azimut);
                        x = distance * Math.cos(angle);
                        y = distance * Math.sin(angle);
                        lineTo.setX(X + x);
                        lineTo.setY(Y - y);
                        path.getElements().add(lineTo);
                        if (distance >= distanceAlarm && alarmOn == true) {
                            alarmSound.play();
                        }
                    }
                }
            }
        });
    }
}
