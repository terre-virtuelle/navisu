package bzh.terrevirtuelle.navisu.charts.vector.s57.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;

import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.ChartS57;
import bzh.terrevirtuelle.navisu.charts.vector.s57.ChartS57Services;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.controller.ChartS57Controller;
import bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view.ChartS57Layer;
import bzh.terrevirtuelle.navisu.core.view.geoview.GeoView;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.LayerManager;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Edge;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Point2D;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.Spatial;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.VectorUsage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Coastline;
import gov.nasa.worldwind.layers.Layer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Impl implements ChartS57, ChartS57Services, ComponentState {

    @UsedService
    GeoViewServices geoViewServices;

    protected static final Logger LOGGER = Logger.getLogger(ChartS57Impl.class.getName());

    protected Driver driver;

    protected ChartS57Controller chartS57Controller;

    protected LayerManager<Layer> layerLayerManager;

    @Override
    public void componentInitiated() {

        this.driver = new Driver() {

            private static final String NAME = "S57";
            private static final String EXTENSION = ".000";

            @Override
            public boolean canOpen(String file) {
                return file.toLowerCase().endsWith(EXTENSION);
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
                return new String[]{"*" + EXTENSION};
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
        this.chartS57Controller = new ChartS57Controller(path);

        LOGGER.info(this.chartS57Controller.getModel().toString());

        LOGGER.info("######################################## CREATE LAYER #############################################");
        // this.layerTreeServices.addGeoLayer("Grib", GeoLayer.impl.newWorldWindGeoLayer(this.gribController.getLayer()));
        this.layerLayerManager = (LayerManager<Layer>) ((GeoView) this.geoViewServices.getDisplayService()).getLayerManager();
        this.layerLayerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(this.chartS57Controller.getChartS57Layer()));
    }

    @Override
    public ChartS57Layer getChartS57Layer() {
        return this.chartS57Controller.getChartS57Layer();
    }

    @Override
    public Set<Coastline> getCoastlines() {
        Set<Coastline> coastlines = new HashSet<>();
        S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
            if (obj.getClass().getSimpleName().equals("Coastline")) {
                Coastline c = (Coastline) obj;
                coastlines.add(c);
            }
        });
        return coastlines;
    }
}
