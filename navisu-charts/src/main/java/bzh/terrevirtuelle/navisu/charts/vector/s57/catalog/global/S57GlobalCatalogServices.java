package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.global;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface S57GlobalCatalogServices
        extends ComponentService {

    Driver getDriver();

    void openFile(String file);

    void loadFile(String filename);
    void load(String filename);
}
