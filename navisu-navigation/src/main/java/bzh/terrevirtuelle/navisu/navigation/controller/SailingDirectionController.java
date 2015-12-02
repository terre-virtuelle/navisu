/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Behavior;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceCircle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
public class SailingDirectionController
        extends NavigationController {

    private String wkt;

    public SailingDirectionController(NavigationData navigationData, double range,
            String displayName, String description) {
        super(navigationData, range, displayName, description);
        
    }

    @Override
    public void updateTarget(Ship ship) {
        distance = getDistanceNm(lat, lon, ship.getLatitude(), ship.getLongitude());
        azimuth = getAzimuth(ship.getLatitude(), ship.getLongitude(), lat, lon);
        s57Behavior.doIt(distance, azimuth);
        surveyZone.setValue(AVKey.DISPLAY_NAME, ((Avurnav) navigationData).getDescription() + "\n distance :  "
                + String.format("%.2f", distance) + " Nm"
                + "\nazimuth :  " + String.format("%d", (int) azimuth) + " °  ");
    }

}
