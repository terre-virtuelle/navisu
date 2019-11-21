/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.UnderwaterAwashRock;
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
public class UnderwaterAwashRockView {

    protected RenderableLayer layer;
    protected List<UnderwaterAwashRock> objects;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes attrs;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;
    protected String label;
    protected boolean dev = false;

    public UnderwaterAwashRockView(RenderableLayer layer) {
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<UnderwaterAwashRock> objects) {
        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        for (UnderwaterAwashRock object : objects) {

            acronym = "UWTROC";
            lat = object.getLatitude();
            lon = object.getLongitude();
            String natureOfSurface = object.getNatureOfSurface();
            if (natureOfSurface == null) {
                natureOfSurface = "";
            } else {
                natureOfSurface = NATSUR.ATT.get(natureOfSurface);
                natureOfSurface += "\n";
            }
            String valueOfSounding = object.getValueOfSounding();
            if (valueOfSounding == null) {
                valueOfSounding = "";
            } else {
                valueOfSounding += "\n";
            }
            placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0.0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            label = object.getClass().getSimpleName() + "\n"
                    + natureOfSurface
                    + valueOfSounding
                    + "Lat : " + new Float(lat).toString() + "\n"
                    + "Lon : " + new Float(lon).toString();
            // object.setLabel(label);
            placemark.setValue(AVKey.DISPLAY_NAME, label);

            String imageAddress = "";
            if (acronym.equals("LNDMRK")) {
                imageAddress = "img/wreck/"
                        + acronym
                        + "04.png";
            }
            object.setImageAddress(imageAddress);

            attrs = new PointPlacemarkAttributes();
            attrs.setImageAddress(imageAddress);
            attrs.setImageOffset(Offset.BOTTOM_CENTER);
            attrs.setScale(0.65);//0.9
            placemark.setAttributes(attrs);
            if (dev) {
                placemark.setValue(AVKey.DISPLAY_NAME, imageAddress);
            }
            pointPlacemarks.add(placemark);
        }
        layer.addRenderables(pointPlacemarks);
    }
}
