/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Sounding;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.Path;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class SoundingView {

    protected RenderableLayer layer;
    protected List<Landmark> objects;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes attrs;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;

    public SoundingView(RenderableLayer layer) {
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<Sounding> objects) {
        List<Path> paths = new ArrayList<>();
        for (Sounding object : objects) {
            lat = object.getLatitude();
            lon = object.getLongitude();
            String label = Double.toString(object.getDepth());
            ArrayList<Position> pathPositions = new ArrayList<>();
            pathPositions.add(Position.fromDegrees(lat, lon, 10));
            pathPositions.add(Position.fromDegrees(lat, lon, 00));

            Path path = new Path(pathPositions);
            path.setViewDistanceExpiration(false);
            path.setAltitudeMode(WorldWind.ABSOLUTE);
            path.setFollowTerrain(true);
            path.setShowPositions(true);
            path.setShowPositionsScale(3);
            path.setShowPositionsThreshold(Double.MAX_VALUE);
            ShapeAttributes attrs = new BasicShapeAttributes();
            attrs.setOutlineWidth(3);
            attrs.setOutlineMaterial(Material.RED);
            path.setAttributes(attrs);
            path.setValue(AVKey.DISPLAY_NAME, label);
            paths.add(path);

        }
        layer.addRenderables(paths);
        wwd.redrawNow();
    }
}
