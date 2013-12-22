package bzh.terrevirtuelle.navisu.app.grib.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.app.grib.Grib;
import bzh.terrevirtuelle.navisu.app.grib.GribServices;
import bzh.terrevirtuelle.navisu.app.grib.impl.controller.GribController;
import bzh.terrevirtuelle.navisu.app.grib.impl.view.GribLayer;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import gov.nasa.worldwind.layers.Layer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * User: jordan
 * Date: 23/11/2013
 */
public class GribImpl implements Grib, GribServices, ComponentState {

    @UsedService GeoViewServices geoViewServices;

    protected Logger LOGGER = Logger.getLogger(GribImpl.class.getName());

    protected Driver driver;

    protected GribController gribController;

    protected LayerManager<Layer> layerLayerManager;

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
            public void open(ProgressHandle pHandle, String... files) {
                for(String file : files) {
                    LOGGER.info("Opening " + file + " ...");
                    loadFile(file); //Todo Make stuff for all files
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
        this.gribController = new GribController(path);

        LOGGER.info(this.gribController.getModel().toString());

        LOGGER.info("######################################## CREATE LAYER #############################################");
        //this.layerTreeServices.addGeoLayer("Grib", GeoLayer.impl.newWorldWindGeoLayer(this.gribController.getLayer()));
        this.layerLayerManager = (LayerManager<Layer>) ((GeoView)this.geoViewServices.getDisplayService()).getLayerManager();
        this.layerLayerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(this.gribController.getLayer()));
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
    public double getLatitudeDimension() {
        return this.gribController.getModel().getLatitudeDimension();
    }

    @Override
    public double getLongitudeDimension() {
        return this.gribController.getModel().getLongitudeDimension();
    }

    @Override
    public double getTimeDimension() {
        return this.gribController.getModel().getTimeDimension();
    }

    @Override
    public GribLayer getGribLayer() {
        return this.gribController.getLayer();
    }
}
