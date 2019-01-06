package bzh.terrevirtuelle.navisu.buildings.osmb;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * @date Jan 3, 2019 10:09:49 AM
 * @author Serge Morvan
 */
public interface OsmbComponentServices
        extends ComponentService {

   InstrumentDriver getDriver();


}
