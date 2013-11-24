package bzh.terrevirtuelle.navisu.app.grib.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.grib.Grib;
import bzh.terrevirtuelle.navisu.app.grib.GribConstants;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import org.capcaval.c3.component.ComponentState;
import ucar.nc2.dt.GridDatatype;
import ucar.nc2.dt.grid.GeoGrid;
import ucar.nc2.dt.grid.GridDataset;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: jordan
 * Date: 23/11/2013
 */
public class GribImpl implements Grib, GribServices, ComponentState {

    protected Logger LOGGER = Logger.getLogger(GribImpl.class.getName());

    protected Driver driver;

    @Override
    public void componentInitiated() {
        this.driver = new Driver() {

            private static final String NAME = "Grib";
            private static final String EXTENSION = ".grb";

            @Override
            public boolean canOpen(String file) {
                return file.toLowerCase().endsWith(EXTENSION);
            }

            @Override
            public void open(String... files) {
                for(String file : files) {
                    LOGGER.info("Opening " + file + " ...");
                }
            }

            @Override
            public String getName() {
                return NAME;
            }

            @Override
            public String[] getExtensions() {
                return new String[] { "*" + EXTENSION };
            }
        };
    }

    @Override
    public void componentStarted() {}

    @Override
    public void componentStopped() {}

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public void loadFile(String path) {

    }

    @Override
    public double[] getVelocityInMPSAtPoint(double latitude, double longitude) {

        return null;
    }

    @Override
    public double getPressionAtPoint(double latitude, double longitude) {
        //TODO add Time
        return 0;
    }
}
