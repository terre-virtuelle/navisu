package bzh.terrevirtuelle.navisu.netcdf.grib.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.netcdf.grib.Grib;
import bzh.terrevirtuelle.navisu.netcdf.grib.GribServices;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller.AnalyticSurfaceController;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.controller.GribController;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.view.GribLayer;
import gov.nasa.worldwind.layers.Layer;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * User: jordan Date: 23/11/2013
 */
public class GribImpl implements Grib, GribServices, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    protected static final Logger LOGGER = Logger.getLogger(GribImpl.class.getName());

    protected Driver driver;

    protected GribController gribController;
    protected GribModel gribModel;
    protected AnalyticSurfaceController analyticSurfaceController;
    protected LayerManager<Layer> layerLayerManager;

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

            @Override
            public boolean canOpen(String file) {
                boolean canOpen = false;
                if (file.toLowerCase().endsWith(EXTENSION_0)
                        || file.toLowerCase().endsWith(EXTENSION_1)
                        || file.toLowerCase().endsWith(EXTENSION_2)
                        || file.toLowerCase().endsWith(EXTENSION_3)
                        || file.toLowerCase().endsWith(EXTENSION_4)
                        || file.toLowerCase().endsWith(EXTENSION_5)
                        ) {
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
                    "*" + EXTENSION_5
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
        this.gribController = new GribController(path);
        
        this.gribModel = this.gribController.getModel();
        
        analyticSurfaceController
                = new AnalyticSurfaceController(gribController.getModel().getVelocityField(),
                        getLongitudeDimension(), getLatitudeDimension(),
                        getMinLatitude(), getMaxLatitude(),
                        getMinLongitude(), getMaxLongitude(),
                        0.0, 15.0,
                        0.6,
                        "Wind", "Kt");

        // LOGGER.info(this.gribController.getModel().toString());
        //  LOGGER.info("######################################## CREATE LAYER #############################################");
        //  this.layerTreeServices.addGeoLayer("Grib", GeoLayer.impl.newWorldWindGeoLayer(this.gribController.getLayer()));
        System.out.println("loadFile");
        this.layerLayerManager = (LayerManager<Layer>) ((GeoView) this.geoViewServices.getDisplayService()).getLayerManager();
        this.layerLayerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(this.gribController.getLayer()));
        this.layerLayerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(this.analyticSurfaceController.getLayer()));
   
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

    @Override
    public int getLatitudeDimension() {

        return this.gribModel.getLatitudeDimension();
    }

    @Override
    public int getLongitudeDimension() {
        return this.gribModel.getLongitudeDimension();
    }

    @Override
    public int getTimeDimension() {
        return this.gribModel.getTimeDimension();
    }

    @Override
    public GribLayer getGribLayer() {
        return this.gribController.getLayer();
    }

    public double getMaxLatitude() {
        return this.gribModel.getMaxLatitude();
    }

    public double getMaxLongitude() {
        return this.gribModel.getMaxLongitude();
    }

    public double getMinLatitude() {
        return this.gribModel.getMinLatitude();
    }

    public double getMinLongitude() {
        return gribModel.getMinLongitude();
    }
}
