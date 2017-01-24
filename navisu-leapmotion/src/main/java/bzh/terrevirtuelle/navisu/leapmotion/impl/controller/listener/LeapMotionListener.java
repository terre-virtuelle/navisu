/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.leapmotion.impl.controller.listener;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.GestureList;
import com.leapmotion.leap.Listener;

/**
 *
 * @author serge
 * @date Nov 16, 2016
 */
public class LeapMotionListener
        extends Listener {
  
    

    /**
     * Called remotely when the Leap Motion controller is successfully connected
     *
     * @param controller
     */
    @Override
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    /**
     * Called remotely at each frame that the controller receives
     *
     * @param controller
     */
    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        if (frame.gestures().count() > 0) {
            System.out.println("Nombre de gesture : " + frame.gestures().count());
            for(Gesture gesture : frame.gestures())
            {
                switch (gesture.type()) {
                    case TYPE_CIRCLE:
                        //Handle circle gestures
                        System.out.println("Handle circle gestures");
                        break;
                    case TYPE_KEY_TAP:
                        //Handle key tap gestures
                        System.out.println("Handle key tap gestures");
                        break;
                    case TYPE_SCREEN_TAP:
                        //Handle screen tap gestures
                        System.out.println("Handle screen tap gestures");
                        break;
                    case TYPE_SWIPE:
                        //Handle swipe gestures
                        System.out.println("Handle swipe gestures");
                        break;
                    default:
                        //Handle unrecognized gestures
                        System.out.println("Geste non reconnu");
                        break;
                }
            }
        }
               

        }
}
