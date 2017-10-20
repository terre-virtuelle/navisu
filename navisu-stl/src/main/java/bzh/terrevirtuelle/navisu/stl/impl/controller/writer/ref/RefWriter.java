/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.controller.writer.ref;

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
public class RefWriter {

    protected Polygon polygon;
    double latRangeMetric;
    double lonRangeMetric;
    double latMetric;
    double lonMetric;
    double latMetric1;
    double lonMetric1;
    double scaleLatFactor;
    double scaleLonFactor;
    // double SQUARE_SIDE = 200;
    double tileSideX;
    double tileSideY;
    List<? extends Position> positions;
    double latDegrees;
    double lonDegrees;
    double latDegrees1;
    double lonDegrees1;
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

    public RefWriter(List<? extends Position> positions, double tileSideX, double tileSideY) {

        this.positions = positions;
        this.tileSideX = tileSideX;
        this.tileSideY = tileSideY;
        latDegrees = positions.get(0).getLatitude().getDegrees();
        lonDegrees = positions.get(0).getLongitude().getDegrees();
        latDegrees1 = positions.get(2).getLatitude().getDegrees();
        lonDegrees1 = positions.get(2).getLongitude().getDegrees();

        System.out.println(latDegrees + " " + lonDegrees + " " + latDegrees1 + " " + lonDegrees1);
        latRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(3).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(3).getLongitude().getDegrees()), 100));

        lonRangeMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(1).getLatitude().getDegrees()),
                        Angle.fromDegrees(positions.get(1).getLongitude().getDegrees()), 100));

        scaleLatFactor = latRangeMetric / tileSideY;
        scaleLatFactor *= 10;
        scaleLonFactor = lonRangeMetric / tileSideX;
        scaleLonFactor *= 10;

        latMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(latDegrees),
                        Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 100));
        lonMetric = WwjGeodesy.getDistanceM(positions.get(0),
                new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                        Angle.fromDegrees(lonDegrees), 100));
        latMetric *= scaleLatFactor;
        latMetric = -tileSideY + latMetric;
        lonMetric *= scaleLatFactor;
        lonMetric = tileSideX - lonMetric;

    }

    public String compute() {
        String transform = "<Transform "
                + "translation='" + 2 * tileSideX + " 0.0 " + 2 * tileSideY 
                +  " '>\n";

        return transform + baseData + "</Transform>\n";
    }
}
