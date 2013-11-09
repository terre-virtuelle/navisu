/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.controller.events;

import java.util.EventObject;

/**
 *
 * @author Serge Morvan
 */
public class DayNightEvent
        extends EventObject {

    boolean day;

    public DayNightEvent(Object o, boolean isDay) {
        super(o);
        day = isDay;
    }

    public boolean isDay() {
        return day;
    }
    
}
