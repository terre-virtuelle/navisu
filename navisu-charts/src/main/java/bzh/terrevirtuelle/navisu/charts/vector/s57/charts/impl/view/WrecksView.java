/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Wrecks;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATWRK;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.EXPSOU;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.QUASOU;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.WATLEV;
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
            System.out.println("getCategoryOfWreck : " + object.getCategoryOfWreck());
            acronym = "WRECKS";
            lat = object.getLatitude();
            lon = object.getLongitude();

            placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0.0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);

            label = acronym + "\n"
                    + object.getObjectName() + "\n"
                    + "Lat : " + new Float(lat).toString() + "\n"
                    + "Lon : " + new Float(lon).toString() + "\n"
                    + "CATWRK : " + CATWRK.ATT.get(object.getCategoryOfWreck()) + "\n"
                    + "EXPSOU : " + EXPSOU.ATT.get(object.getExpositionOfSounding()) + "\n"
                    + "QUASOU : " + QUASOU.ATT.get(object.getQualityOfSoundingMeasurement()) + "\n"
                    + "VALSOU : " + ((int)(Double.valueOf(object.getValueOfSounding())*10))/10.0+" m" + "\n"
                    + "WATLEV : " + WATLEV.ATT.get(object.getWaterLevelEffect());
            object.setLabel(label);
            placemark.setValue(AVKey.DISPLAY_NAME, label);

            String imageAddress = "";
            if (acronym.equals("WRECKS")) {
                imageAddress = "img/wrecks/"
                        + acronym + "_"
                        + object.getCategoryOfWreck()
                        + ".png";
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
        wwd.redrawNow();
    }
}
