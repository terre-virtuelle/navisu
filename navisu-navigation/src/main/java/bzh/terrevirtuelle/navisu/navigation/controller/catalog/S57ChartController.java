/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.catalog;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.awt.Color;

/**
 *
 * @author serge
 */
public class S57ChartController
        extends NavigationController {

    public S57ChartController(S57Behavior s57Behavior,
            GuiAgentServices guiAgentServices,
            NavigationData navigationData, boolean create,double range,
            String displayName, String description) {
        super(s57Behavior, guiAgentServices, navigationData, create, range, displayName, description);
    }

    @Override
    public void updateTarget(Ship ship) {
        System.out.println("S57ChartController updateTarget " + ship);
        distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
        azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
        surveyZone.setValue(AVKey.DISPLAY_NAME, ((S57Chart) navigationData).getDescription() + "\n distance :  "
                + String.format("%.2f", distance) + " Nm"
                + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
        s57Behavior.doIt(distance, azimuth);
    }

    @Override
    protected void createAttributes() {
        placemarkNormalAttributes = new PointPlacemarkAttributes();
        String[] t = navigationData.getClass().getName().split("\\.");
        placemarkNormalAttributes.setImageAddress(NavigationIcons.ICONS.get(t[t.length - 1]));
        placemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        placemarkNormalAttributes.setScale(0.3);

        polygonNormalAttributes = new BasicShapeAttributes();
        polygonNormalAttributes.setInteriorMaterial(new Material(Color.ORANGE));
        polygonNormalAttributes.setDrawInterior(true);
        polygonNormalAttributes.setInteriorOpacity(0.02);
        polygonNormalAttributes.setOutlineMaterial(new Material(Color.ORANGE));
        polygonNormalAttributes.setOutlineOpacity(0.2);
        polygonNormalAttributes.setEnableLighting(true);

        polygonHighlightAttributes = new BasicShapeAttributes(polygonNormalAttributes);
        polygonHighlightAttributes.setOutlineOpacity(0.2);
        polygonHighlightAttributes.setDrawInterior(true);
        polygonHighlightAttributes.setInteriorMaterial(new Material(Color.ORANGE));
        polygonHighlightAttributes.setInteriorOpacity(0.2);
    }

    

}
