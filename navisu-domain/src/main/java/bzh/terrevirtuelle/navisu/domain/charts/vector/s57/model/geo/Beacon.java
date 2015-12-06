/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.BCNSHP;

/**
 *
 * @author Serge Morvan
 * @date 2 oct. 2014 NaVisu project
 */
public class Beacon extends Buoyage {

    public Beacon() {
    }

    public Beacon(double lat, double lon) {
        super(lat, lon);
    }

    public Beacon(String wkt) {
        super(wkt);
    }

    public String getBeaconShape() {
        return shape;
    }

    public void setBeaconShape(String value) {
        shape = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *
     * @param shape
     * @return
     */
    @Override
    public String getShapeMeaning(String shape) {
        return super.getShapeMeaning(BCNSHP.ATT.get(shape));
    }
}
