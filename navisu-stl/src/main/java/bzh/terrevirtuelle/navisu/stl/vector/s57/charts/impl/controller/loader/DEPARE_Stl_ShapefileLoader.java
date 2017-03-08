/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.util.WwjJTS;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPARE_ShapefileLoader;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge Morvan
 * @date 26 fev 2017 NaVisu project
 */
public class DEPARE_Stl_ShapefileLoader
        extends DEPARE_ShapefileLoader {

    protected String outFilename;
    protected static int nb = 0;
    protected static float depth = 0;
    protected static boolean created = false;
    protected double latOrg;
    protected double lonOrg;
    protected boolean first = true;
    protected Polygon polyEnveloppe;
    protected Geometry geometryEnveloppe;

    public DEPARE_Stl_ShapefileLoader(String filename, Polygon polyEnveloppe) {
        this.outFilename = filename;
        this.polyEnveloppe = polyEnveloppe;
        List<? extends Position> positions = polyEnveloppe.getBoundaries().get(0);
        System.out.println("polygon.getBoundaries().get(0) : " + polyEnveloppe.getBoundaries().get(0));
        System.out.println("0 : " + positions.get(0).getLatitude().getDegrees() + " " + positions.get(0).getLongitude().getDegrees());
        System.out.println("1 : " + positions.get(1).getLatitude().getDegrees() + " " + positions.get(1).getLongitude().getDegrees());
        System.out.println("2 : " + positions.get(2).getLatitude().getDegrees() + " " + positions.get(2).getLongitude().getDegrees());
        System.out.println("3 : " + positions.get(3).getLatitude().getDegrees() + " " + positions.get(3).getLongitude().getDegrees());
        latOrg = positions.get(0).getLatitude().getDegrees();
        lonOrg = positions.get(0).getLongitude().getDegrees();
        String wkt = WwjJTS.toPolygonWkt(positions);
        WKTReader wktReader = new WKTReader();

        if (wkt != null) {
            try {
                geometryEnveloppe = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        normalAttributes = super.createPolygonAttributes(record);
        nb++;
        val1 = 51 - val1;
        List<LatLon> pts = addRenderablesForExtrudedPolygons(record.getShapeFile(), val1, val2);
        Geometry geo = filter(pts);
        List<Position> ptsFiltered = WwjJTS.wktPolygonToPositionList(geo.toText());
        System.out.println("ptsFiltered " + ptsFiltered.size());

        if (!ptsFiltered.isEmpty() && val1 != 0) {
            String txt = " <Shape>\n"
                    + "<Appearance>\n"
                    + " <Material diffuseColor='1.0 0.3 0'/> \n"
                    + "</Appearance>\n"
                    + "<Extrusion convex='false' \n"
                    + " crossSection='";
            txt = ptsFiltered.stream().map((ll) -> ll.getLatitude().getDegrees() * 111120 + " "
                    + ll.getLongitude().getDegrees() * 111120 + ", ").reduce(txt, String::concat);

            txt += "'\n "
                    + "solid='true' \n"
                    + "spine='0 0 0 0 "
                    + val1 * 10 + " 0'/>\n"
                    // + 10 + " 0'/>\n"
                    + "</Shape>\n";
            try {
                Files.write(Paths.get(outFilename), txt.getBytes(), APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
        /*
        if (!pts.isEmpty() && val1 != 0) {
            String txt = " <Shape>\n"
                    + "<Appearance>\n"
                    + " <Material diffuseColor='1.0 0.3 0'/> \n"
                    + "</Appearance>\n"
                    + "<Extrusion convex='false' \n"
                    + " crossSection='";
            txt = pts.stream().map((ll) -> ll.getLatitude().getDegrees() * 111120 + " "
                    + ll.getLongitude().getDegrees() * 111120 + ", ").reduce(txt, String::concat);

            txt += "'\n "
                    + "solid='true' \n"
                    + "spine='0 0 0 0 "
                    + val1 * 10 + " 0'/>\n"
                    // + 10 + " 0'/>\n"
                    + "</Shape>\n";
            try {
                Files.write(Paths.get(outFilename), txt.getBytes(), APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
        }
         */
        return normalAttributes;
    }

    protected List<LatLon> addRenderablesForExtrudedPolygons(Shapefile shp, float val1, float val2) {
        ExtrudedPolygon ep = new ExtrudedPolygon();
        List<LatLon> pts = new ArrayList<>();
        for (int i = 0; i < record.getNumberOfParts(); i++) {
            VecBuffer buffer = record.getCompoundPointBuffer().subBuffer(i);
            if (WWMath.computeWindingOrderOfLocations(buffer.getLocations()).equals(AVKey.CLOCKWISE)) {
                if (!ep.getOuterBoundary().iterator().hasNext()) // has no outer boundary yet
                {
                    if (val1 >= 0 && val2 >= 0) {
                        ep.setOuterBoundary(buffer.getLocations());
                        Iterator<? extends LatLon> iterator = ep.getOuterBoundary().iterator();
                        while (iterator.hasNext()) {
                            pts.add(iterator.next());
                        }
                        ep.setHeight(val1);
                        //   layer.addRenderable(ep);
                    }
                } else {
                    ep.setOuterBoundary(record.getCompoundPointBuffer().getLocations());
                }
            } else {
                ep.addInnerBoundary(buffer.getLocations());
            }
        }
        return pts;
    }

    private Geometry filter(List<LatLon> pts) {
        String wkt = WwjJTS.toPolygonWkt1(pts);
        WKTReader wktReader = new WKTReader();
        Geometry polygon = null;
        if (wkt != null) {
            try {
                polygon = wktReader.read(wkt);
            } catch (com.vividsolutions.jts.io.ParseException ex) {
                Logger.getLogger(WwjJTS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Geometry geometryFiltered = geometryEnveloppe.intersection(polygon);
        return geometryFiltered;
    }
}
