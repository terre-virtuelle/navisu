package bzh.terrevirtuelle.navisu.buildings.osm;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 * 
 * @date Jan 3, 2019 10:09:49 AM
 * @author Serge Morvan
 */
public interface OsmComponentServices
        extends ComponentService {

   InstrumentDriver getDriver();


}
