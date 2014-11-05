package bzh.terrevirtuelle.navisu.charts.vector.s57.catalog;

import bzh.terrevirtuelle.navisu.app.ddriver.DDriver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public interface S57CatalogServices
        extends ComponentService {

    DDriver getDriver();

    void openChart(String file);

}
