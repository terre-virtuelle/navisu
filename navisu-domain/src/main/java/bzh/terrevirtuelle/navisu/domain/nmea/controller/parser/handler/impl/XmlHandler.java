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
public class XmlHandler
        extends Handler {

    private Sentences sentences;
  

    public XmlHandler(Sentences sentences) {
        this.sentences = sentences;
    }

    /**
     * Get the value of sentences
     *
     * @return the value of sentences
     */
    public Sentences getSentences() {
        return sentences;
    }

    /**
     * Set the value of sentences
     *
     * @param sentences new value of sentences
     */
    public void setSentences(Sentences sentences) {
        this.sentences = sentences;
    }

    @Override
    public <T extends NMEA> void doIt(T data) {
        sentences.add(data);
    }
}
