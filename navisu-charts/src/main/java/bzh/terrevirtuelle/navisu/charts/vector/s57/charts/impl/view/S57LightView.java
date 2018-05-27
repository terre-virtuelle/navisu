/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.view;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Lights;
import gov.nasa.worldwind.render.airspaces.PartialCappedCylinder;

/**
 *
 * @author Serge Morvan
 * @date 28 juin 2014 NaVisu project
 */
public class S57LightView
        extends PartialCappedCylinder
        implements S57Lights {

    private Lights light;

    private boolean tmp = false;

    /**
     * Get the value of tmp
     *
     * @return the value of tmp
     */
    public boolean isTmp() {
        return tmp;
    }

    /**
     * Set the value of tmp
     *
     * @param tmp new value of tmp
     */
    public void setTmp(boolean tmp) {
        this.tmp = tmp;
    }

    public S57LightView(Lights light) {
        this.light = light;
    }

    public S57LightView() {
    }

    public Lights getLight() {
        return light;
    }

}
