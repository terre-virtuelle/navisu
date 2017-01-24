/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion.impl.controller;

import bzh.terrevirtuelle.navisu.leapmotion.impl.controller.listener.LeapMotionListener;
import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Gesture;

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
            System.out.println( "Création instance" );
        }
        return INSTANCE;
    }
    
    public static LeapMotionController closeInstance() {
        System.out.println( "Fermeture instance" );
        INSTANCE = null;
        return INSTANCE;
    }

    private LeapMotionController() {
        controller = new Controller();
        listener = new LeapMotionListener();
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.addListener(listener);
    }

}
