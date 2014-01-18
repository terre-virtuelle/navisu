/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.controller.events;


import bzh.terrevirtuelle.navisu.nmea.model.NMEA;

/**
 *
 * @author Serge
 */
public interface NMEAEvent {

    public <T extends NMEA> void update(T data);
}
