/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.controller.loader;

import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.BasicShapeAttributes;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import gov.nasa.worldwind.render.SurfaceIcon;
import java.awt.Color;
//import gov.nasa.worldwindx.examples.util.ShapefileLoader;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Serge Morvan
 * @date 4 juin 2014 NaVisu project
 */
public class OBSTRN_CNT_ShapefileLoader
        extends ShapefileLoader {

    ShapefileRecord record;

    SurfaceIcon surfaceIcon = null;
    final String IMG_STR = "img/wrecks/danger02.png";

    public OBSTRN_CNT_ShapefileLoader() {
    }

    @SuppressWarnings({"UnusedDeclaration"})
    @Override
    protected Renderable createPoint(ShapefileRecord record, double latDegrees, double lonDegrees,
            PointPlacemarkAttributes attrs) {
        try {
            surfaceIcon = new SurfaceIcon(ImageIO.read(new File(IMG_STR)), new LatLon(LatLon.fromDegrees(latDegrees, lonDegrees)));
        } catch (IOException ex) {
            Logger.getLogger(OBSTRN_CNT_ShapefileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        surfaceIcon.setMaxSize(100.0);
        surfaceIcon.setMinSize(40.0);
        return surfaceIcon;
    }
    @Override
    protected ShapeAttributes createPolygonAttributes(ShapefileRecord record) {

        ShapeAttributes normalAttributes = new BasicShapeAttributes();
        normalAttributes.setDrawInterior(false);
        normalAttributes.setDrawOutline(true);
        normalAttributes.setOutlineMaterial(new Material(new Color(87,137,108)));
       
        normalAttributes.setEnableLighting(true);

        return normalAttributes;
    }
}
