/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.catalog;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.navigation.view.NavigationIcons;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import java.awt.Color;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class SailingDirectionsController
        extends NavigationController {

    

    public SailingDirectionsController(S57Behavior s57Behavior,
            GuiAgentServices guiAgentServices,
            NavigationData navigationData, double range,
            String displayName, String description) {
        super(s57Behavior, guiAgentServices, navigationData, range, displayName, description);
         
    }

    @Override
    public void updateTarget(Ship ship) {

        distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
        azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
        surveyZone.setValue(AVKey.DISPLAY_NAME, ((SailingDirections) navigationData).getDescription() + "\n distance :  "
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
        polygonNormalAttributes.setInteriorMaterial(new Material(Color.GREEN));
        polygonNormalAttributes.setDrawInterior(true);
        polygonNormalAttributes.setInteriorOpacity(0.02);
        polygonNormalAttributes.setOutlineMaterial(new Material(Color.GREEN));
        polygonNormalAttributes.setOutlineOpacity(0.2);
        polygonNormalAttributes.setEnableLighting(true);

        polygonHighlightAttributes = new BasicShapeAttributes(polygonNormalAttributes);
        polygonHighlightAttributes.setOutlineOpacity(0.2);
        polygonHighlightAttributes.setDrawInterior(true);
        polygonHighlightAttributes.setInteriorMaterial(new Material(Color.GREEN));
        polygonHighlightAttributes.setInteriorOpacity(0.2);
    }
    
}
