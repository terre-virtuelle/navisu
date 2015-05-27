package bzh.terrevirtuelle.navisu.netcdf.grib.impl.view;

import bzh.terrevirtuelle.navisu.netcdf.grib.impl.model.GribModel;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.*;
import gov.nasa.worldwind.util.WWUtil;
import java.awt.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * User: jordan Date: 24/11/2013
 */
public class GribLayer extends MarkerLayer {

    protected final static Logger LOGGUER = Logger.getLogger(GribLayer.class.getName());

    protected GribModel model;

    public GribLayer(GribModel model) {

        this.model = model;

        this.init();
    }

    private void init() {

        List<Marker> markers = new ArrayList<>();
        Marker marker;
        MarkerAttributes attr = new BasicMarkerAttributes(new Material(WWUtil.makeColorBrighter(Color.RED)), BasicMarkerShape.HEADING_LINE, 1d, 10, 10);
        for (double lat = this.model.getBottomRightLatitude(); lat < this.model.getTopLeftLatitude(); lat += this.model.getLatitudeGap()) {
            for (double lon = this.model.getTopLeftLongitude(); lon < this.model.getBottomRightLongitude(); lon += this.model.getLongitudeGap()) {
                // LOGGUER.info("---------- lat = " + lat + ", lon = " + lon);
                double[] vector = this.model.getVelocityVectorFromLatLon(lat, lon, 10);
                // LOGGUER.info("---------- x = " + vector[0] +" , y = " + vector[1]);
                marker = new BasicMarker(Position.fromDegrees(lat, lon, 0), attr);
                marker.setPosition(Position.fromDegrees(lat, lon, 0));
                marker.setHeading(Angle.fromDegrees(Math.toDegrees(vector[1])));
                markers.add(marker);
            }
        }
        this.setOverrideMarkerElevation(true);
        this.setKeepSeparated(false);
        this.setElevation(1000d);
        this.setMarkers(markers);
    }
}
