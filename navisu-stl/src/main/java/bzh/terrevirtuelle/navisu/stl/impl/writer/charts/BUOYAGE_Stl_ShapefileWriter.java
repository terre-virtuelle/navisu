/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.writer.charts;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.BUOYAGE_ShapefileLoader;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATCAM;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATLAM;
import bzh.terrevirtuelle.navisu.util.Pair;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author serge
 * @date Mar 21, 2017
 */
public class BUOYAGE_Stl_ShapefileWriter
        extends BUOYAGE_ShapefileLoader {

    String result;
    double latRangeMetric;
    double lonRangeMetric;
    double scaleLatFactor;
    double scaleLonFactor;
    double buoyageScale;
    double sideX;
    double sideY;
    double latMetric;
    double lonMetric;
    List<? extends Position> positions;
    Geometry geometryEnveloppe;
    GeometryFactory geometryFactory;

    public BUOYAGE_Stl_ShapefileWriter(Geometry geometryEnveloppe,
            Polygon polygon,
            double scaleLatFactor, double scaleLonFactor,
            double buoyageScale,
            double sideX, double sideY,
            boolean dev, String path,
            Map<Pair<Double, Double>, String> topMarks,
            String marsys,
            String acronym,
            Set<S57Controller> s57Controllers) {
        super(dev, path, topMarks, marsys, acronym, s57Controllers);
        this.geometryEnveloppe = geometryEnveloppe;
        positions = polygon.getBoundaries().get(0);
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.buoyageScale = buoyageScale;
        this.sideX = sideX;
        this.sideY = sideY;
        geometryFactory = new GeometryFactory();
        result = "";
       
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Renderable createPoint(ShapefileRecord record,
            double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        super.createPoint(record, latDegrees, lonDegrees, attrs);
        if (geometryEnveloppe.contains(geometryFactory.createPoint(new Coordinate(lonDegrees, latDegrees)))) {
            
            latMetric = WwjGeodesy.getDistanceM(positions.get(0),
                    new Position(Angle.fromDegrees(latDegrees),
                            Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 0));

            lonMetric = WwjGeodesy.getDistanceM(positions.get(0),
                    new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                            Angle.fromDegrees(lonDegrees), 0));

            latMetric *= scaleLatFactor;
            lonMetric *= scaleLonFactor;

            latMetric = -sideX + latMetric;
            lonMetric = sideY - lonMetric;

            String catMark;
            if (acronym.contains("CAR")) {
                catMark = CATCAM.ATT.get(object.getCategoryOfMark());
                if (catMark != null) {
                    if (catMark.contains("South")) {
                        result += writeSouthBuoy(lonMetric, latMetric);
                    } else {
                        if (catMark.contains("North")) {
                            result += writeNorthBuoy(lonMetric, latMetric);
                        } else {
                            if (catMark.contains("West")) {
                                result += writeWestBuoy(lonMetric, latMetric);
                            } else {
                                if (catMark.contains("East")) {
                                    result += writeEastBuoy(lonMetric, latMetric);
                                }
                            }
                        }
                    }
                }
            } else if (acronym.contains("LAT")) {
                catMark = CATLAM.ATT.get(object.getCategoryOfMark());
                if (catMark != null) {
                    if (catMark.contains("Starboard")) {
                        result += writeCone(lonMetric, latMetric);
                    } else {
                        if (catMark.contains("Port")) {
                            result += writeCylinder(lonMetric, latMetric);
                        }
                    }
                }
            } else if (acronym.contains("SPP")) {
                result += writeCross(lonMetric, latMetric);
            } else if (acronym.contains("ISD")) {
                result += writeDanger(lonMetric, latMetric);
            } else if (acronym.contains("MORFAC")) {
                result += writeMorfac(lonMetric, latMetric);
            }
        }
        return placemark;
    }

    public String compute() {
        return result;
    }

    private String writeCylinder(double lat, double lon) {
        String str = " <!--" + "Lateral red cylinder buoy" + "-->\n"
                + "<Transform \n"
                + "   translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\"\n"
                + "    rotation=\"1 0 0 1.57058\">\n"
                + "<Shape>\n"
                + "<Appearance>\n"
                + "<Material diffuseColor='1 0 0'/>\n"
                + "</Appearance>\n"
                + "<Box size='1.5 1.8 .5'/>\n"
                + "</Shape> "
                + "     </Transform>\n";
        return str;
    }

    private String writeCone(double lat, double lon) {

        String str = " <!--" + "Lateral green cone buoy" + "-->\n"
                + "<Transform "
                + "    translation=\"" + lat + " -0.500000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\"\n"
                + "    rotation=\"0 1 0 3.14116\">\n"
                + "    <Shape DEF='Extrusion1'>\n"
                + "    <Appearance>\n"
                + "    <Material DEF='Green-starboard'\n"
                + "    ambientIntensity='.2'\n"
                + "    shininess='0'\n"
                + "    diffuseColor='0 .4902 0'/>\n"
                + "    </Appearance>\n"
                + "    <Extrusion DEF='GeoExtrusion1'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "      0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "      0 0 0\n"
                + "      0 1 0'/>\n"
                + "    </Shape>\n"
                + "     </Transform>\n";
        return str;
    }

    private String writeNorthBuoy(double lat, double lon) {
        String str = " <!--" + "Cardinal south buoy" + "-->\n"
                + "<Group DEF='SouthBuoy'>\n"
                + "<Transform translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\">\n"
                + "<Transform DEF='dad_Group1'\n"
                + " translation='0 0 -.73213'>\n"
                + " <Transform DEF='dad_Extrusion2'\n"
                + "  translation='0 0 1.86606'\n"
                + "  rotation='0 1 0 3.142'>\n"
                + "  <Shape DEF='Extrusion2'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Black'\n"
                + "     containerField='material'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='0 0 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion2'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + " <Transform DEF='dad_Extrusion1'\n"
                + "  translation='.04576 0 -.22879'\n"
                + "  rotation='0 1 0 3.142'>\n"
                + "  <Shape DEF='Extrusion1'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Yellow'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='1 1 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion1'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + " </Transform>\n"
                + "</Transform>"
                + "</Group>";
        return str;
    }

    private String writeSouthBuoy(double lat, double lon) {

        String str = " <!--" + "Cardinal north buoy" + "-->\n"
                + "<Group DEF='NorthBuoy'>\n"
                + "<Transform translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\">\n"
                + "<Transform DEF='dad_Group1'\n"
                + " translation='0 0 -.73213'>\n"
                + " <Transform DEF='dad_Extrusion2'\n"
                + "  translation='0 0 1.86606'>\n"
                + "  <Shape DEF='Extrusion2'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Yellow'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='1 1 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion2'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + " <Transform DEF='dad_Extrusion1'\n"
                + "  translation='.04576 0 -.22879'>\n"
                + "  <Shape DEF='Extrusion1'\n"
                + "   containerField='children'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Black'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='0 0 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion1'\n"
                + "    containerField='geometry'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + "</Transform>"
                + "</Transform>"
                + "</Group>";
        return str;
    }

    private String writeEastBuoy(double lat, double lon) {

        String str = " <!--" + "Cardinal east buoy" + "-->\n"
                + "<Group DEF='EastBuoy'>\n"
                + "<Transform translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\">\n"
                + "<Transform DEF='dad_Group1'\n"
                + " translation='0 0 -.73213'>\n"
                + " <Transform DEF='dad_Extrusion1'\n"
                + "  translation='.04576 0 -.22879'>\n"
                + "  <Shape DEF='Extrusion1'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Yellow'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='1 1 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion1'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + " <Transform DEF='dad_Extrusion2'\n"
                + "  translation='0 0 1.86606'\n"
                + "  rotation='0 1 0 3.142'>\n"
                + "  <Shape DEF='Extrusion2'>\n"
                + "   <Appearance>\n"
                + "    <Material DEF='Black'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='0 0 0'/>\n"
                + "   </Appearance>\n"
                + "   <Extrusion DEF='GeoExtrusion2'\n"
                + "    creaseAngle='.5236'\n"
                + "    crossSection='\n"
                + "     -1 1\n"
                + "     1 1\n"
                + "     0 -1\n"
                + "     -1 1'\n"
                + "    spine='\n"
                + "     0 0 0\n"
                + "     0 1 0'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + "</Transform>"
                + "</Transform>"
                + "</Group>";
        return str;
    }

    private String writeWestBuoy(double lat, double lon) {

        String str = " <!--" + "Cardinal west buoy" + "-->\n"
                + "<Group DEF='WestBuoy'>\n"
                + "<Transform translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\">\n"
                + "<Transform DEF='dad_Extrusion2'\n"
                + "translation='0 0 1.86606'>\n"
                + "<Shape DEF='Extrusion2'>\n"
                + "<Appearance>\n"
                + "<Material DEF='Black'\n"
                + "ambientIntensity='.2'\n"
                + "shininess='.2'\n"
                + "diffuseColor='0 0 0'/>\n"
                + "</Appearance>\n"
                + "<Extrusion DEF='GeoExtrusion2'\n"
                + "containerField='geometry'\n"
                + "creaseAngle='.5236'\n"
                + "crossSection='\n"
                + "-1 1\n"
                + "1 1\n"
                + "0 -1\n"
                + "-1 1'\n"
                + "spine='\n"
                + "0 0 0\n"
                + "0 1 0'/>\n"
                + "</Shape>\n"
                + "</Transform>\n"
                + "<Transform DEF='dad_Extrusion1'\n"
                + "translation='.04576 0 -.22879'\n"
                + "rotation='0 1 0 3.142'>\n"
                + "<Shape DEF='Extrusion1'>\n"
                + "<Appearance>\n"
                + "<Material DEF='Yellow'\n"
                + "ambientIntensity='.2'\n"
                + "shininess='.2'\n"
                + "diffuseColor='1 1 0'/>\n"
                + "</Appearance>\n"
                + "<Extrusion DEF='GeoExtrusion1'\n"
                + "creaseAngle='.5236'\n"
                + "crossSection='\n"
                + " -1 1\n"
                + "1 1\n"
                + "0 -1\n"
                + "-1 1'\n"
                + "spine='\n"
                + "0 0 0\n"
                + "0 1 0'/>\n"
                + "</Shape>\n"
                + "</Transform>\n"
                + "</Transform>\n"
                + "</Group>";

        return str;
    }

    private String writeCross(double lat, double lon) {

        String str = " <!--" + "Special purpose buoy" + "-->\n"
                + "<Transform \n"
                + "   translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + 0.2 * buoyageScale + " " + 0.2 * buoyageScale + " " + 0.2 * buoyageScale + "\"\n"
                //   + "    scale=\"0.200000 0.200000 0.20000\"\n"
                + "    rotation=\"0 0 1 3.14116\">\n"
                + "<Transform DEF='dad_Box1'\n"
                + " rotation='0 1 0 .785'>\n"
                + " <Shape DEF='Box1'\n"
                + "  containerField='children'>\n"
                + "  <Appearance\n"
                + "   containerField='appearance'>\n"
                + "   <Material DEF='Yellow'\n"
                + "    containerField='material'\n"
                + "    ambientIntensity='.2'\n"
                + "    shininess='.2'\n"
                + "    diffuseColor='1 1 0'/>\n"
                + "  </Appearance>\n"
                + "  <Box DEF='GeoBox1'\n"
                + "   containerField='geometry'\n"
                + "   size='10 2 2'/>\n"
                + " </Shape>\n"
                + "</Transform>\n"
                + "<Transform DEF='dad_Box2'\n"
                + " rotation='0 1 0 -.785'>\n"
                + " <Shape DEF='Box2'\n"
                + "  containerField='children'>\n"
                + "  <Appearance\n"
                + "   containerField='appearance'>\n"
                + "   <Material DEF='Shiny_Yellow'\n"
                + "    containerField='material'\n"
                + "    ambientIntensity='.2'\n"
                + "    shininess='.1'\n"
                + "    diffuseColor='1 1 0'\n"
                + "    specularColor='1 1 0'/>\n"
                + "  </Appearance>\n"
                + "  <Box DEF='GeoBox2'\n"
                + "   containerField='geometry'\n"
                + "   size='10 2 2'/>\n"
                + " </Shape>\n"
                + "</Transform>"
                + "     </Transform>\n";

        return str;
    }

    private String writeDanger(double lat, double lon) {

        String str = " <!--" + "Isolated danger buoy" + "-->\n"
                + "<Transform \n"
                + "   translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"" + buoyageScale + " " + buoyageScale + " " + buoyageScale + "\"\n"
                + "    rotation=\"0 0 0 0.0\">\n"
                + "<Transform DEF='dad_Cylinder1'\n"
                + " translation='0 0 -1'>\n"
                + " <Shape DEF='Cylinder1'\n"
                + "  containerField='children'>\n"
                + "  <Appearance\n"
                + "   containerField='appearance'>\n"
                + "   <Material DEF='Grey'\n"
                + "    containerField='material'\n"
                + "    ambientIntensity='.2'\n"
                + "    shininess='.1'\n"
                + "    diffuseColor='1.0 1.0 1.0'/>\n"
                + "  </Appearance>\n"
                + "  <Cylinder DEF='GeoCylinder1'\n"
                + "   containerField='geometry'\n"
                + "   height='1'\n"
                + "   radius='1'/>\n"
                + " </Shape>\n"
                + "</Transform>\n"
                + "<Transform DEF='dad_Cylinder2'\n"
                + " translation='0 0 1'>\n"
                + " <Shape DEF='Cylinder2'\n"
                + "  containerField='children'>\n"
                + "  <Appearance\n"
                + "   containerField='appearance'>\n"
                + "   <Material\n"
                + "    containerField='material'\n"
                + "    USE='Grey'/>\n"
                + "  </Appearance>\n"
                + "  <Cylinder DEF='GeoCylinder2'\n"
                + "   containerField='geometry'\n"
                + "   height='1'\n"
                + "   radius='1'/>\n"
                + " </Shape>\n"
                + "</Transform>"
                + "     </Transform>\n";

        return str;
    }

    private String writeMorfac(double lat, double lon) {
        String str = " <!--" + "Morfac buoy" + "-->\n"
                + "<Transform \n"
                + "   translation=\"" + lat + " -0.200000 " + lon + "\"\n"
                + "    scale=\"" + 0.6 * buoyageScale + " " + 0.6 * buoyageScale + " " + 0.6 * buoyageScale + "\"\n"
                //    + "    scale=\".6 .6 .6\"\n"
                + "    rotation=\"0 0 0 0.0\">\n"
                + "<Shape>\n"
                + "      <Appearance>\n"
                + "        <Material diffuseColor='0 0 0'/>\n"
                + "      </Appearance>\n"
                + "      <Extrusion beginCap='false' convex='false' creaseAngle='1.57' "
                + "crossSection='1.00 0.00 0.92 -0.38 0.71 -0.71 0.38 -0.92 0.00 -1.00 -0.38 -0.92 -0.71 -0.71 -0.92 -0.38 -1.00 -0.00 -0.92 0.38 -0.71 0.71 -0.38 0.92 0.00 1.00 0.38 0.92 0.71 0.71 0.92 0.38 1.00 0.00' endCap='false' spine='2.00 0.0 0.00 1.85 0.0 0.77 1.41 0.0 1.41 0.77 0.0 1.85 0.00 0.0 2.00 -0.77 0.0 1.85 -1.41 0.0 1.41 -1.85 0.0 0.77 -2.00 0.0 0.00 -1.85 0.0 -0.77 -1.41 0.0 -1.41 -0.77 0.0 -1.85 0.00 0.0 -2.00 0.77 0.0 -1.85 1.41 0.0 -1.41 1.85 0.0 -0.77 2.00 0.0 0.00'/>\n"
                + "    </Shape>"
                + "     </Transform>\n";
        return str;
    }
}
