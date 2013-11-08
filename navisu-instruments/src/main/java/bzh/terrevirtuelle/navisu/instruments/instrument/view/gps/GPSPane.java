 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.instrument.view.gps;

//import com.sun.prism.paint.Color;
import org.navisu.instrument.model.Display;
import org.navisu.instrument.model.InstrumentPane;

/**
 *
 * @author Serge Morvan
 */
public class GPSPane
        extends InstrumentPane {

    public GPSPane(Display display) {
        super(display);
        rootDir = getClass().getPackage().getName().replace(".", "/");
        setId("gpsPane");
    }
}
