/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.sounder;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Line;
import jfxtras.labs.scene.control.gauge.Gauge;
import jfxtras.labs.scene.control.gauge.Lcd;
import jfxtras.labs.scene.control.gauge.LcdDesign;
import jfxtras.labs.scene.control.gauge.StyleModel;
import jfxtras.labs.scene.control.gauge.StyleModelBuilder;
import org.navisu.instrument.controller.events.SounderEvent;
import org.navisu.instrument.controller.events.SounderEventListener;
import org.navisu.instrument.model.Display;
import org.navisu.instrument.model.InstrumentPane;

/**
 *
 * @author Serge Morvan
 */
public abstract class SounderPane
        extends InstrumentPane {

    protected Lcd lcd;
    protected Lcd lcd0;
    protected Lcd lcd1;
    protected Lcd lcd2;
    protected List<Line> lines;
    protected int dec = 0;
    protected float offset = 0;
    protected float depth = 0;
    protected float depthAlarm = 0;
    protected float scale = 0.0f;
    protected float range = 30.f;
    protected Label rangeLabel;
    protected TextField offsetTF;
    protected static final StyleModel STYLE_MODEL_1 = StyleModelBuilder.create()
            .lcdDesign(LcdDesign.GREEN_DARKGREEN)
            .lcdValueFont(Gauge.LcdFont.LCD)
            .lcdUnitStringVisible(true)
            .lcdThresholdVisible(true)
            .build();
    protected static final StyleModel STYLE_MODEL_2 = StyleModelBuilder.create()
            .lcdDesign(LcdDesign.BLACK_RED)
            .lcdValueFont(Gauge.LcdFont.LCD)
            .lcdUnitStringVisible(true)
            .lcdThresholdVisible(true)
            .build();
    protected static final StyleModel STYLE_MODEL_3 = StyleModelBuilder.create()
            .lcdDesign(LcdDesign.AMBER)
            .lcdValueFont(Gauge.LcdFont.LCD)
            .lcdUnitStringVisible(true)
            .lcdThresholdVisible(true)
            .build();
    protected static final String REAL_SLIDER_TOOLTIP =
            "A slider ranging in centimeters whose values are bound to the fields to the left.";
    protected static final String VIRTUAL_SLIDER_TOOLTIP =
            "A slider ranging in meters whose values are bound to the fields to the left.";
    protected static final String EDIT_FIELD_TOOLTIP =
            "Click on me to edit the slider's value,\n"
            + "but you will only be able to enter numeric values within the slider range.\n"
            + "The slider will be updated as you type\n"
            + "and there is no need to commit the edit with an enter key.";
    protected List<SounderEventListener> listeners;
    protected final int VIEWPORT_X_MIN = 58;
    protected final int VIEWPORT_X_MAX = 184;
    protected final int VIEWPORT_Y_MIN = 158;
    protected final int VIEWPORT_Y_MAX = 226;
    protected final int VIEWPORT_Y_RANGE = VIEWPORT_Y_MAX - VIEWPORT_Y_MIN;
    protected boolean sailBoat = true;
    protected Sounder sounder;
    protected AudioClip alarmSound;
    protected boolean alarmOn = false;
    protected final String UNIT_0 = "Meters";
    protected final String UNIT_1 = "Fathoms";
    protected final String UNIT_2 = "Feet";
    protected final float METER_TO_FATHOM = 0.546806649f;
    protected final float METER_TO_FOOT = 3.2808f;
    protected float unitConvert = 1;
    protected Button alarmButton;
    protected final String ALARM_ID = "alarm";
    protected final String ALARM_ON = "alarmOn";
    protected final String ALARM_OFF = "alarmOff";
    protected final String SUFFIX = ".gif";
    protected String ALARM_ON_TOOLTIP = "Alarm is On";
    protected String ALARM_OFF_TOOLTIP = "Push for alarm Off";

    public SounderPane(Display display) {
        super(display);
        sounder = (Sounder) display;
        attibutes.put("type", "sounder");
        attibutes.put("day", "true");
        attibutes.put("boatType", "sail");
        attibutes.put("range", "30");
        attibutes.put("offset", "0");
        attibutes.put("depthAlarm", "0");
        listeners = new ArrayList<>();
        rootDir = getClass().getPackage().getName().replace(".", "/");
        alarmButton = ButtonBuilder.create()
                .id(ALARM_ID)
                .layoutX(LAYOUT_X - 92)
                .layoutY(9)
                .minWidth(Control.USE_PREF_SIZE)
                .prefWidth(42.0)
                .minHeight(Control.USE_PREF_SIZE)
                .prefHeight(10.0)
                .graphic(new ImageView(new Image(rootDir + IMAGES + ALARM_ON + SUFFIX)))
                .build();
        alarmButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                alarmOn = false;
                alarmSound.stop();
                getChildren().remove(alarmButton);
            }
        });
    }

    public abstract void initMenu();

    public abstract void setDepth(final float data);

    public void addEventListener(SounderEventListener listener) {
        listeners.add(listener);
    }

    public void removeEventListener(SounderEventListener listener) {
        listeners.remove(listener);
    }

    protected void fireSounderEvent(SounderEvent evt) {
        for (SounderEventListener s : listeners) {
            s.update(evt);
        }
    }

    public float getDepth() {
        return depth;
    }

    public float getOffset() {
        return offset;
    }

    public void setOffset(float offset) {
        this.offset = offset;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public float getDepthAlarm() {
        return depthAlarm;
    }

    public void setDepthAlarm(float depthAlarm) {
        this.depthAlarm = depthAlarm;
    }
}
