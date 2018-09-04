/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BUOYAGE_INV;
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
public class BuoyageView {

    protected RenderableLayer layer;
    protected List<Buoyage> buoyages;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes attrs;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;
    protected String label;
    protected boolean dev = false;

    public BuoyageView() {
    }

    public BuoyageView(RenderableLayer layer) {
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<Buoyage> buoyages) {
        display(buoyages, 0.8);
    }

    @SuppressWarnings("unchecked")
    public void display(List<Buoyage> buoyages, double scale) {

        List<PointPlacemark> pointPlacemarks = new ArrayList<>();
        for (Buoyage buoyage : buoyages) {

            acronym = BUOYAGE_INV.ATT.get(buoyage.getClass().getSimpleName());
            lat = buoyage.getLatitude();
            lon = buoyage.getLongitude();

            placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            label = buoyage.getClass().getSimpleName() + "\n"
                    + buoyage.getObjectName() + "\n"
                    + "Lat : " + new Float(lat).toString() + "\n"
                    + "Lon : " + new Float(lon).toString();
            buoyage.setLabel(label);
            placemark.setValue(AVKey.DISPLAY_NAME, label);

            String imageAddress = "";
            if (acronym.equals("LNDMRK")) {
                imageAddress = "img/landmarks_" + buoyage.getMarsys() + "/"
                        + acronym + "_"
                        + buoyage.getCategoryOfMark() + "_"
                        + buoyage.getConspicuousVisually() + "_"
                        + ((Landmark) buoyage).getFunction() + "_"
                        + buoyage.getColour() + "_"
                        + buoyage.getColourPattern() + "_"
                        + buoyage.getMarsys()
                        + ".png";
            } else {
                if (acronym.equals("DAYMAR")) {
                    imageAddress = "img/daymarks_"
                            + buoyage.getMarsys() + "/"
                            + acronym + "_"
                            + buoyage.getShape() + "_"
                            + buoyage.getCategoryOfMark() + "_"
                            + buoyage.getColour() + "_"
                            + buoyage.getColourPattern() + "_"
                            + buoyage.getNatureOfConstruction() + "_"
                            + buoyage.getMarsys()
                            + ".png";
                } else {
                    imageAddress = "img/buoyage_"
                            + buoyage.getMarsys() + "/"
                            + acronym + "_"
                            + buoyage.getShape() + "_"
                            + buoyage.getCategoryOfMark() + "_"
                            + buoyage.getColour() + "_"
                            + buoyage.getColourPattern() + "_"
                            + buoyage.getTopMark() + "_"
                            + buoyage.getMarsys() + ".png";
                }
            }
            buoyage.setImageAddress(imageAddress);
            attrs = new PointPlacemarkAttributes();
            attrs.setImageAddress(imageAddress);
            attrs.setImageOffset(Offset.BOTTOM_CENTER);
            attrs.setScale(scale);//0.9
            placemark.setAttributes(attrs);
            if (dev) {
                placemark.setValue(AVKey.DISPLAY_NAME, imageAddress);
            }
            pointPlacemarks.add(placemark);
        }
        if (layer != null) {
            layer.addRenderables(pointPlacemarks);
        }
    }
}
