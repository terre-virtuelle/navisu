/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Light;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.COLOR;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.COLOR_NAME;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import java.util.List;

/**
 *
 * @author serge
 */
public class LightView {

    protected RenderableLayer layer;
    //  protected List<Light> lights;
    protected List<S57LightView> s57LigthViews;
    protected String acronym;
    protected WorldWindow wwd = GeoWorldWindViewImpl.getWW();
    protected Globe globe = GeoWorldWindViewImpl.getWW().getModel().getGlobe();
    protected double lat;
    protected double lon;
    protected String label;
    protected boolean dev = false;

    public LightView(RenderableLayer layer) {
        this.layer = layer;
    }

    @SuppressWarnings("unchecked")
    public void display(List<Light> objects) {

        objects.forEach((object) -> {
            showLightSectors(object);
        });
    }

    private void showLightSectors(Light data) {
        S57LightView lightView;
        if (data != null
                && data.getSectorLimitOne() != null
                && data.getSectorLimitTwo() != null
                && data.getValueOfNominalRange() != null) {
            lightView = new S57LightView();
            lightView.setTmp(true);
            double lat = data.getLatitude();
            double lon = data.getLongitude();
            double elevation = globe.getElevation(Angle.fromDegrees(lat), Angle.fromDegrees(lon));
            lightView.setCenter(new LatLon(Angle.fromDegrees(lat),
                    Angle.fromDegrees(lon)));
            double range = new Double(data.getValueOfNominalRange());
            lightView.setRadii(0.0, range * 1852);
            lightView.getAttributes().setInteriorOpacity(0.2);
            lightView.getAttributes().setOutlineOpacity(0.2);
            lightView.setHighlightAttributes(new BasicShapeAttributes(lightView.getAttributes()));
            lightView.getHighlightAttributes().setInteriorOpacity(1.0);
            lightView.setAltitude(elevation + 35);
            lightView.setAzimuths(Angle.fromDegrees(new Float(data.getSectorLimitOne()) + 180),
                    Angle.fromDegrees(new Float(data.getSectorLimitTwo()) + 180));

            String label = "Light \n"
                    + "Lat : " + Double.toString(lat) + "\n"
                    + "Lon : " + Double.toString(lon) + "\n"
                    + "Color : " + COLOR_NAME.getColor(data.getColour()) + "\n"
                    + (data.getSignalPeriod() != null ? "Period : " + (int) Double.parseDouble(data.getSignalPeriod()) + " s" + "\n" : "")
                    + (data.getHeight() != null ? "Height : " + (int) Double.parseDouble(data.getHeight()) + " m" + "\n" : "")
                    + (data.getValueOfNominalRange() != null ? "Nominal range : " + (int) Double.parseDouble(data.getValueOfNominalRange()) + " Nm" + "\n" : "")
                    + "Sect1 : " + (int) Double.parseDouble(data.getSectorLimitOne()) + "\n"
                    + "Sect2 : " + (int) Double.parseDouble(data.getSectorLimitTwo()) + "\n";
            lightView.setValue(AVKey.DISPLAY_NAME, label);
            lightView.getAttributes().setDrawOutline(true);

            // Si la couleur est blanche, la vue est jaune
            if (data.getColour().contains("1")) {
                lightView.getAttributes().setInteriorMaterial(new Material(COLOR.ATT.get("6")));
                lightView.getHighlightAttributes().setInteriorMaterial(new Material(COLOR.ATT.get("6")));
                lightView.getAttributes().setOutlineMaterial(new Material(COLOR.ATT.get("6")));
            } else {
                lightView.getAttributes().setInteriorMaterial(new Material(COLOR.ATT.get(data.getColour())));
                lightView.getHighlightAttributes().setInteriorMaterial(new Material(COLOR.ATT.get(data.getColour())));
                lightView.getAttributes().setOutlineMaterial(new Material(COLOR.ATT.get(data.getColour())));
            }
            layer.addRenderable(lightView);
        }
    }
}
