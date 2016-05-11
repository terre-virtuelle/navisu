/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.base;

import bzh.terrevirtuelle.navisu.domain.devices.model.BaseStation;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import java.util.Calendar;
import java.util.Map;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge Morvan
 */
public interface AisServices
        extends ComponentService {

    void on();

    default void off() {
    }

    boolean isOn();

    Map<Integer, Ship> getShips();

    Map<Integer, BaseStation> getStations();

    Map<Integer, Calendar> getTimestamps();

    Map<Integer, String> getMidMap();

  //  void aisCreateTargetEvent(Ship ship);

  //  void aisUpdateTargetEvent(Ship ship);
}
