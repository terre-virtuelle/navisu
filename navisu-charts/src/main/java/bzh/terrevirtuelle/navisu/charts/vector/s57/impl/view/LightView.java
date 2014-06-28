/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.impl.view;

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

    private final Light light;

    public LightView(Light light) {
        this.light = light;
    }

  
}
