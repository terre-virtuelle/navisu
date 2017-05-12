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
    double latDegrees1 = 48.24402;
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
            = "<Group>\n"
            + "        <!-- Vertical Y arrow and label -->\n"
            + "        <Group DEF='ArrowGreen'>\n"
            + "          <Shape>\n"
            + "            <Cylinder DEF='ArrowCylinder' height='200' radius='1' top='false'/>\n"
            + "            <Appearance DEF='Green'>\n"
            + "              <Material diffuseColor='.1 .6 .1' emissiveColor='.05 .2 .05'/>\n"
            + "            </Appearance>\n"
            + "          </Shape>\n"
            + "          <Transform translation='0 100 0'>\n"
            + "            <Shape>\n"
            + "              <Cone DEF='ArrowCone' bottomRadius='2' height='1'/>\n"
            + "              <Appearance USE='Green'/>\n"
            + "            </Shape>\n"
            + "          </Transform>\n"
            + "        </Group>\n"
            + "      </Group>\n"
            + "      <Transform rotation='0 0 1 -1.57079' translation='1 0 0 100'>\n"
            + "        <!-- Horizontal X arrow and label -->\n"
            + "        <Group>\n"
            + "          <Group DEF='ArrowRed'>\n"
            + "            <Shape>\n"
            + "              <Cylinder USE='ArrowCylinder'/>\n"
            + "              <Appearance DEF='Red'>\n"
            + "                <Material diffuseColor='1.0 .0 .0' emissiveColor='1.0 0 0'/>\n"
            + "              </Appearance>\n"
            + "            </Shape>\n"
            + "            <Transform translation='0 100 0'>\n"
            + "              <Shape>\n"
            + "                <Cone USE='ArrowCone'/>\n"
            + "                <Appearance USE='Red'/>\n"
            + "              </Shape>\n"
            + "            </Transform>\n"
            + "          </Group>\n"
            + "        </Group>\n"
            + "      </Transform>\n"
            + "      <Transform rotation='1 0 0 1.57079'>\n"
            + "        <!-- Perpendicular Z arrow and label, note right-hand rule -->\n"
            + "        <Group>\n"
            + "          <Group DEF='ArrowBlue'>\n"
            + "            <Shape>\n"
            + "              <Cylinder USE='ArrowCylinder'/>\n"
            + "              <Appearance DEF='Blue'>\n"
            + "                <Material diffuseColor='.3 .3 1' emissiveColor='.1 .1 .33'/>\n"
            + "              </Appearance>\n"
            + "            </Shape>\n"
            + "            <Transform translation='0 100 0'>\n"
            + "              <Shape>\n"
            + "                <Cone USE='ArrowCone'/>\n"
            + "                <Appearance USE='Blue'/>\n"
            + "              </Shape>\n"
            + "            </Transform>\n"
            + "          </Group>\n"
            + "        </Group>"
            + "          </Transform>\n";

    public RefLoader() {
        //48.27917 -4.58863
    }

    public String compute() {
        return baseData;
    }
}
