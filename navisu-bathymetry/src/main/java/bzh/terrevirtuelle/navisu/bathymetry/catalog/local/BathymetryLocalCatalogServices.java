package bzh.terrevirtuelle.navisu.bathymetry.catalog.local;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 31/01/2015 15:49
 */
public interface BathymetryLocalCatalogServices
        extends ComponentService {

    Driver getDriver();

    void openFile(String file);

}
