/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

/**
 *
 * @author Serge
 */
public class NewClass {

    public NewClass() {
        AIS1 ais1 = new AIS1();
        ais1.setDevice(null);
        ais1.setMMSI(0);
        ais1.setNavigationalStatus(0);
        ais1.setRot(0.0f);
        ais1.setSog(0.0f);
        ais1.setLongitude(0.0f);
        ais1.setLatitude(0.0f);
        ais1.setCog(0.0f);
        ais1.setHeading(0.0f);
        ais1.setSecond(0);

        AIS4 ais4 = new AIS4();
        ais4.setDevice(null);
        ais4.setMMSI(0);
        ais4.setDate(null);
        ais4.setLatitude(0.0f);
        ais4.setLongitude(0.0f);
        
        AIS5 ais5 = new AIS5();
        ais5.setDevice(null);
        ais5.setMMSI(0);
        ais5.setImo(0);
        ais5.setCallsign(null);
        ais5.setShipname(null);
        ais5.setShipType(0);
        ais5.setLength(0);
        ais5.setWidth(0);
        ais5.setETA(null);
        ais5.setDraught(0.0f);
        ais5.setDestination(null);
        
        AIS18 ais18 = new AIS18();
        ais18.setSog(0);
        ais18.setLatitude(0.0f);
        ais18.setLongitude(0.0f);
        ais18.setCog(0);
        ais18.setTrueHeading(0);
        ais18.setSecond(0);
        
        
    }

}
