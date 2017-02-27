/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.vector.s57.charts.impl.controller.loader;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.DEPARE_ShapefileLoader;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
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

    protected String WRL_FILE = "out.wrl";
    protected static int nb = 0;
    protected static float depth = 0;
    protected static boolean created = false;

    public DEPARE_Stl_ShapefileLoader() {
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        normalAttributes = super.createPolygonAttributes(record);
        nb++;
        val1 = 51 - val1;
        List<LatLon> pts = addRenderablesForExtrudedPolygons(record.getShapeFile(), val1, val2);

        if (!pts.isEmpty() && val1 != 0) {
            String txt = "Shape {\n"
                    + "      appearance Appearance {\n"
                    + "    material Material {\n"
                    + "      diffuseColor"
                    + " " + color.getRed() / 2.56
                    + " " + color.getGreen() / 2.56
                    + " " + color.getBlue() / 2.56
                    + "\n"
                    + "    } \n"
                    + "  }\n"
                    + "        geometry Extrusion {\n"
                    + "            solid TRUE\n"
                    + "            endCap TRUE\n"
                    + "            crossSection [\n";
            txt = pts.stream().map((ll) -> ll.getLatitude().getDegrees() * 111120 + " "
                    + ll.getLongitude().getDegrees() * 111120 + ", ").reduce(txt, String::concat);
            txt += "\n] "
                    + "spine ["
                    + "0 0 0, 0 " + val1 * 10 + " 0]"
                    + "} \n"
                    + "        }\n";
            try {
                Files.write(Paths.get(WRL_FILE), txt.getBytes(), APPEND);
            } catch (IOException ex) {
                Logger.getLogger(DEPARE_Stl_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
}
