package bzh.terrevirtuelle.navisu.charts.raster.geotiff.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChart;
import bzh.terrevirtuelle.navisu.charts.raster.geotiff.GeoTiffChartServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import gov.nasa.worldwind.layers.SurfaceImageLayer;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class GeoTiffChartImpl implements GeoTiffChart, GeoTiffChartServices, Driver, ComponentState {

    protected final Logger LOGGER = Logger.getLogger(GeoTiffChartImpl.class.getName());

    protected static final String EXTENSION = ".tif";
    protected static final String GROUP = "GeoTiff charts";
    private SurfaceImageLayer layer;

    @UsedService
    GeoViewServices geoViewServices;

    @UsedService
    LayerTreeServices layerTreeServices;

    @Override
    public void componentInitiated() {
        layerTreeServices.createGroup(GROUP);
    }

    @Override
    public boolean canOpen(String file) {

        boolean canOpen = false;

        if (file.toLowerCase().endsWith(EXTENSION)) {
            canOpen = true;
        }

        return canOpen;
    }

    @Override
    public void open(ProgressHandle pHandle, String... files) {

        for (String file : files) {
            this.handleOpenFile(pHandle, file);
        }
    }

    protected void handleOpenFile(ProgressHandle pHandle, String file) {

        LOGGER.log(Level.INFO, "Opening {0} ...", file);

        this.layer = new SurfaceImageLayer();
        this.layer.setOpacity(1);
        this.layer.setPickEnabled(false);
        if (layer != null) {
            try {
                layer.addImage(file);
            } catch (IOException ex) {
                Logger.getLogger(GeoTiffChartImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            geoViewServices.getLayerManager().insertGeoLayer(GeoLayer.factory.newWorldWindGeoLayer(layer));
            layerTreeServices.addGeoLayer(GROUP, GeoLayer.factory.newWorldWindGeoLayer(layer));
        }

    }

    @Override
    public String getName() {
        return "GeoTiff";
    }

    @Override
    public String[] getExtensions() {
        return new String[]{"*" + EXTENSION};
    }

    @Override
    public void openChart(String file) {
        this.open(null, file);
    }

    @Override
    public Driver getDriver() {
        return this;
    }

    @Override
    public void componentStarted() { /* Nothing to do here */ }

    @Override
    public void componentStopped() { /* Nothing to do here */ }
}
