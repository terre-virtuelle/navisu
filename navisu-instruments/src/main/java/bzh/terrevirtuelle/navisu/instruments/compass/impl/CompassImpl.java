/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.compass.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.HDGEvent;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.instruments.compass.Compass;
import bzh.terrevirtuelle.navisu.instruments.compass.CompassServices;
import bzh.terrevirtuelle.navisu.instruments.compass.impl.controller.CompassController;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 31 mars 2015
 * @author Serge Morvan
 */
public class CompassImpl
        implements Compass, CompassServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "Compass";
    @UsedService
    GuiAgentServices guiAgentServices;
    ComponentManager cm;
    ComponentEventSubscribe<HDGEvent> hdgEvent;
    boolean first = true;
    private CompassController controller;

    @Override
    public void componentInitiated() {
        controller = new CompassController(this, KeyCode.T, KeyCombination.CONTROL_DOWN);
        cm = ComponentManager.componentManager;
        hdgEvent = cm.getComponentEventSubscribe(HDGEvent.class);
    }

    @Override
    public boolean canOpen(String category) {

        return category.equals(KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void on(String... files) {
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().add(controller); //Par defaut le radar n'est pas visible Ctrl-A
        controller.setVisible(true);
        hdgEvent.subscribe(new HDGEvent() {

            @Override
            public <T extends NMEA> void notifyNmeaMessageChanged(T d) {
                HDG data = (HDG) d;
                controller.notifyNmeaMessageChanged(data);
            }
        });
    }


@Override
        public void off() {
        //if (first == false) {
            guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, controller);
            guiAgentServices.getRoot().getChildren().remove(controller);
            controller.setVisible(false);
            controller.stop();
        //}
    }

    @Override
        public void componentStarted() {
    }

    @Override
        public void componentStopped() {
    }

}
