/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.dem.impl.controller;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import gov.nasa.worldwind.WorldWindow;

/**
 *
 * @author serge
 */
public class DemComponentController {
    /*
     ArrayList<LatLon> latlons = new ArrayList<>();

            latlons.add(LatLon.fromDegrees(45.50d, -123.3d));
            latlons.add(LatLon.fromDegrees(45.52d, -123.3d));
            latlons.add(LatLon.fromDegrees(45.54d, -123.3d));
            latlons.add(LatLon.fromDegrees(45.56d, -123.3d));
            latlons.add(LatLon.fromDegrees(45.58d, -123.3d));
            latlons.add(LatLon.fromDegrees(45.60d, -123.3d));

            Sector sector = Sector.fromDegrees(45d, 46d, -123d, -122d);

            double[] elevations = new double[latlons.size()];

            // request resolution of DTED2 (1degree / 3600 )
            double targetResolution = Angle.fromDegrees(1d).radians / 3600;

            double resolutionAchieved = this.wwd.getModel().getGlobe().getElevationModel().getElevations(
                    sector, latlons, targetResolution, elevations);

            StringBuffer sb = new StringBuffer();
            for (double e : elevations) {
                sb.append("\n").append(e);
            }
    */
    
    Point3D[][] retrieveElevations(WorldWindow wwd, Point3D[][] latlons, double targetResolution){
        return null;
    }
}
