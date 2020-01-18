/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.UnderwaterAwashRock;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wrecks;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.NATSUR;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class WrecksView {

    protected RenderableLayer layer;
    protected List<Wrecks> objects;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes attrs;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;
    protected String label;
    protected boolean dev = false;

    public WrecksView(RenderableLayer layer) {
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<Wrecks> objects) {
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        for (Wrecks object : objects) {

            acronym = "Wreck";
            lat = object.getLatitude();
            lon = object.getLongitude();
            
            placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0.0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            
            pointPlacemarks.add(placemark);
        }
        layer.addRenderables(pointPlacemarks);
    }
}
