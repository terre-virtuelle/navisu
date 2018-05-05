/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BOYSHP;

/**
 *
 * @author Serge Morvan
 * @date 2 oct. 2014 NaVisu project
 */
public abstract class Buoy 
        extends Buoyage {

    public String getBuoyShape() {
        return shape;
    }

    public void setBuoyShape(String value) {
        shape = value;
    }

    /**
     *
     * @param shape
     * @return
     */
    @Override
    public String getShapeMeaning(String shape) {
        return super.getShapeMeaning(BOYSHP.ATT.get(shape));
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
