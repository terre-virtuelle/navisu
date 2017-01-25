/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.common.controller;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;

/**
 *
 * @author serge
 * @date Jan 24, 2017
 */
public interface GpsEventsListener {

    public default void updateTarget(Ship ship) {
    }
}
