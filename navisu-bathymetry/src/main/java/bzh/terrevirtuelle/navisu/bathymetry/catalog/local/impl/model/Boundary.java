/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.catalog.local.impl.model;

import bzh.terrevirtuelle.navisu.util.Pair;
import java.util.List;

/**
 *
 * @author Serge
 */
public class Boundary {

    private List<Pair<Double, Double>> polygon;

    /**
     * Get the value of polygon
     *
     * @return the value of polygon
     */
    public List<Pair<Double, Double>> getPolygon() {
        return polygon;
    }

    /**
     * Set the value of polygon
     *
     * @param polygon new value of polygon
     */
    public void setPolygon(List<Pair<Double, Double>> polygon) {
        this.polygon = polygon;
    }

}
