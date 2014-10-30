/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology.surveyZone.model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import gov.nasa.worldwind.formats.shapefile.ShapefileRecord;
import gov.nasa.worldwind.render.SurfacePolygons;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 7 oct. 2014 NaVisu project
 */
public class SurveyZone {

    private Polygon polygon;
    private final SurfacePolygons shape;
    private final Coordinate[] coordinates;

    public SurveyZone(SurfacePolygons shape, ShapefileRecord record) {
        this.shape = shape;
        List<double[]> vertices = new ArrayList<>();
        Iterable<double[]> coords = record.getCompoundPointBuffer().getCoords();
        for (double[] c : coords) {
            vertices.add(c);
        }
        coordinates = new Coordinate[vertices.size() + 1];
        int i = 0;
        for (double[] t : vertices) {
            coordinates[i] = new Coordinate(t[0], t[1], 0.0);
            i++;
        }
        coordinates[i] = new Coordinate(coordinates[0].x, coordinates[0].y, 0.0);// Le poly doit être fermé
        polygon = new GeometryFactory().createPolygon(new GeometryFactory().createLinearRing(coordinates), null);

    }

    /**
     * Get the value of polygon
     *
     * @return the value of polygon
     */
    public Polygon getPolygon() {
        return polygon;
    }

    /**
     * Set the value of polygon
     *
     * @param polygon new value of polygon
     */
    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

    public boolean contains(double lat, double lon) {
        return polygon.contains(new GeometryFactory().createPoint(new Coordinate(lon, lat)));
    }

    public String getAcronym() {
        return (String) shape.getValue("ACRONYM");
    }

    public String getObjname() {
        return (String) shape.getValue("OBJNAM");
    }

}
