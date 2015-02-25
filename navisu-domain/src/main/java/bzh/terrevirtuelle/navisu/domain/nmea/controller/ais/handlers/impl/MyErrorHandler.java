/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleInvalidInput;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMLine;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.VDMMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;

/**
 *
 * @author serge
 */
public class MyErrorHandler implements HandleInvalidInput {

    @Override
    public void handleInvalidVDMMessage(VDMMessage invalidVDMMessage) {
      //  System.err.println("Error VDM Message : " + invalidVDMMessage.getProvenance().getProvenanceTree(""));
    }

    @Override
    public void handleInvalidVDMLine(VDMLine invalidVDMLine) {
       // System.err.println("Error VDM Line : " + invalidVDMLine.getProvenance().getProvenanceTree(""));
    }

    @Override
    public void handleInvalidSensorData(Provenance source, String sensorData) {
       // System.err.println("Error sensor data : " + sensorData);
    }
}
