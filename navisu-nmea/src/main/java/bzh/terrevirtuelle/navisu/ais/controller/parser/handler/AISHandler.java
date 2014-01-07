/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.ais.controller.parser.handler;

import bzh.terrevirtuelle.navisu.ais.model.AISMessage;

/**
 *
 * @author morvan
 */
public abstract class AISHandler {

    /**
     *
     */
    public AISHandler() {
    }

    /**
     *
     */
    public abstract void doIt(AISMessage message);

}
