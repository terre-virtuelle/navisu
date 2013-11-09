/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.controller;

import java.util.ArrayList;
import bzh.terrevirtuelle.navisu.instruments.widget.controller.WidgetsGlassPaneController;
import java.util.List;
import javafx.scene.paint.Color;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.events.QuitEventListener;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.events.DayNightEvent;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.events.DayNightEventListener;
import bzh.terrevirtuelle.navisu.instruments.instrument.controller.events.QuitEvent;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;
import bzh.terrevirtuelle.navisu.instruments.widget.model.Widget;

/**
 *
 * @author Serge Morvan
 */
public class InstrumentsGlassPaneController
        extends WidgetsGlassPaneController
        implements DayNightEventListener, QuitEventListener {

    protected String rootDir = null;
    protected final String STYLE = "/resources/style/";
    protected final String NIGHT_CSS = "instruments_night.css";
    protected final String DAY_CSS = "instruments.css";
    protected String style = DAY_CSS;
    protected boolean day = true;
    protected List<Display> displays;

    public InstrumentsGlassPaneController(int height, int width) {
        super(height, width);
        rootDir = getClass().getPackage().getName().replace(".", "/");
        displays = new ArrayList<>();
    }

    @Override
    public void update(DayNightEvent event) {
        day = event.isDay();
        if (day == true) {
            widgetsGlassPane.getScene().setFill(Color.TRANSPARENT);
            style = DAY_CSS;
        } else {
            widgetsGlassPane.getScene().setFill(new Color(0.16078, 0.95294, 0.035294, 0.4));
            style = NIGHT_CSS;
        }
        widgetsGlassPane.getScene().getStylesheets().add(rootDir + STYLE + style);
    }

    @Override
    public void update(QuitEvent event) {
        Widget source = (Widget) event.getSource();
        widgetsGlassPane.remove(source);
    }

    public void addInstrument(Display display) {
        super.add(display);
        display.setDay(day);
        display.addQuitEventListener(this);
        display.addDayNightEventListener(this);
        displays.add(display);
        for (Display d : displays) {
            d.addDayNightEventListener(display);
            display.addDayNightEventListener(d);
        }
        display.setStyle(rootDir + STYLE + style);
        display.fireDayNightEvent(new DayNightEvent(this, day));
    }

    public void removeInstrument(Display display) {
        super.remove(display);
        for (Display d : displays) {
            d.removeDayNightEventListener(display);
        }
    }

    public boolean isDay() {
        return day;
    }
}
