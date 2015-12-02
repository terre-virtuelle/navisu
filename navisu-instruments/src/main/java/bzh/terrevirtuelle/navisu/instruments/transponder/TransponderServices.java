/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.transponder;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge Morvan
 */
public interface TransponderServices
        extends ComponentService {

    void on();

    default void off() {
    }

    boolean isOn();

    void setShip(Ship ship);

    void transponderUpdateTargetEvent(Ship ship);
}
