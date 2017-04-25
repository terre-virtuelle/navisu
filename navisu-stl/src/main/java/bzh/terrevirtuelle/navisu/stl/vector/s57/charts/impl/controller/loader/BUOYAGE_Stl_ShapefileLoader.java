/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

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
public class BUOYAGE_Stl_ShapefileLoader
        extends BUOYAGE_ShapefileLoader {

    static String result = "";
    double latRangeMetric;
    double lonRangeMetric;
    double scaleLatFactor;
    double scaleLonFactor;
    double squareSide;
    double latMetric;
    double lonMetric;
    List<? extends Position> positions;
    Geometry geometryEnveloppe;
    GeometryFactory geometryFactory;

    public BUOYAGE_Stl_ShapefileLoader(Geometry geometryEnveloppe, Polygon polygon,
            double scaleLatFactor, double scaleLonFactor, double squareSide,
            boolean dev, String path, Map<Pair<Double, Double>, String> topMarks, String marsys, String acronym, Set<S57Controller> s57Controllers) {
        super(dev, path, topMarks, marsys, acronym, s57Controllers);
        this.geometryEnveloppe = geometryEnveloppe;
        positions = polygon.getBoundaries().get(0);
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.squareSide = squareSide;
        geometryFactory = new GeometryFactory();
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
                            Angle.fromDegrees(positions.get(0).getLongitude().getDegrees()), 100));
            lonMetric = WwjGeodesy.getDistanceM(positions.get(0),
                    new Position(Angle.fromDegrees(positions.get(0).getLatitude().getDegrees()),
                            Angle.fromDegrees(lonDegrees), 100));
            latMetric *= scaleLatFactor;
            lonMetric *= scaleLatFactor;
            latMetric = -squareSide + latMetric;
            lonMetric = squareSide - lonMetric;

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
            }
            /*
        else if (acronym.contains("SPP")) {
            catMark = CATSPM.ATT.get(object.getCategoryOfMark());
        } else if (acronym.contains("ISD")) {
            catMark = "0";
        }
             */
        }
        return placemark;
    }

    public String compute() {
        return result;
    }

    private String writeCylinder(double lat, double lon) {
        /*
        String str = "<Transform \n"
                + "   translation=\"" + lat + " 2.00000 " + lon + "\"\n"
                + "    scale=\"1.00000 1.00000 1.00000\"\n"
                + "    rotation=\"0.0 0.0 0.0 0.0\">\n"
                + "     <Shape>\n"
                + "          <Appearance>\n"
                + "		     <Material DEF=\"MA_Shape\"\n"
                + "			diffuseColor=\"1.00 0.00 0.00\"\n"
                + "			specularColor=\"1.000 0.000 0.000\"\n"
                + "			emissiveColor=\"1.000 0.000 0.000\"\n"
                + "			ambientIntensity=\"0.0\"\n"
                + "			shininess=\"1.\"\n"
                + "			transparency=\"0.0\" \n"
                + "              />\n"
                + "		 </Appearance>\n"
                + "          <Cylinder height='5' radius='1.5'/>\n"
                + "    </Shape>\n"
                + "</Transform>\n";
         */
        String str = "<Transform \n"
                + "   translation=\"" + lat + " 0.00000 " + lon + "\"\n"
                + "    scale=\"1.00000 1.00000 1.00000\"\n"
                + "    rotation=\"1 0 0 1.57058\">\n"
                + "     <Shape>\n"
                + "     <Appearance> \n"
                + "     containerField='appearance'>\n"
                + "     <Material DEF='Red'\n"
                + "     containerField='material'\n"
                + "     ambientIntensity='.2'\n"
                + "     shininess='.2'\n"
                + "     diffuseColor='1 0 0'/>\n"
                + "     </Appearance>\n"
                + "     <Cylinder DEF='GeoCylinder1'\n"
                + "     containerField='geometry'\n"
                + "     height='2'\n"
                + "     radius='1'/>\n"
                + "     </Shape>\n"
                + "     </Transform>\n";

        return str;
    }

    private String writeCone(double lat, double lon) {
        /*
        String str = "<Transform translation=\"" + lat + " 2.00000 " + lon + "\">\n"
                + "  <Shape>\n"
                + " <Appearance>\n"
                + "    <Material diffuseColor='0 1 0'/>\n"
                + "  </Appearance>\n"
                + "  <Cone bottomRadius='2' height='5'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n";
         */
        String str = "<Transform "
                + "    translation=\"" + lat + " 0.00000 " + lon + "\">\n"
                + "    scale=\"1.00000 1.00000 1.00000\"\n"
                + "    rotation=\"1 0 0 0.0\">\n"
                + "    <Shape DEF='Extrusion1'>\n"
                + "    <Appearance\n"
                + "    containerField='appearance'>\n"
                + "    <Material DEF='Green-starboard'\n"
                + "    containerField='material'\n"
                + "    ambientIntensity='.2'\n"
                + "    shininess='0'\n"
                + "    diffuseColor='0 .4902 0'/>\n"
                + "    </Appearance>\n"
                + "    <Extrusion DEF='GeoExtrusion1'\n"
                + "    containerField='geometry'\n"
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

    private String writeWestBuoy(double lat, double lon) {
        
        String str = "<Group DEF='WestBuoy'>\n"
                + "<Transform translation=\"" + lat + " 2.00000 " + lon + "\">\n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.5 .75'>\n"
                + "  <Shape>\n"
                + " <Appearance>\n"
                + "    <Material diffuseColor='1 1 0.0'/>\n"
                + "  </Appearance>\n"
                + "  <Cone bottomRadius='2' height='5'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + " <Transform rotation='1 0 0 -1.57058' translation='0 -1.5 2'>\n"
                + "   <Shape>\n"
                + "    <Appearance>\n"
                + "       <Material diffuseColor='0 0 0.0'/>\n"
                + "   </Appearance>\n"
                + "    <Cone bottomRadius='2' height='5'/>\n"
                + " </Shape>\n"
                + " </Transform>\n"
                + " </Transform>\n"
                + "</Group>";
       /*
        String str = "<Group DEF='WestBuoy'>\n"
                + "<Transform translation=\"" + lat + " 2.00000 " + lon + "\">\n"
                + "<Transform DEF='dad_Extrusion2'\n"
                + "containerField='children'\n"
                + "translation='0 0 1.86606'>\n"
                + "<Shape DEF='Extrusion2'\n"
                + "containerField='children'>\n"
                + "<Appearance\n"
                + "containerField='appearance'>\n"
                + "<Material DEF='Black'\n"
                + "containerField='material'\n"
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
                + "containerField='children'\n"
                + "translation='.04576 0 -.22879'\n"
                + "rotation='0 1 0 3.142'>\n"
                + "<Shape DEF='Extrusion1'\n"
                + "containerField='children'>\n"
                + "<Appearance\n"
                + "containerField='appearance'>\n"
                + "<Material DEF='Yellow'\n"
                + "containerField='material'\n"
                + "ambientIntensity='.2'\n"
                + "shininess='.2'\n"
                + "diffuseColor='1 1 0'/>\n"
                + "</Appearance>\n"
                + "<Extrusion DEF='GeoExtrusion1'\n"
                + "containerField='geometry'\n"
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
*/
        return str;
    }

    private String writeSouthBuoy(double lat, double lon) {

        String str = "<Group DEF='SouthBuoy'>\n"
                + "<Transform translation=\"" + lat + " 2.00000 " + lon + "\" rotation='0 1 0 3.14116'>\n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.2 0'>\n"
                + "  <Shape>\n"
                + " <Appearance>\n"
                + "    <Material diffuseColor='1 1 0.0'/>\n"
                + "  </Appearance>\n"
                + "  <Cone bottomRadius='2' height='5'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.2 4'>\n"
                + "   <Shape>\n"
                + "    <Appearance>\n"
                + "       <Material diffuseColor='1 1 0.0'/>\n"
                + "   </Appearance>\n"
                + "    <Cone bottomRadius='2' height='5'/>\n"
                + " </Shape>\n"
                + " </Transform>\n"
                + " </Transform>\n"
                + "</Group>";
        return str;
    }

    private String writeNorthBuoy(double lat, double lon) {

        String str = "<Group DEF='SouthBuoy'>\n"
                + "<Transform translation=\"" + lat + " 2.00000 " + lon + "\" >\n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.2 0'>\n"
                + "  <Shape>\n"
                + " <Appearance>\n"
                + "    <Material diffuseColor='0 0 0.0'/>\n"
                + "  </Appearance>\n"
                + "  <Cone bottomRadius='2' height='5'/>\n"
                + "  </Shape>\n"
                + " </Transform>\n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.2 4'>\n"
                + "   <Shape>\n"
                + "    <Appearance>\n"
                + "       <Material diffuseColor='0 0 0.0'/>\n"
                + "   </Appearance>\n"
                + "    <Cone bottomRadius='2' height='5'/>\n"
                + " </Shape>\n"
                + " </Transform>\n"
                + " </Transform>\n"
                + "</Group>";
        return str;
    }

    private String writeEastBuoy(double lat, double lon) {

        String str = "<Transform translation=\"" + lat + " 2.00000 " + lon + "\">\n"
                + "<Group DEF='EastBuoy'> \n"
                + "<Transform rotation='1 0 0 1.57058' translation='0 -1.2 0'>\n"
                + "<Transform rotation='0 0 1 3.14116' >\n"
                + "<Shape>\n"
                + " <Appearance>\\\n"
                + "<Material diffuseColor='0 0 0.0'/>\n"
                + "  </Appearance>\n"
                + "  <Cone bottomRadius='2' height='5'/>\n"
                + "  </Shape>\n"
                + "            </Transform>\n"
                + "             </Transform>\n"
                + "            <Transform rotation='1 0 0 1.57058' translation='0 -1.2 5.5'>  \n"
                + "                <Shape>\n"
                + "                    <Appearance>\n"
                + "                        <Material diffuseColor='1 1 0.0'/>\n"
                + "                    </Appearance>\\n\"\n"
                + "                    <Cone bottomRadius='2' height='5'/>\n"
                + "                </Shape>\n"
                + "            </Transform>\n"
                + "        +</Group>\n"
                + " </Transform>\n";
        return str;
    }
}
