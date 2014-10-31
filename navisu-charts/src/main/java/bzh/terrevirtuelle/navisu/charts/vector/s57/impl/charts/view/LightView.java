/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.charts.view;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo.Light;
import gov.nasa.worldwind.render.airspaces.PartialCappedCylinder;

/**
 *
 * @author Serge Morvan
 * @date 28 juin 2014 NaVisu project
 */
public class LightView
        extends PartialCappedCylinder
        implements Lights {

    private Light light;

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

    public LightView(Light light) {
        this.light = light;
    }

    public LightView() {
    }

    public Light getLight() {
        return light;
    }

}
