/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.geodesy;

import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 *
 * @author serge
 * @date Sep 12, 2017
 */
public class Geodesy {

    private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default

    public static double getDistanceM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance();
    }

    public static Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 0);
        return p;
    }

    
}
