/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion.impl.controller;

import bzh.terrevirtuelle.navisu.leapmotion.impl.controller.listener.LeapMotionListener;
import com.leapmotion.leap.Controller;

/**
 *
 * @author serge
 * @date Nov 16, 2016
 */
public class LeapMotionController {

    static private LeapMotionController INSTANCE;
    private final Controller controller;
    private final LeapMotionListener listener;

    public static LeapMotionController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LeapMotionController();
        }
        return INSTANCE;
    }

    private LeapMotionController() {
        controller = new Controller();
        listener = new LeapMotionListener();
        controller.addListener(listener);
    }

}
