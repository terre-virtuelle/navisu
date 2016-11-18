/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.leapmotion.LeapMotionComponent;
import bzh.terrevirtuelle.navisu.leapmotion.LeapMotionComponentServices;
import bzh.terrevirtuelle.navisu.leapmotion.impl.listener.LeapMotionComponentListener;
import com.leapmotion.leap.Controller;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author serge
 * @date Nov 16, 2016
 */
public class LeapMotionComponentImpl
        implements LeapMotionComponent, LeapMotionComponentServices,
        InstrumentDriver, ComponentState {

    private final String NAME = "LeapMotion";
    private final LeapMotionComponentListener listener = new LeapMotionComponentListener();
    private Controller controller = null;

    @Override
    public void componentInitiated() {
        if(controller == null)
            controller = new Controller();
    }

    @Override
    public void componentStarted() {
        // Here or in componentInitiated()?
        controller.addListener(listener);
    }

    @Override
    public void componentStopped() {
        controller.removeListener(listener);
    }

    @Override
    public void on(String... files) {
        System.out.println("leapMotion ON");
    }

    @Override
    public void off() {
        System.out.println("leapMotion OFF");
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
