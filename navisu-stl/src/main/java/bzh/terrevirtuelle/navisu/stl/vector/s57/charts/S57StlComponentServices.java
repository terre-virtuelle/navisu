package bzh.terrevirtuelle.navisu.stl.vector.s57.charts;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 25/2/2017
 */
public interface S57StlComponentServices
        extends ComponentService {

    Driver getDriver();

    void openChart(String file);

}
