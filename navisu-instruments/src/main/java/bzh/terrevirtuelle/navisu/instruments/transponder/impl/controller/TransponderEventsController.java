/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.transponder.impl.controller;

import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;

/**
 *
 * @author serge
 */
public abstract class TransponderEventsController {
/*
    ComponentManager cm;
    ComponentEventSubscribe<TransponderUpdateTargetEvent> transponderUpdateTargetEvent;

    public TransponderEventsController() {

        cm = ComponentManager.componentManager;
        transponderUpdateTargetEvent = cm.getComponentEventSubscribe(TransponderUpdateTargetEvent.class);
    }

    public void subscribe() {
        transponderUpdateTargetEvent.subscribe((TransponderUpdateTargetEvent) (Ship ship) -> {
            updateTarget(ship);
        });
    }

    public void unsubscribe() {
        transponderUpdateTargetEvent.unsubscribe((TransponderUpdateTargetEvent) (Ship ship) -> {
        });
    }
*/
    public abstract void updateTarget(Ship ship);

}
