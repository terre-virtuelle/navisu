/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;

/**
 *
 * @author Serge Morvan
 */
public class PrintHandler
        extends Handler {

    @Override
    public <T extends NMEA> void doIt(T data) {
       // System.out.println(data.getSentence());
        // System.out.println(data.getChecksumValidation());
        // System.out.println(data.getClass());
       // System.out.println(data);
    }

    @Override
    public Sentences getSentences() {
       return null;
    }
}
