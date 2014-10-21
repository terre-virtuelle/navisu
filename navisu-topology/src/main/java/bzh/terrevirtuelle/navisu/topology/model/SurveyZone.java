/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.topology.model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import java.util.List;

/**
 *
 * @author Serge Morvan
 * @date 7 oct. 2014 NaVisu project
 */
public class SurveyZone {

    private Polygon polygon;
    private final String acronym;
    private final String objname;

    public SurveyZone(String acronym, String objname, List<double[]> vertices) {
        this.acronym = acronym;
        this.objname = objname;
        Coordinate[] coordinates0 = new Coordinate[vertices.size() + 1];
        int i = 0;
        for (double[] t : vertices) {
            coordinates0[i] = new Coordinate(t[0], t[1], 0.0);
            i++;
        }
        coordinates0[i] = new Coordinate(coordinates0[0].x, coordinates0[0].y, 0.0);// Le poly doit être fermé
        polygon = new GeometryFactory().createPolygon(new GeometryFactory().createLinearRing(coordinates0), null);

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
        return acronym;
    }

    public String getObjname() {
        return objname;
    }
    
}
