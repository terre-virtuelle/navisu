/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.catalog;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
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
public class AvurnavController
        extends NavigationController {

    public AvurnavController(S57Behavior s57Behavior, GuiAgentServices guiAgentServices,
            NavigationData avurnav, double range,
            String displayName, String description) {
        super(s57Behavior, guiAgentServices, avurnav, range, displayName, description);
    }

    @Override
    public void updateTarget(Ship ship) {

        distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
        azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
        s57Behavior.doIt(distance, azimuth);
        surveyZone.setValue(AVKey.DISPLAY_NAME, ((NavigationalWarnings) navigationData).getDescription() + "\n distance :  "
                + String.format("%.2f", distance) + " Nm"
                + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
    }

    @Override
    protected void createAttributes() {
        placemarkNormalAttributes = new PointPlacemarkAttributes();
        String[] t = navigationData.getClass().getName().split("\\.");
        placemarkNormalAttributes.setImageAddress(NavigationIcons.ICONS.get(t[t.length - 1]));
        placemarkNormalAttributes.setImageOffset(Offset.BOTTOM_CENTER);
        placemarkNormalAttributes.setScale(0.3);

        polygonNormalAttributes = new BasicShapeAttributes();
        polygonNormalAttributes.setInteriorMaterial(new Material(Color.GRAY));
        polygonNormalAttributes.setDrawInterior(true);
        polygonNormalAttributes.setInteriorOpacity(0.02);
        polygonNormalAttributes.setOutlineMaterial(new Material(Color.GRAY));
        polygonNormalAttributes.setOutlineOpacity(0.2);
        polygonNormalAttributes.setEnableLighting(true);

        polygonHighlightAttributes = new BasicShapeAttributes(polygonNormalAttributes);
        polygonHighlightAttributes.setOutlineOpacity(0.2);
        polygonHighlightAttributes.setDrawInterior(true);
        polygonHighlightAttributes.setInteriorMaterial(new Material(Color.GRAY));
        polygonHighlightAttributes.setInteriorOpacity(0.2);
    }

    private String createText(NavigationalWarnings a) {
        String tmp = Translator.tr("navigation.avurnav.globalZone") + " : " + a.getGlobalZone() + "\n"
                + Translator.tr("navigation.avurnav.broadcastTime") + " : " + a.getBroadcastTime() + "\n"
                + Translator.tr("navigation.avurnav.expirationDate") + " : " + a.getExpirationDate() + "\n"
                + Translator.tr("navigation.avurnav.description") + " : " + a.getDescription() + "\n\n";
        tmp = tmp.replace("\t", "");
        return tmp;
    }

    @Override
    protected void notifyNmeaMessage(GGA data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void notifyNmeaMessage(VTG data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void notifyNmeaMessage(RMC data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
