package bzh.terrevirtuelle.navisu.charts.raster.geotiff;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author Serge
 * @date 29/09/2014 12:49
 */
public interface GeoTiffChartServices 
        extends ComponentService {

    Driver getDriver();

    void openChart(String file);
}
