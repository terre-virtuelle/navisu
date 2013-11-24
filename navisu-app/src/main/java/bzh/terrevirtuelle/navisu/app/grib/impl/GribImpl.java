package bzh.terrevirtuelle.navisu.app.grib.impl;

import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.grib.Grib;
import bzh.terrevirtuelle.navisu.app.grib.GribConstants;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import org.capcaval.c3.component.ComponentState;
import ucar.ma2.Array;
import ucar.nc2.dt.grid.GeoGrid;
import ucar.nc2.dt.grid.GridDataset;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * User: jordan
 * Date: 23/11/2013
 */
public class GribImpl implements Grib, GribServices, ComponentState {

    protected Logger LOGGER = Logger.getLogger(GribImpl.class.getName());

    protected Driver driver;

    protected GeoGrid uGrid;

    protected GeoGrid vGrid;

    protected GeoGrid pressionGrid;

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
                    loadFile(files[0]); //TODO load all files !
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
        GridDataset gd = null;
        try {
            gd = GridDataset.open(path);
        } catch (IOException e) {
            e.printStackTrace(); //TODO Make custom exception for drivers
        }

        if(null == gd.findGridByName(GribConstants.PRESSURE_GRID_NAME) ||
                null == gd.findGridByName(GribConstants.U_GRID_NAME) ||
                null == gd.findGridByName(GribConstants.V_GRID_NAME)) {

            return; //TODO Make custom Exception here
        } else {

            this.pressionGrid = gd.findGridByName(GribConstants.PRESSURE_GRID_NAME);
            this.uGrid = gd.findGridByName(GribConstants.U_GRID_NAME);
            this.vGrid = gd.findGridByName(GribConstants.V_GRID_NAME);
        }
    }

    @Override
    public double[] getVelocityInMPSAtPoint(double latitude, double longitude) {

        double[] velocity = new double[2];

        Array uArray;
        Array vArray;

        try {
            uArray = this.uGrid.readYXData(0,0);
            vArray = this.vGrid.readYXData(0,0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] uVelocityIndex = this.uGrid.getCoordinateSystem().findXYindexFromLatLon(latitude, longitude, null);
        int[] vVelocityIndex = this.vGrid.getCoordinateSystem().findXYindexFromCoord(latitude, longitude, null);

        return null;
    }

    @Override
    public double getPressionAtPoint(double latitude, double longitude) {
        //TODO add Time
        return 0;
    }
}
