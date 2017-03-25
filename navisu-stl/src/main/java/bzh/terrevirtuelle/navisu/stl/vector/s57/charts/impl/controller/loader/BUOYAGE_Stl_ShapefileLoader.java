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

    public BUOYAGE_Stl_ShapefileLoader(Polygon polygon,
            double scaleLatFactor, double scaleLonFactor, double squareSide,
            boolean dev, String path, Map<Pair<Double, Double>, String> topMarks, String marsys, String acronym, Set<S57Controller> s57Controllers) {
        super(dev, path, topMarks, marsys, acronym, s57Controllers);
        positions = polygon.getBoundaries().get(0);
        this.scaleLatFactor = scaleLatFactor;
        this.scaleLonFactor = scaleLonFactor;
        this.squareSide = squareSide;
        
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Renderable createPoint(ShapefileRecord record,
            double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        super.createPoint(record, latDegrees, lonDegrees, attrs);

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
        } else if (acronym.contains("LAT")) {
            catMark = CATLAM.ATT.get(object.getCategoryOfMark());
            if (catMark.contains("Starboard")) {
                result += writeCone(lonMetric, latMetric);
            } else {
                if (catMark.contains("Port")) {
                    result += writeCylinder(lonMetric, latMetric);
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
        return placemark;
    }

    public String compute() {
        return result;
    }

    private String writeCylinder(double lat, double lon) {
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
                + "          <Cylinder/>\n"
                + "    </Shape>\n"
                + "</Transform>\n";
        return str;
    }

    private String writeCone(double lat, double lon) {
        String str = "<Transform \n"
                + "		           translation=\"" + lat + " 1.00000 " + lon + "\"\n"
                + "		           scale=\"0.5000000 0.5000000 0.5000000\"\n"
                + "		           rotation=\"0.000000 0.000000 0.000000 0\">\n"
                + "				<Group DEF=\"catMark LAT Starboard\">\n"
                + "					<Shape>\n"
                + "						<Appearance>\n"
                + "							<Material DEF=\"MA_Shape\"\n"
                + "							          diffuseColor=\"0.00 1.00 0.00\"\n"
                + "							          specularColor=\"0.000 1.000 0.000\"\n"
                + "							          emissiveColor=\"0.000 1.000 0.000\"\n"
                + "							          ambientIntensity=\"0.0\"\n"
                + "							          shininess=\"1.\"\n"
                + "							          transparency=\"0.0\"\n"
                + "							          />\n"
                + "						</Appearance>\n"
                + "						<IndexedFaceSet solid=\"true\"\n"
                + "						                coordIndex=\"2 0 1 -1 3 0 2 -1 4 0 3 -1 5 0 4 -1 6 0 5 -1 7 0 6 -1 8 0 7 -1 9 0 8 -1 10 0 9 -1 11 0 10 -1 12 0 11 -1 13 0 12 -1 14 0 13 -1 15 0 14 -1 16 0 15 -1 1 0 16 -1 1 16 15 -1 15 14 13 -1 13 12 11 -1 11 10 9 -1 9 8 7 -1 7 6 5 -1 5 4 3 -1 3 2 1 -1 1 15 13 -1 13 11 9 -1 9 7 5 -1 5 3 1 -1 1 13 9 -1 9 5 1 -1 \"\n"
                + "						                >\n"
                + "							<Coordinate DEF=\"coords_ME_Shape_Cone\"\n"
                + "							            point=\"0.000000 2.660725 0.000000 -0.000000 -2.660725 -3.334500 -1.276058 -2.660725 -3.080676 -2.357847 -2.660725 -2.357847 -3.080676 -2.660725 -1.276058 -3.334500 -2.660725 -0.000000 -3.080676 -2.660725 1.276058 -2.357847 -2.660725 2.357847 -1.276058 -2.660725 3.080676 -0.000000 -2.660725 3.334500 1.276058 -2.660725 3.080676 2.357847 -2.660725 2.357847 3.080676 -2.660725 1.276058 3.334500 -2.660725 0.000000 3.080676 -2.660725 -1.276058 2.357847 -2.660725 -2.357847 1.276058 -2.660725 -3.080676 \"\n"
                + "							            />\n"
                + "						</IndexedFaceSet>\n"
                + "					</Shape>\n"
                + "				</Group>\n"
                + "		</Transform>\n";
        return str;
    }
}
