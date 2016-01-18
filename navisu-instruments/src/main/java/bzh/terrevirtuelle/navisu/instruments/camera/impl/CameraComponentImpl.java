/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.camera.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.instruments.camera.impl.controller.CameraComponentController;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponent;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponentServices;

/**
 * NaVisu
 *
 * @date 15 octobre 2015
 * @author Serge Morvan
 */
public class CameraComponentImpl
        implements CameraComponent, CameraComponentServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "Camera";
    private CameraComponentController controller;


    @Override
    public void componentInitiated() {
        controller = CameraComponentController.getInstance();
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
        controller.subscribe();
    }

    @Override
    public void off() {
        controller.unsubscribe();
    }

    @Override
    public void updateTarget(Camera camera) {
        controller.updateTarget(camera);
    }

}
