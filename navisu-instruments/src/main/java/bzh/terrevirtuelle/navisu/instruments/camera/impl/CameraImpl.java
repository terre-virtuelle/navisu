  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.camera.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.instruments.camera.Camera;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraServices;
import bzh.terrevirtuelle.navisu.instruments.camera.impl.controller.CameraController;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 * NaVisu
 *
 * @date 15 octobre 2015
 * @author Serge Morvan
 */
public class CameraImpl
        implements Camera, CameraServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "Camera";

    ComponentManager cm;

    private CameraController controller;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
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
        controller = CameraController.getInstance();
        controller.subscribe();
    }

    @Override
    public void off() {
        controller.unsubscribe();
    }

}
