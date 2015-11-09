/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.logger.impl.controller;

import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import bzh.terrevirtuelle.navisu.instruments.common.controller.AisEventsController;

/**
 * NaVisu
 *
 * @date 18 juin 2015
 * @author Serge Morvan
 */
public class AisLoggerAisEventsController
        extends AisEventsController {

    public AisLoggerAisEventsController() {
    }

    @Override
    public void createTarget(Ship ship) {
        System.out.println("createTarget " + ship);
    }

    @Override
    public void updateTarget(Ship ship) {
        System.out.println("updateTarget " + ship);
    }

    @Override
    public void deleteTarget(Ship ship) {
        System.out.println("deleteTarget " + ship);
    }

    @Override
    public void createBaseStation(BaseStation baseStation) {
        System.out.println("createBaseStation " + baseStation);
    }

    @Override
    public void updateBaseStation(BaseStation baseStation) {
        System.out.println("updateBaseStation " + baseStation);
    }

    @Override
    public void deleteBaseStation(BaseStation baseStation) {
        System.out.println("deleteBaseStation " + baseStation);
    }
}
