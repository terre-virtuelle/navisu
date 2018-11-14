/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.util.time;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 */
public class Log {

    public static void in(Object object, String comment) {
        Logger.getLogger(object.getClass().getTypeName()).log(Level.INFO,comment);
    }
}
