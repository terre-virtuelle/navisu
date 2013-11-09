/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.sounder;

import bzh.terrevirtuelle.navisu.instruments.instrument.misc.IntField;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.events.SounderEvent;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.CustomMenuItemBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CheckMenuItemBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import jfxtras.labs.scene.control.gauge.LcdBuilder;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;

/**
 *
 * @author Serge Morvan
 */
public class RealSounderPane
        extends SounderPane {

    private final String RANGE_0 = "0...30 m";
    private final String RANGE_1 = "0...50 m";
    private final String RANGE_2 = "0...100 m";

    public RealSounderPane(Display display) {
        super(display);

        backgroundFileName = "sounderBackgroundSail.png";
        foregroundFileName = "verre.png";
        backgroundNightFileName = "night_sounderBackgroundSail.png";
        attibutes.put("type", "realSounder");
        createScene();
        alarmSound = new AudioClip(RealSounderPane.class.getResource("resources/sounds/9248.mp3").toString());
    }

    private void createScene() {
        setId("realSounder");
        createBasicScene();

        lines = new ArrayList<>();
        lcd0 = LcdBuilder.create()
                .styleModel(STYLE_MODEL_1)
                .thresholdBehaviorInverted(true)
                .threshold(4)
                .bargraphVisible(true)
                .minMeasuredValueVisible(true)
                .minMeasuredValueDecimals(1)
                .maxMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(1)
                .formerValueVisible(true)
                .layoutX(65.0)
                .layoutY(40.0)
                .prefHeight(70.0)
                .prefWidth(150.0)
                .title("Depth")
                .unit("m")
                .build();
        lcd = lcd0;
        root.getChildren().add(lcd);
        lcd1 = LcdBuilder.create()
                .styleModel(STYLE_MODEL_1)
                .thresholdBehaviorInverted(true)
                .threshold(4)
                .bargraphVisible(true)
                .minMeasuredValueVisible(true)
                .minMeasuredValueDecimals(1)
                .maxMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(1)
                .formerValueVisible(true)
                .layoutX(65.0)
                .layoutY(40.0)
                .prefHeight(70.0)
                .prefWidth(150.0)
                .title("Depth")
                .unit("fa")
                .build();
        lcd2 = LcdBuilder.create()
                .styleModel(STYLE_MODEL_1)
                .thresholdBehaviorInverted(true)
                .threshold(4)
                .bargraphVisible(true)
                .minMeasuredValueVisible(true)
                .minMeasuredValueDecimals(1)
                .maxMeasuredValueVisible(true)
                .maxMeasuredValueDecimals(1)
                .formerValueVisible(true)
                .layoutX(65.0)
                .layoutY(40.0)
                .prefHeight(70.0)
                .prefWidth(150.0)
                .title("Depth")
                .unit("ft")
                .build();
        rangeLabel = LabelBuilder.create()
                .id("range")
                .text("30")
                .prefWidth(40.0)
                .alignment(Pos.BASELINE_RIGHT)
                .textFill(Color.RED)
                .font(Font.font("Cambria", 10))
                .layoutX(15)
                .layoutY(215)
                .build();
        root.getChildren().add(rangeLabel);
    }

    @Override
    public void initPage() {
        if (display.isDay() == true) {
            lcd.setStyleModel(STYLE_MODEL_1);
            if (getAttributes().get("boatType").equals("sail")) {
                backgroundImage = new Image(rootDir + IMAGES + "sounderBackgroundSail.png");
            } else {
                backgroundImage = new Image(rootDir + IMAGES + "sounderBackgroundMotor.png");
            }
        } else {
            lcd.setStyleModel(STYLE_MODEL_2);
            if (getAttributes().get("boatType").equals("sail")) {
                backgroundImage = new Image(rootDir + IMAGES + "night_sounderBackgroundSail.png");
            } else {
                backgroundImage = new Image(rootDir + IMAGES + "night_sounderBackgroundMotor.png");
            }
        }
        background.setImage(backgroundImage);

        display.getDayNightButton().setOnMouseClicked(
                new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                if (display.isDay() == true) {
                    display.setDay(false);
                    lcd.setStyleModel(STYLE_MODEL_2);
                    if (getAttributes().get("boatType").equals("sail")) {
                        backgroundImage = new Image(rootDir + IMAGES + "night_sounderBackgroundSail.png");
                    } else {
                        backgroundImage = new Image(rootDir + IMAGES + "night_sounderBackgroundMotor.png");
                    }
                } else {
                    display.setDay(true);
                    lcd.setStyleModel(STYLE_MODEL_1);
                    if (getAttributes().get("boatType").equals("sail")) {
                        backgroundImage = new Image(rootDir + IMAGES + "sounderBackgroundSail.png");
                    } else {
                        backgroundImage = new Image(rootDir + IMAGES + "sounderBackgroundMotor.png");
                    }
                }
                background.setImage(backgroundImage);
            }
        });
    }

    @Override
    public void initMenu() {
        ObservableList<MenuItem> items = display.getMenuButton().getItems();
        if (items.size() != 0) {
            items.clear();
        }

        MenuItem sailMotorItem = new MenuItem("Sail/Motor");
        items.addAll(sailMotorItem);
        sailMotorItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (getAttributes().get("boatType").equals("sail")) {
                    getAttributes().put("boatType", "motor");
                    sounder.getVirtualAttributes().put("boatType", "motor");
                    fireSounderEvent(new SounderEvent(this, attibutes));
                    if (display.isDay() == true) {
                        background.setImage(new Image(rootDir + IMAGES + "sounderBackgroundMotor.png"));
                    } else {
                        background.setImage(new Image(rootDir + IMAGES + "night_sounderBackgroundMotor.png"));
                    }
                } else {
                    getAttributes().put("boatType", "sail");
                    sounder.getVirtualAttributes().put("boatType", "sail");
                    fireSounderEvent(new SounderEvent(this, attibutes));
                    if (display.isDay() == true) {
                        background.setImage(new Image(rootDir + IMAGES + "sounderBackgroundSail.png"));
                    } else {
                        background.setImage(new Image(rootDir + IMAGES + "night_sounderBackgroundSail.png"));
                    }
                }
            }
        });

        Menu depthRange = new Menu("Depth range");
        CheckMenuItem[] depthRanges = new CheckMenuItem[3];
        depthRanges[0] = CheckMenuItemBuilder.create().text(RANGE_0).build();
        depthRanges[1] = CheckMenuItemBuilder.create().text(RANGE_1).build();
        depthRanges[2] = CheckMenuItemBuilder.create().text(RANGE_2).build();
        for (int i = 0; i < depthRanges.length; i++) {
            depthRanges[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String tmp = ((MenuItem) evt.getSource()).getText();
                    if (tmp != null && !tmp.equals("")) {
                        switch (tmp) {
                            case RANGE_0:
                                range = 30.f;
                                rangeLabel.setText("30");
                                break;
                            case RANGE_1:
                                range = 50.f;
                                rangeLabel.setText("50");
                                break;
                            case RANGE_2:
                                range = 100.f;
                                rangeLabel.setText("100");
                                break;
                        }
                        root.getChildren().removeAll(lines);
                        lines.clear();
                        dec = 0;
                        attibutes.put("range", (new Float(range).toString()));
                        fireSounderEvent(new SounderEvent(this, attibutes));
                    }
                }
            });
        }
        depthRange.getItems().addAll(depthRanges);
        items.addAll(depthRange);

        Menu offsetMenu = new Menu("Offset");
        CustomMenuItem offsetMenuItem = CustomMenuItemBuilder.create()
                .build();
        // create a slider.
        final Slider slider = new Slider(0, 200, 50);
        slider.setTooltip(new Tooltip(REAL_SLIDER_TOOLTIP));
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                offset = new_val.floatValue() / 100;
                attibutes.put("offset", (new Float(offset).toString()));
                fireSounderEvent(new SounderEvent(this, attibutes));
            }
        });
        // bind the slider bidirectionally to an editable text field restricted to the slider range.
        final IntField intField = new IntField(0, 200, 50);
        intField.setTooltip(new Tooltip(EDIT_FIELD_TOOLTIP));
        intField.setStyle("-fx-background-color: white; -fx-font-size: 20");
        intField.valueProperty().bindBidirectional(slider.valueProperty());
        intField.setPrefWidth(50);


        // layout the scene.
        HBox controls = new HBox(10);
        controls.getChildren().addAll(intField, slider);
        controls.setStyle(" -fx-alignment: baseline-left;");

        offsetMenuItem.setContent(controls);
        offsetMenu.getItems().add(offsetMenuItem);
        items.addAll(offsetMenu);

        Menu alarmMenu = new Menu("Alarm");
        CustomMenuItem alarmMenuItem = CustomMenuItemBuilder.create()
                .build();
        // create a slider.
        final Slider alarmSlider = new Slider(0, 10, 1);
        alarmSlider.setTooltip(new Tooltip(REAL_SLIDER_TOOLTIP));
        alarmSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                depthAlarm = new_val.floatValue();
                alarmOn = true;
                attibutes.put("depthAlarm", (new Float(depthAlarm).toString()));
                lcd.setThreshold(depthAlarm);
                fireSounderEvent(new SounderEvent(this, attibutes));
                alarmButton.setGraphic(new ImageView(new Image(rootDir + IMAGES + ALARM_ON + SUFFIX)));
                alarmButton.setTooltip(new Tooltip(ALARM_ON_TOOLTIP + " " + (int)depthAlarm + "m"));
                if (!getChildren().contains(alarmButton)) {
                    getChildren().add(alarmButton);
                }
            }
        });
        // bind the slider bidirectionally to an editable text field restricted to the slider range.
        final IntField alarmIntField = new IntField(0, 10, 1);
        alarmIntField.setTooltip(new Tooltip(EDIT_FIELD_TOOLTIP));
        alarmIntField.setStyle("-fx-background-color: white; -fx-font-size: 20");
        alarmIntField.valueProperty().bindBidirectional(alarmSlider.valueProperty());
        alarmIntField.setPrefWidth(50);

        Button roundAlarmButton = new Button("Stop");
        roundAlarmButton.setStyle("-fx-background-color: red; -fx-font-size: 20");
        roundAlarmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                alarmOn = false;
                alarmSound.stop();
                getChildren().remove(alarmButton);
            }
        });
        HBox alarmControls = new HBox(10);
        alarmControls.getChildren().addAll(alarmIntField, alarmSlider, roundAlarmButton);
        alarmControls.setStyle(" -fx-alignment: baseline-left;");
        alarmMenuItem.setContent(alarmControls);
        alarmMenu.getItems().add(alarmMenuItem);
        items.addAll(alarmMenu);


        Menu depthUnit = new Menu("Depth unit");
        CheckMenuItem[] depthUnits = new CheckMenuItem[3];
        depthUnits[0] = CheckMenuItemBuilder.create().text(UNIT_0).build();
        depthUnits[1] = CheckMenuItemBuilder.create().text(UNIT_1).build();
        depthUnits[2] = CheckMenuItemBuilder.create().text(UNIT_2).build();
        for (int i = 0; i < depthRanges.length; i++) {
            depthUnits[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent evt) {
                    String tmp = ((MenuItem) evt.getSource()).getText();
                    if (tmp != null && !tmp.equals("")) {
                        switch (tmp) {
                            case UNIT_0:
                                unitConvert = 1.f;
                                root.getChildren().remove(lcd);
                                lcd = lcd0;
                                root.getChildren().add(lcd);
                                break;
                            case UNIT_1:
                                unitConvert = METER_TO_FATHOM;
                                root.getChildren().remove(lcd);
                                lcd = lcd1;
                                root.getChildren().add(lcd);
                                break;
                            case UNIT_2:
                                unitConvert = METER_TO_FOOT;
                                root.getChildren().remove(lcd);
                                lcd = lcd2;
                                root.getChildren().add(lcd);
                                break;
                        }
                        attibutes.put("unitConcert", (new Float(unitConvert).toString()));
                        fireSounderEvent(new SounderEvent(this, attibutes));
                    }
                }
            });
        }
        depthUnit.getItems().addAll(depthUnits);
        items.addAll(depthUnit);
    }

    @Override
    public void setDepth(final float data) {
        float alarmThreshold = new Float(attibutes.get("depthAlarm"));
        alarmThreshold *= unitConvert;
        this.depth = data + offset;
        depth *= unitConvert;
        if (alarmThreshold >= depth) {
            if (!alarmSound.isPlaying() && alarmOn == true) {
                alarmButton.setGraphic(new ImageView(new Image(rootDir + IMAGES + ALARM_OFF + SUFFIX)));
                alarmButton.setTooltip(new Tooltip(ALARM_OFF_TOOLTIP));
                alarmSound.play();
            }
        }
        lcd.setValue(depth);
        Line line;
        scale = VIEWPORT_Y_RANGE / range;
        depth = VIEWPORT_Y_MIN + scale * depth;
        if (depth > VIEWPORT_Y_MAX) {
            depth = VIEWPORT_Y_MAX;
        }
        line = new Line(VIEWPORT_X_MIN + dec, depth,
                VIEWPORT_X_MIN + dec, VIEWPORT_Y_MAX);
        line.setFill(null);
        if (depth == VIEWPORT_Y_MAX) {
            line.setStroke(Color.rgb(0, 0, 0, 0));
        } else {
            line.setStroke(Color.BLACK);
        }
        line.setStrokeWidth(2);
        lines.add(line);
        if (dec < VIEWPORT_X_MAX) { // tant que dans le viewport 
            root.getChildren().add(line);
            dec += 2;
        } else {
            root.getChildren().removeAll(lines); // extremite droite du viewport atteinte
            lines.remove(0);
            for (Line l : lines) {
                l.setLayoutX(l.getLayoutX() - 2);// decalage de toutes les lignes vers la gauche
            }
            root.getChildren().addAll(lines);
        }
    }
}
