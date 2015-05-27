package bzh.terrevirtuelle.navisu.netcdf.grib;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.view.GribLayer;
import org.capcaval.c3.component.ComponentService;

/**
 * User: jordan
 * Date: 23/11/2013
 */
public interface GribServices extends ComponentService {

    Driver getDriver();

    void loadFile(String path);

    double[] getVelocityInMPSAtPoint(double latitude, double longitude);

    double getPressionAtPoint(double latitude, double longitude);

    double getLatitudeDimension();

    double getLongitudeDimension();

    double getTimeDimension();

    GribLayer getGribLayer();
}
