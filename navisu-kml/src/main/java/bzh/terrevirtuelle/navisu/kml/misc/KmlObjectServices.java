package bzh.terrevirtuelle.navisu.kml.misc;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface KmlObjectServices
        extends ComponentService {

    Driver getDriver();

    void openFile(String file);

    
}
