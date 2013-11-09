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
public class QuitEvent extends EventObject {

    boolean quit;

    public QuitEvent(Object o, boolean isQuit) {
        super(o);
        quit = isQuit;
    }

    public boolean isQuit() {
        return quit;
    }
}
