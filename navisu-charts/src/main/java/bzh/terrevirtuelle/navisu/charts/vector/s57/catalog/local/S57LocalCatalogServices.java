package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog.local;

import bzh.terrevirtuelle.navisu.app.drivers.directorydriver.DirectoryDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface S57LocalCatalogServices
        extends ComponentService {

    DirectoryDriver getDriver();

    void openChart(String file);

}
