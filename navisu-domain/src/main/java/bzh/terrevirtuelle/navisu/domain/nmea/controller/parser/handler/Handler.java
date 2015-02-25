/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;

/**
 *
 * @author Serge Morvan
 */
public abstract class Handler {

    public abstract Sentences getSentences();

    public abstract <T extends NMEA> void doIt(T data);
}
