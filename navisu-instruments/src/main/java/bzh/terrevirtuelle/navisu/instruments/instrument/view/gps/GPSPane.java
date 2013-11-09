 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.gps;

//import com.sun.prism.paint.Color;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.Display;
import bzh.terrevirtuelle.navisu.instruments.instrument.model.InstrumentPane;

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
