package bzh.terrevirtuelle.navisu.app.grib.impl.view;

import bzh.terrevirtuelle.navisu.app.grib.impl.model.GribModel;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.MarkerLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.markers.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jordan
 * Date: 24/11/2013
 */
public class GribLayer extends MarkerLayer {

    protected GribModel model;

    public GribLayer(GribModel model) {

        this.model = model;

        this.init();
    }

    private void init() {

        List<Marker> markers = new ArrayList<>();
        for(double lat = this.model.getBottomRightLatitude(); lat < this.model.getTopLeftLatitude(); lat += this.model.getLatitudeGap()) {
            for(double lon = this.model.getTopLeftLongitude(); lon < this.model.getBottomRightLongitude(); lon += this.model.getLongitudeGap()) {
                double[] vector = this.model.getVelocityVectorFromLatLon(lat, lon, 0);
                MarkerAttributes attr = new BasicMarkerAttributes(Material.WHITE, BasicMarkerShape.HEADING_LINE, 1d, 10, 5);
                Marker marker = new BasicMarker(Position.fromDegrees(lat, lon, 0), attr);
                marker.setPosition(Position.fromDegrees(lat, lon, 0));
                marker.setHeading(Angle.fromDegrees(vector[1]));
                markers.add(marker);
            }
        }
        this.setOverrideMarkerElevation(true);
        this.setKeepSeparated(false);
        this.setElevation(1000d);
        this.setMarkers(markers);
    }
}

/*

            double minLat = 20, maxLat = 60, latDelta = 2;
            double minLon = -140, maxLon = -60, lonDelta = 2;

            int i = 0;
            ArrayList<Marker> markers = new ArrayList<Marker>();
            for (double lat = minLat; lat <= maxLat; lat += latDelta)
            {
                for (double lon = minLon; lon <= maxLon; lon += lonDelta)
                {
                    Marker marker = new BasicMarker(Position.fromDegrees(lat, lon, 0), attrs[i % attrs.length]);
                    marker.setPosition(Position.fromDegrees(lat, lon, 0));
                    marker.setHeading(Angle.fromDegrees(lat * 5));
                    markers.add(marker);
                    i++;
                }
            }

            final MarkerLayer layer = new MarkerLayer();
            layer.setOverrideMarkerElevation(true);
            layer.setKeepSeparated(false);
            layer.setElevation(1000d);
            layer.setMarkers(markers);
            insertBeforePlacenames(this.getWwd(), layer);
            this.getLayerPanel().update(this.getWwd());
 */
