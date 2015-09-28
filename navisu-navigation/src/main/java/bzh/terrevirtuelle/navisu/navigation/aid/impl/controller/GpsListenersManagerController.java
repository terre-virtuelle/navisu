/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.aid.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.model.ExtendedBuoyage;
import java.util.List;

/**
 * NaVisu
 *
 * @date 28 sept. 2015
 * @author Serge Morvan
 */
public class GpsListenersManagerController {

    private static final GpsListenersManagerController INSTANCE;
    static {
        INSTANCE = new GpsListenersManagerController();
    }
    protected List<ExtendedBuoyage> buoyages;

    public static GpsListenersManagerController getInstance() {
        return INSTANCE;
    }

}
