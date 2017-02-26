/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.ExtrudedPolygon;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.util.VecBuffer;
import gov.nasa.worldwind.util.WWMath;
import java.awt.Color;
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
 * @date 4 juin 2014 NaVisu project
 */
public class DEPARE_ShapefileLoader
        extends LayerShapefileLoader
        implements S57ShapeFileLoader {

    static int nb = 0;
    static float depth = 0;
    static boolean created = false;
    ShapefileRecord record;

    public DEPARE_ShapefileLoader() {
    }

    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {
        /*
        OutputStream stream = null;
        XMLOutputFactory xof = XMLOutputFactory.newFactory();
        XMLStreamWriter xsw;
        try {
            stream = new FileOutputStream("out" + nb++ + ".vrml");
            xsw = xof.createXMLStreamWriter(stream);
            record.exportAsXML(xsw);//ou XML
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(DEPARE_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
        this.record = record;
        Float val1 = new Float(record.getAttributes().getValue("DRVAL1").toString());
        Float val2 = new Float(record.getAttributes().getValue("DRVAL2").toString());

        Color color = new Color(159, 215, 247);

        if (val1 == -9.0 && val2 <= 0.0) {
            color = new Color(151, 199, 0);
        }
        if (val1 >= -14.0 && val2 <= 0.0) {
            // color = new Color(151, 199, 0);
            color = new Color(87, 137, 108);
        }
        if (val1 >= 0.0 && val2 <= 12.0) {
            color = new Color(91, 175, 247);
        }
        if (val1 >= 0.0 && val2 <= 8.0) {
            //color = new Color(31, 175, 247);
            color = new Color(115, 182, 239);
        }

        if (val1 >= 0.0 && val2 <= 3.0) {
            // color = new Color(33, 255, 242);
            // color = new Color(115, 182, 239);
            color = new Color(31, 175, 247);
        }

        if (val1 == 5.0 && val2 <= 10.0) {
            color = new Color(159, 215, 247);
        }
        if (val1 >= 5.0 && val2 <= 25.0) {//20.0
            color = new Color(159, 215, 247);
        }
        if (val1 == 10.0 && val2 <= 20.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 10.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 20.0 && val2 <= 30.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 15.0 && val2 <= 50.0) {
            color = new Color(129, 195, 226);
        }
        if (val1 == 30.0 && val2 <= 50.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 == 50.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val1 >= 20.0 && val2 <= 5000.0) {
            color = new Color(247, 247, 247);
        }
        if (val2 >= 100.0) {
            color = new Color(247, 247, 247);
        }
        // pour une mer bleue, en mode nav
        /* 
         if (val1 >= -20.0 && val2 <= 5000.0) {
         color = new Color(10, 38, 51);
         }
         */

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setInteriorMaterial(new Material(color));
        normalAttributes.setDrawOutline(false);
        //  normalAttributes.setImageSource(getClass().getResourceAsStream("img/mer.jpg"));
        nb++;
        
        val1 = 51 - val1;
        List<LatLon> pts = addRenderablesForExtrudedPolygons(record.getShapeFile(), val1, val2);

        if (!pts.isEmpty() && val1 != 0) {
            String txt = "#VRML V2.0 utf8\n"
                    + "#- Exemple 1 -\n"
                    + "\n"
                    + "NavigationInfo {\n"
                    + "\n"
                    + "    type \"EXAMINE\"\n"
                    + "\n"
                    + "}"
                    + "Shape {\n"
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
                    //  + "0 0 0, 0 1 0]"
                    + "} \n"
                    + "        }\n";
            try {
                if (created == false) {
                    Files.write(Paths.get("out.wrl"), txt.getBytes(), CREATE);
                    created = true;
                } else {
                    Files.write(Paths.get("out.wrl"), txt.getBytes(), APPEND);
                }
            } catch (IOException ex) {
                Logger.getLogger(DEPARE_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return normalAttributes;
    }

    @Override
    protected ShapeAttributes createPolylineAttributes(ShapefileRecord record) {
        this.record = record;

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(Material.BLACK);
        normalAttributes.setOutlineWidth(2.0);
        return normalAttributes;
    }

    @Override
    public ShapefileRecord getRecord() {
        return record;
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
