 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.compass;

import org.navisu.instrument.model.Display;
import org.navisu.instrument.model.InstrumentPane;

/**
 *
 * @author Serge Morvan
 */
public class CompassPane
        extends InstrumentPane {

    public CompassPane(Display display) {
        super(display);
        foregroundFileName = "verre.png";
        rootDir = getClass().getPackage().getName().replace(".", "/");
    }
}
