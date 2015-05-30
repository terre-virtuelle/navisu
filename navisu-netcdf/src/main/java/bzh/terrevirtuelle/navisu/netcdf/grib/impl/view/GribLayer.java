package bzh.terrevirtuelle.navisu.netcdf.grib.impl.view;

import bzh.terrevirtuelle.navisu.netcdf.common.view.Arrow;
import bzh.terrevirtuelle.navisu.netcdf.common.view.Arrow_10_15;
import bzh.terrevirtuelle.navisu.netcdf.common.view.METEO_SYMBOLS;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import gov.nasa.worldwind.layers.RenderableLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: jordan Date: 24/11/2013
 */
public class GribLayer
        extends RenderableLayer {

    protected final static Logger LOGGUER = Logger.getLogger(GribLayer.class.getName());
    protected GribModel model;

    public GribLayer(GribModel model) {

        this.model = model;
        this.init();
    }

    private void init() {
        double latInit = this.model.getBottomRightLatitude();
        double latMax = this.model.getTopLeftLatitude();
        double latGap = this.model.getLatitudeGap();
        double lonInit = this.model.getTopLeftLongitude();
        double lonMax = this.model.getBottomRightLongitude();
        double lonGap = this.model.getLongitudeGap();

        List<Arrow> arrows = new ArrayList<>();

        for (double lat = latInit; lat <= latMax; lat += latGap) {
            for (double lon = lonInit; lon <= lonMax; lon += lonGap) {
                 Arrow arrow = new Arrow_10_15(lat, lon);
                arrow.setRotation(Math.toDegrees(model.getDirection(lat, lon, 0)));
                arrows.add(arrow);
            }
        }
        addRenderables(arrows);
    }
}
