/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.util;

import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import java.util.HashSet;
import java.util.Set;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 *
 * @author serge
 * @date Mar 7, 2017
 */
public class WwjGeodesy {

    private final static Ellipsoid REFERENCE = Ellipsoid.WGS84;//default
    private final static double KM_TO_METER = 1000;

    public static double getDistanceM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        //  geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getAzimuth();
        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance() / KM_TO_METER;
    }

    public static Pair<Double, Double> getXYM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());
        GlobalCoordinates wpC = new GlobalCoordinates(posB.getLatitude().getDegrees(), posA.getLongitude().getDegrees());

        double y = geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpC).getEllipsoidalDistance() / KM_TO_METER;
        double x = geoCalc.calculateGeodeticCurve(REFERENCE, wpC, wpB).getEllipsoidalDistance() / KM_TO_METER;
        return new Pair<>(x, y);
    }

    public static Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);
        return new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 100);
    }

    public static Set<Position> toGrid(double latMin, double lonMin,
            double latMax, double lonMax,
            double y, double x) {

        double latRange = getDistanceM(Position.fromDegrees(latMin, lonMax), Position.fromDegrees(latMax, lonMax));
        double lonRange = getDistanceM(Position.fromDegrees(latMin, lonMin), Position.fromDegrees(latMin, lonMax));
        int nbLat = (int) (latRange / y);
        int nbLon = (int) (lonRange / x);
        Set<Position> tmp = new HashSet<>();
        for (int i = 0; i < nbLat; i++) {
            Position p = getPosition(Position.fromDegrees(latMin, lonMin), 0.0, i * latRange);
            for (int j = 0; j < nbLon; j++) {
                tmp.add(getPosition(p, 90.0, j * lonRange));
            }
        }
        return tmp;
    }
}
