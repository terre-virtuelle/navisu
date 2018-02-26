/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.databases.impl.view;

import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.util.List;
import java.util.Map;

/**
 *
 * @author serge
 */
public class BuoyageView {

    protected LayersManagerServices layersManagerServices;
    protected RenderableLayer layer;
    protected List<Buoyage> buoyages;
    protected PointPlacemark placemark;
    protected PointPlacemarkAttributes attrs;
    protected String acronym;
    protected Map<Pair<Double, Double>, String> topMarkMap;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected double lat;
    protected double lon;
    protected String topMark;
    protected String label;

    public BuoyageView(Map<Pair<Double, Double>, String> topMarkMap,
            LayersManagerServices layersManagerServices,
            String group, String layerName,
            String acronym) {
        this.topMarkMap = topMarkMap;
        this.layersManagerServices = layersManagerServices;
        this.acronym = acronym;
        layer = layersManagerServices.getLayer(group, layerName);
    }

    @SuppressWarnings("unchecked")
    public void display(List<Buoyage> buoyages) {
        buoyages.stream().map((buoyage) -> {
            lat = buoyage.getLatitude();
            lon = buoyage.getLongitude();
            placemark = new PointPlacemark(Position.fromDegrees(lat, lon, 0));
            return buoyage;
        }).map((buoyage) -> {
            placemark.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
            return buoyage;
        }).map((buoyage) -> {
            label = buoyage.getClass().getSimpleName() + "\n"
                    + buoyage.getObjectName() + "\n"
                    + "Lat : " + new Float(lat).toString() + "\n"
                    + "Lon : " + new Float(lon).toString();
            buoyage.setLabel(label);
            placemark.setValue(AVKey.DISPLAY_NAME, label);
            topMark = topMarkMap.get(new Pair(lat, lon));
            if (topMark == null) {
                topMark = "0";
            }
            String imageAddress = "img/buoyage_" 
                    + buoyage.getMarsys() + "/" 
                    + acronym + "_"
                    + buoyage.getShape() + "_"
                    + buoyage.getCategoryOfMark() + "_"
                    + buoyage.getColour() + "_"
                    + buoyage.getColourPattern() + "_"
                    + topMark + "_"
                    + buoyage.getMarsys()
                    + ".png";
            buoyage.setImageAddress(imageAddress);
            return imageAddress;
        }).map((imageAddress) -> {
            attrs = new PointPlacemarkAttributes();
            return imageAddress;
        }).map((imageAddress) -> {
            attrs.setImageAddress(imageAddress);
            return imageAddress;
        }).map((imageAddress) -> {
            attrs.setImageOffset(Offset.BOTTOM_CENTER);
            return imageAddress;
        }).map((imageAddress) -> {
            attrs.setScale(0.65);//0.9
            return imageAddress;
        }).map((imageAddress) -> {
            placemark.setAttributes(attrs);
            return imageAddress;
        }).forEachOrdered((_item) -> {
            layer.addRenderable(placemark);
        });
        wwd.redrawNow();
    }
}
