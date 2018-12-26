package bzh.terrevirtuelle.navisu.osm;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author sbodmer
 * @date 24/12/2018 12:49
 */
public interface OsmComponentServices
        extends ComponentService {

   InstrumentDriver getDriver();


}
