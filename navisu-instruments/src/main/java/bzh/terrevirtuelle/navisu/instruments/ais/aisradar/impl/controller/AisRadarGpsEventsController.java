/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.ais.aisradar.impl.controller;

import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;

/**
 * NaVisu
 *
 * @date 19 juin 2015
 * @author Serge Morvan
 */
public class AisRadarGpsEventsController extends GpsEventsController{

    AisRadarController controller;

    public AisRadarGpsEventsController(AisRadarController controller) {
        this.controller = controller;
    }

    public void notifyNmeaMessage(GGA data) {
        controller.notifyNmeaMessage(data);
    }

    public void notifyNmeaMessage(VTG data) {
        controller.notifyNmeaMessage(data);
    }

    public void notifyNmeaMessage(RMC data) {
        controller.notifyNmeaMessage(data);
    }
}
