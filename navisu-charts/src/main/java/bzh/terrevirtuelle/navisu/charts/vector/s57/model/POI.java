/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.model;

import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;

/**
 * NaVisu
 *
 * @date 29 sept. 2015
 * @author Serge Morvan
 */
public abstract class POI
        extends GpsEventsController {

    public abstract void activate();

    public abstract void deactivate();
}
