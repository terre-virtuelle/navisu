/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.stl.impl.writer.charts;

import bzh.terrevirtuelle.navisu.charts.util.WwjGeodesy;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.loader.NAVLNE_ShapefileLoader;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.formats.shapefile.Shapefile;
import gov.nasa.worldwind.geom.LatLon;
import gov.nasa.worldwind.render.Polygon;
import gov.nasa.worldwind.render.Renderable;
import gov.nasa.worldwind.render.ShapeAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 * @date Apr 5, 2017
 */
public class NAVLNE_Stl_ShapefileWriter
        extends NAVLNE_ShapefileLoader {

    Geometry geometryEnveloppe;
    Polygon polygon;
    String result = "";

    public NAVLNE_Stl_ShapefileWriter(Geometry geometryEnveloppe, Polygon polygon) {
        super();
        this.geometryEnveloppe = geometryEnveloppe;
        this.polygon = polygon;
    }

    @Override
    protected Renderable createPolyline(Shapefile shp, ShapeAttributes attrs) {
        super.createPolyline(shp, attrs);
        /*
        List<Integer> numberOfPoints = new ArrayList<>();
        while (shp.hasNext()) {
            record = shp.nextRecord();
            records.add(record);
            numberOfPoints.add(record.getNumberOfPoints());
        }
         */
        List<LatLon> latLon = new ArrayList<>();
        for (LatLon l : shp.getPointBuffer().getLocations()) {
            latLon.add(l);
        }

        System.out.println("latLon : " + latLon);

        return null;
    }

    protected void createX3D(List<LatLon> latLon) {
        int size = latLon.size() - 1;
        for (int i = 0; i < size; i++) {
           /* 
            positionList.stream().map((p) -> WwjGeodesy.getXYM(orig, p)).forEachOrdered((xy) -> {
            double x = 200 - xy.getX()*11.85;//*11.85;
            double y = -200 + xy.getY()*11.85;//*11.85;
            result += x + " " + y + (",");

        });
            */
           /*
            writeCylinder(latLon.get(i).getLatitude().getDegrees(),
                    latLon.get(i).getLongitude().getDegrees(),
                    latLon.get(i + 1).getLatitude().getDegrees(),
                    latLon.get(i + 1).getLongitude().getDegrees());
*/
        }
    }

    private String writeCylinder(double lat0, double lon0, double lat1, double lon1) {
        String str = "<Transform \n"
                + "   translation=\"" + lat0 + " 0.0000 " + lon0 + "\"\n"
                + "    scale=\"1.00000 1.00000 1.00000\"\n"
                + "    rotation=\"1.0 0.0 0.0 1.57058\">\n"
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
        return str;
    }

    public String compute() {

        return result;
    }
}
