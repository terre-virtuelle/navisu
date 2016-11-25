package bzh.terrevirtuelle.navisu.netcdf.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.netcdf.NetCDF;
import bzh.terrevirtuelle.navisu.netcdf.NetCDFServices;
import bzh.terrevirtuelle.navisu.netcdf.meteo.controller.WindVectorFieldController;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;
import bzh.terrevirtuelle.navisu.netcdf.common.controller.NetCDFInfoController;
import bzh.terrevirtuelle.navisu.netcdf.impl.controller.NetCDFController;
import bzh.terrevirtuelle.navisu.netcdf.meteo.controller.PressureScalarFieldController;

/**
 * User: serge Date: 23/11/2013
 */
public class NetCDFImpl
        implements NetCDF, NetCDFServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;
    @UsedService
    LayersManagerServices layersManagerServices;
    @UsedService
    GuiAgentServices guiAgentServices;

    protected static final Logger LOGGER = Logger.getLogger(NetCDFImpl.class.getName());
    protected static int LAYER_INDEX = 0;
    protected Driver driver;
    protected NetCDFController netCDFController;
    protected String category;

    @Override
    public void componentInitiated() {

        this.driver = new Driver() {

            private static final String NAME = "NetCDF";

            private static final String EXTENSION_0 = ".grb";
            private static final String EXTENSION_1 = ".grb.bz2";
            private static final String EXTENSION_2 = ".grb.Z";
            private static final String EXTENSION_3 = ".grb.zip";
            private static final String EXTENSION_4 = ".grb.gzip";
            private static final String EXTENSION_5 = ".grb.gz";
            private static final String EXTENSION_6 = ".grib2";
            private static final String EXTENSION_7 = ".nc";
            private static final String EXTENSION_8 = ".nc.gz";
            private static final String EXTENSION_9 = ".bz2";
            private static final String EXTENSION_10 = ".mnt";
            private static final String EXTENSION_11 = ".mnt.zip";
            private static final String EXTENSION_12 = ".dl.zip";

            @Override
            public boolean canOpen(String category, String file) {
                NetCDFImpl.this.category = category;
                boolean canOpen = false;
                if (file.toLowerCase().endsWith(EXTENSION_0)
                        || file.toLowerCase().endsWith(EXTENSION_1)
                        || file.toLowerCase().endsWith(EXTENSION_2)
                        || file.toLowerCase().endsWith(EXTENSION_3)
                        || file.toLowerCase().endsWith(EXTENSION_4)
                        || file.toLowerCase().endsWith(EXTENSION_5)
                        || file.toLowerCase().endsWith(EXTENSION_6)
                        || file.toLowerCase().endsWith(EXTENSION_7)
                        || file.toLowerCase().endsWith(EXTENSION_8)
                        || file.toLowerCase().endsWith(EXTENSION_9)
                        || file.toLowerCase().endsWith(EXTENSION_10)
                        || file.toLowerCase().endsWith(EXTENSION_11)
                        || file.toLowerCase().endsWith(EXTENSION_12)) {
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
                    "*" + EXTENSION_7,
                    "*" + EXTENSION_8,
                    "*" + EXTENSION_9,
                    "*" + EXTENSION_10,
                    "*" + EXTENSION_11
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
        switch (NetCDFImpl.this.category) {
            case "Wind":
                netCDFController = new WindVectorFieldController(layersManagerServices, LAYER_INDEX, guiAgentServices, path);
                break;
            case "Pressure":
                netCDFController = new PressureScalarFieldController(layersManagerServices, LAYER_INDEX, guiAgentServices, path);
                break;
            case "NetCdfInfo":
                netCDFController = new NetCDFInfoController(path);
                break;
            case "Currents":
                netCDFController = new NetCDFInfoController(path);
                break;
            case "Waves":
                netCDFController = new NetCDFInfoController(path);
                break;
            case "Bathy":
                netCDFController = new NetCDFInfoController(path);
                break;
        }
        LAYER_INDEX++;
    }

    @Override
    public int getLatitudeDimension() {
        //  return netCDFController.getLatitudeDimension();
        return 0;
    }

    @Override
    public int getLongitudeDimension() {
        // return meteoNetCdfController.getLongitudeDimension();
        return 0;
    }

    @Override
    public int getTimeDimension() {
        //return meteoNetCdfController.getTimeDimension();
        return 0;
    }

    @Override
    public RenderableLayer getLayer() {
        // return meteoNetCdfController.getLayer();
        return null;
    }

    public double getMaxLatitude() {
        //return meteoNetCdfController.getMaxLatitude();
        return 0;
    }

    public double getMaxLongitude() {
        // return meteoNetCdfController.getMaxLongitude();
        return 0;
    }

    public double getMinLatitude() {
        // return meteoNetCdfController.getMinLatitude();
        return 0;
    }

    public double getMinLongitude() {
        //  return meteoNetCdfController.getMinLongitude();
        return 0;
    }
}
