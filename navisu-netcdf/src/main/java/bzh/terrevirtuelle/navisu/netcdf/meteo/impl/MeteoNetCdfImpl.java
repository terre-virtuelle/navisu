package bzh.terrevirtuelle.navisu.netcdf.meteo.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.netcdf.meteo.MeteoNetCdf;
import bzh.terrevirtuelle.navisu.netcdf.meteo.MeteoNetCdfServices;
import bzh.terrevirtuelle.navisu.netcdf.meteo.impl.controller.MeteoNetCdfController;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * User: jordan Date: 23/11/2013
 */
public class MeteoNetCdfImpl
        implements MeteoNetCdf, MeteoNetCdfServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    protected static final Logger LOGGER = Logger.getLogger(MeteoNetCdfImpl.class.getName());

    protected Driver driver;
    protected MeteoNetCdfController meteoNetCdfController;

    @Override
    public void componentInitiated() {

        this.driver = new Driver() {

            private static final String NAME = "Grib";

            private static final String EXTENSION_0 = ".grb";
            private static final String EXTENSION_1 = ".grb.bz2";
            private static final String EXTENSION_2 = ".grb.Z";
            private static final String EXTENSION_3 = ".grb.zip";
            private static final String EXTENSION_4 = ".grb.gzip";
            private static final String EXTENSION_5 = ".grb.gz";
            private static final String EXTENSION_6 = ".grib2";
            private static final String EXTENSION_7 = ".nc";
            private static final String EXTENSION_8 = ".bz2";

            @Override
            public boolean canOpen(String file) {
                boolean canOpen = false;
                if (file.toLowerCase().endsWith(EXTENSION_0)
                        || file.toLowerCase().endsWith(EXTENSION_1)
                        || file.toLowerCase().endsWith(EXTENSION_2)
                        || file.toLowerCase().endsWith(EXTENSION_3)
                        || file.toLowerCase().endsWith(EXTENSION_4)
                        || file.toLowerCase().endsWith(EXTENSION_5)
                        || file.toLowerCase().endsWith(EXTENSION_6)
                        || file.toLowerCase().endsWith(EXTENSION_7)
                        || file.toLowerCase().endsWith(EXTENSION_8)) {
                    canOpen = true;
                }
                return canOpen;
            }

            @Override
            public void open(ProgressHandle pHandle, String... files) {
                for (String file : files) {
                    LOGGER.log(Level.INFO, "Opening {0} ...", file);
                    loadFile(file); //Todo Make stuff for all files
                }
            }

            @Override
            public String getName() {
                return NAME;
            }

            @Override
            public String[] getExtensions() {
                return new String[]{"*" + EXTENSION_0,
                    "*" + EXTENSION_1,
                    "*" + EXTENSION_2,
                    "*" + EXTENSION_3,
                    "*" + EXTENSION_4,
                    "*" + EXTENSION_5,
                    "*" + EXTENSION_6,
                    "*" + EXTENSION_7

                };
            }
        };
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public Driver getDriver() {
        return this.driver;
    }

    @Override
    public void loadFile(String path) {
        meteoNetCdfController = new MeteoNetCdfController(layersManagerServices, path);
    }

    @Override
    public int getLatitudeDimension() {
        return meteoNetCdfController.getLatitudeDimension();
    }

    @Override
    public int getLongitudeDimension() {
        return meteoNetCdfController.getLongitudeDimension();
    }

    @Override
    public int getTimeDimension() {
        return meteoNetCdfController.getTimeDimension();
    }

    @Override
    public RenderableLayer getLayer() {
        return meteoNetCdfController.getLayer();
    }

    public double getMaxLatitude() {
        return meteoNetCdfController.getMaxLatitude();
    }

    public double getMaxLongitude() {
        return meteoNetCdfController.getMaxLongitude();
    }

    public double getMinLatitude() {
        return meteoNetCdfController.getMinLatitude();
    }

    public double getMinLongitude() {
        return meteoNetCdfController.getMinLongitude();
    }
}
