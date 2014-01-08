/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.controller.services;


import bzh.terrevirtuelle.navisu.nmea.model.NMEA;

/**
 *
 * @author Serge
 */
public interface NMEAService {

    public <T extends NMEA> void update(T data);
}
