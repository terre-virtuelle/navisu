 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.view.compass;

import bzh.terrevirtuelle.navisu.instruments.model.Display;
import bzh.terrevirtuelle.navisu.instruments.model.InstrumentPane;

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
