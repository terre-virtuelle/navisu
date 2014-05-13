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
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.S57Model;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Coastline;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.DepthArea;
import gov.nasa.worldwind.layers.Layer;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.util.logging.Logger;

/**
 * @author Serge Morvan
 * @date 11/05/2014 12:49
 */
public class ChartS57Impl
        implements ChartS57, ChartS57Services, ComponentState {

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
                    addCoastlines();
                    addDepthAreas();
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
        System.out.println("chartLayer " + chartS57Controller.getChartS57Layer().getName());
        // this.layerTreeServices.addGeoLayer("Grib", GeoLayer.impl.newWorldWindGeoLayer(this.gribController.getLayer()));
        this.layerLayerManager = (LayerManager<Layer>) ((GeoView) this.geoViewServices.getDisplayService()).getLayerManager();
        this.layerLayerManager.insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(this.chartS57Controller.getChartS57Layer()));
    }

    @Override
    public ChartS57Layer getChartS57Layer() {
        return this.chartS57Controller.getChartS57Layer();
    }

    @Override
    public void addCoastlines() {
        Set<Coastline> objects = new HashSet<>();
        S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
            if (obj.getClass().getSimpleName().equals("Coastline")) {
                Coastline c = (Coastline) obj;
                objects.add(c);
            }
        });
        chartS57Controller.addCoastlines(objects);
    }

    @Override
    public void removeCoastlines() {
        chartS57Controller.removeCoastlines();
    }

    @Override
    public void addDepthAreas() {
        Set<DepthArea> objects = new HashSet<>();
        S57Model.getFeatureObjects().values().stream().forEach((obj) -> {
            if (obj.getClass().getSimpleName().equals("DepthArea")) {
                DepthArea c = (DepthArea) obj;
                objects.add(c);
            }
        });
        chartS57Controller.addDepthAreas(objects);
    }

    @Override
    public void addDepthContours() {

    }
}
