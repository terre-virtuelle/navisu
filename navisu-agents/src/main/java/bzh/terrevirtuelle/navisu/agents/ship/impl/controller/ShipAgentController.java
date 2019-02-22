/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.agents.ship.impl.controller;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;


/**
 * NaVisu project
 *
 * @date Jan 21, 2019 3:53:15 PM
 * @author Serge Morvan
 */
public class ShipAgentController {

    KmlComponentServices kmlComponentServices;
    private Ship ship;

    public ShipAgentController(KmlComponentServices kmlComponentServices) {
        this.kmlComponentServices = kmlComponentServices;
    }
    
}
