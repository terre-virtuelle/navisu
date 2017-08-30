package bzh.terrevirtuelle.navisu.stl.bathy;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 29/8/2017
 */
public interface BathyStlComponentServices
        extends ComponentService {

    InstrumentDriver getDriver();

    

}
