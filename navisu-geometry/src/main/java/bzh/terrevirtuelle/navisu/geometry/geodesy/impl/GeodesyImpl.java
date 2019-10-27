/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.geodesy.impl;

import bzh.terrevirtuelle.navisu.geometry.geodesy.Geodesy;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 *
 * @author serge
 * @date Sep 12, 2017
 */
public class GeodesyImpl
        implements Geodesy, GeodesyServices, ComponentState {

    private final Ellipsoid REFERENCE = Ellipsoid.WGS84;//default

    @Override
    public double getDistanceM(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());

        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance();
    }

    @Override
    public double getDistanceM(double latA, double lonA, double latB, double lonB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();

        GlobalCoordinates wpA = new GlobalCoordinates(latA, lonA);
        GlobalCoordinates wpB = new GlobalCoordinates(latB, lonB);

        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getEllipsoidalDistance();
    }

    /*
    v -4.617537622481796 48.35436048828461 83.942168
     */
    @Override
    public String getDistancesXY(String latLonElvA, String latLonElvB) {
        String result = "";
        String tmp = latLonElvA.replace("v ", "").trim();
        String[] tab = tmp.split("\\s+");
        double lonA = Double.valueOf(tab[0]);
        double latA = Double.valueOf(tab[1]);
        double elvA = Double.valueOf(tab[2]);

        tmp = latLonElvB.replace("v ", "").trim();
        tab = tmp.split("\\s+");
        double lonB = Double.valueOf(tab[0]);
        double latB = Double.valueOf(tab[1]);
        double elvB = Double.valueOf(tab[2]);
        elvB = elvB - elvA;

        double x = getDistanceM(latA, lonA, latA, lonB);
        if (lonB < lonA) {
            x = -x;
        }
        double y = getDistanceM(latA, lonA, latB, lonA);
        if (latB < latA) {
           y = -y;
        }
        result = "v " + x + " " + y + " " + elvB;
        return result;
    }

    @Override
    public Position getPosition(Position posA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE,
                locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 0);
        return p;
    }

    @Override
    public Position getPosition(Position posA, double bearing, double distance, double elevation) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE, locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), elevation);
        return p;
    }

    @Override
    public Position getPosition(double latA, double lonA, double bearing, double distance) {
        double[] endBearing = new double[1];
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates locA = new GlobalCoordinates(latA, lonA);
        GlobalCoordinates coordinates = geoCalc.calculateEndingGlobalCoordinates(REFERENCE, locA, bearing, distance, endBearing);

        Position p = new Position(Angle.fromDegrees(coordinates.getLatitude()),
                Angle.fromDegrees(coordinates.getLongitude()), 0);
        return p;
    }

    @Override
    public double getAzimuth(Position posA, Position posB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(posA.getLatitude().getDegrees(), posA.getLongitude().getDegrees());
        GlobalCoordinates wpB = new GlobalCoordinates(posB.getLatitude().getDegrees(), posB.getLongitude().getDegrees());

        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getAzimuth();
    }

    @Override
    public double getAzimuth(double latA, double lonA, double latB, double lonB) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        GlobalCoordinates wpA = new GlobalCoordinates(latA, lonA);
        GlobalCoordinates wpB = new GlobalCoordinates(latB, lonB);

        return geoCalc.calculateGeodeticCurve(REFERENCE, wpA, wpB).getAzimuth();
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    @Override
    public List<Pair<Position, Position>> split(int count, Position a, Position b) {

        return null;
    }

}
