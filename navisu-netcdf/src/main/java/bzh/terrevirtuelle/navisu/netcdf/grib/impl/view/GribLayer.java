package bzh.terrevirtuelle.navisu.netcdf.grib.impl.view;

import bzh.terrevirtuelle.navisu.netcdf.common.view.symbols.meteorology.Arrow;
import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import gov.nasa.worldwind.layers.RenderableLayer;

import java.util.ArrayList;
import java.util.List;
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
        int timeDimension = model.getTimeDimension();

        List<Arrow> arrows = new ArrayList<>();
      //  for (int i = 0; i < timeDimension; i++) {
        int i = 0;
            for (double lat = latInit; lat <= latMax; lat += latGap) {
                for (double lon = lonInit; lon <= lonMax; lon += lonGap) {
                    Arrow arrow = new Arrow(lat, lon, model.getVelocity(lat, lon, i));
                    arrow.setRotation(Math.toDegrees(model.getDirection(lat, lon, i)));
                    arrows.add(arrow);
                }
            }
      //  }

        addRenderables(arrows);
    }

}
