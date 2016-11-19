/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.leapmotion.LeapMotionComponent;
import bzh.terrevirtuelle.navisu.leapmotion.LeapMotionComponentServices;
import bzh.terrevirtuelle.navisu.leapmotion.impl.controller.LeapMotionController;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 * @date Nov 16, 2016
 */
public class LeapMotionComponentImpl
        implements LeapMotionComponent, 
        LeapMotionComponentServices,
        InstrumentDriver, ComponentState {

    private final String NAME = "LeapMotion";
    private LeapMotionController leapMotionController;

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
    public void on(String... files) {
        leapMotionController = LeapMotionController.getInstance();
    }

    @Override
    public void off() {
        leapMotionController = null;
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

}
