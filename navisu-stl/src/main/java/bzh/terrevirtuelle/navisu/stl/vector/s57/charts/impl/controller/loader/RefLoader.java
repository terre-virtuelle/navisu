/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Polygon;
import java.util.List;

/**
 *
 * @author serge
 * @date Mar 18, 2017
 */
public class RefLoader {

    protected Polygon polygon;
    double latRangeMetric;
    double lonRangeMetric;
    double latMetric;
    double lonMetric;
    double latMetric1;
    double lonMetric1;
    double scaleLatFactor;
    double scaleLonFactor;
    double SQUARE_SIDE = 200;
    List<? extends Position> positions;
    double latDegrees = 48.243795854365146; 
    double lonDegrees = -4.675140275521613;
    double latDegrees1 = 48.24402  ;
    double lonDegrees1 = -4.4481;

    public RefLoader(Polygon polygon) {
        this.polygon = polygon;
        positions = polygon.getBoundaries().get(0);
        /*
        System.out.println("0 " + positions.get(0).getLatitude().getDegrees() + " " + positions.get(0).getLongitude().getDegrees());
        System.out.println("1 "+positions.get(1).getLatitude().getDegrees() + " " + positions.get(1).getLongitude().getDegrees());
        System.out.println("2 " +positions.get(2).getLatitude().getDegrees() + " " + positions.get(2).getLongitude().getDegrees());
        System.out.println("3 " +positions.get(3).getLatitude().getDegrees() + " " + positions.get(3).getLongitude().getDegrees());
*/
       
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));

        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        scaleLatFactor = latRangeMetric / SQUARE_SIDE;
        scaleLatFactor *= 10;
        scaleLonFactor = lonRangeMetric / SQUARE_SIDE;
        scaleLonFactor *= 10;
      
        latMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(latDegrees),
                        Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 100));
        lonMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                        Angle.fromDegrees(lonDegrees), 100));
        System.out.println(latDegrees + " " + lonDegrees);
        System.out.println("latMetric : " + latMetric + "  lonMetric : " + lonMetric);
        latMetric *= scaleLatFactor;
        latMetric = -SQUARE_SIDE + latMetric;
        lonMetric *= scaleLatFactor;
        lonMetric = SQUARE_SIDE - lonMetric;
      //  System.out.println("latMetric : " + latMetric + "  lonMetric : " + lonMetric);

        latMetric1 = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(latDegrees1),
                        Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 100));
        lonMetric1 = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                        Angle.fromDegrees(lonDegrees1), 100));
      //  System.out.println(latDegrees1 + " " + lonDegrees1);
      //  System.out.println("latMetric1 : " + latMetric1 + "  lonMetric1 : " + lonMetric1);
        latMetric1 *= scaleLatFactor;
        latMetric1 = -SQUARE_SIDE + latMetric1;
        lonMetric1 *= scaleLatFactor;
        lonMetric1 = SQUARE_SIDE - lonMetric1;
       // System.out.println("latMetric1 : " + latMetric1 + "  lonMetric1 : " + lonMetric1);
    }

    String baseData
            = "<Transform \n"
            + "    translation=\"0.0 " + 10.000000 + " 0.0\"\n"
            + "    scale=\"1.00000 10.00000 1.00000\"\n"
            + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
            + "     <Shape>\n"
            + "          <Appearance>\n"
            + "		     <Material DEF=\"MA_Shape\"\n"
            + "			diffuseColor=\"1.00 0.00 0.00\"\n"
            + "			specularColor=\"0.000 1.000 0.000\"\n"
            + "			emissiveColor=\"0.000 1.000 0.000\"\n"
            + "			ambientIntensity=\"0.0\"\n"
            + "			shininess=\"1.\"\n"
            + "			transparency=\"0.0\" \n"
            + "              />\n"
            + "		 </Appearance>\n"
            + "          <Cylinder/>\n"
            + "    </Shape>\n"
            + "</Transform>\n"
            + "<Transform \n"
            + "    translation=\"200.000000 10.000000 -200.000000\"\n"
            + "    scale=\"1.00000 10.00000 1.00000\"\n"
            + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
            + "     <Shape>\n"
            + "          <Appearance>\n"
            + "		     <Material DEF=\"MA_Shape\"\n"
            + "			diffuseColor=\"0.00 1.00 0.00\"\n"
            + "			specularColor=\"0.000 1.000 0.000\"\n"
            + "			emissiveColor=\"0.000 1.000 0.000\"\n"
            + "			ambientIntensity=\"0.0\"\n"
            + "			shininess=\"1.\"\n"
            + "			transparency=\"0.0\" \n"
            + "              />\n"
            + "		 </Appearance>\n"
            + "          <Cylinder/>\n"
            + "    </Shape>\n"
            + "</Transform>\n"
            + "<Transform \n"
            + "    translation=\"50.000000 10.000000 -170.000000\"\n"
            + "    scale=\"1.00000 10.00000 1.00000\"\n"
            + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
            + "     <Shape>\n"
            + "          <Appearance>\n"
            + "		     <Material DEF=\"MA_Shape\"\n"
            + "			diffuseColor=\"0.00 0.00 1.00\"\n"
            + "			specularColor=\"0.000 1.000 0.000\"\n"
            + "			emissiveColor=\"0.000 1.000 0.000\"\n"
            + "			ambientIntensity=\"0.0\"\n"
            + "			shininess=\"1.\"\n"
            + "			transparency=\"0.0\" \n"
            + "              />\n"
            + "		 </Appearance>\n"
            + "          <Cylinder/>\n"
            + "    </Shape>\n"
            + "</Transform>\n"
            + "<Transform \n"
            + "    translation=\" 13.950034543109629 10.000000 -13.984894708283745\"\n"
            + "    scale=\"1.00000 10.00000 1.00000\"\n"
            + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
            + "     <Shape>\n"
            + "          <Appearance>\n"
            + "		     <Material DEF=\"MA_Shape\"\n"
            + "			diffuseColor=\"0.00 1.00 1.00\"\n"
            + "			specularColor=\"0.000 1.000 1.000\"\n"
            + "			emissiveColor=\"0.000 1.000 1.000\"\n"
            + "			ambientIntensity=\"0.0\"\n"
            + "			shininess=\"1.\"\n"
            + "			transparency=\"0.0\" \n"
            + "              />\n"
            + "		 </Appearance>\n"
            + "          <Cylinder/>\n"
            + "    </Shape>\n"
            + "</Transform>\n"
            + "<Transform \n"
            + "    translation=\"3.933442510419615 10.000000 6.425384051355084\"\n"
            + "    scale=\"1.00000 10.00000 1.00000\"\n"
            + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
            + "     <Shape>\n"
            + "          <Appearance>\n"
            + "		     <Material DEF=\"MA_Shape\"\n"
            + "			diffuseColor=\"1.00 0.00 1.00\"\n"
            + "			specularColor=\"1.000 0.000 1.000\"\n"
            + "			emissiveColor=\"1.000 0.000 1.000\"\n"
            + "			ambientIntensity=\"0.0\"\n"
            + "			shininess=\"1.\"\n"
            + "			transparency=\"0.0\" \n"
            + "              />\n"
            + "		 </Appearance>\n"
            + "          <Cylinder/>\n"
            + "    </Shape>\n"
            + "</Transform>\n";

    public RefLoader() {
        //48.27917 -4.58863
    }

    public String compute() {
        return baseData;
    }
}
