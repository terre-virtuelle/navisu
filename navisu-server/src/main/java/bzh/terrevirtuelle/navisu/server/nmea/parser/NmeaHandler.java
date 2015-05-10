 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.server.nmea.parser;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;

/**
 *
 * @author Serge
 */
public class NmeaHandler extends Handler{
     private Sentences sentences;

    public NmeaHandler() {
    }

    public NmeaHandler(Sentences sentences) {
        this.sentences = sentences;
    }

    @Override
    public <T extends NMEA> void doIt(T t) {  
        sentences.add(t);
    }

     @Override
    public Sentences getSentences() {
        return sentences;
    }
}
