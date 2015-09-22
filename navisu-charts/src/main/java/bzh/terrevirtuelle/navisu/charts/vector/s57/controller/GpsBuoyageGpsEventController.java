/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.controller;

import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.GpsPlotterController;

/**
 * NaVisu
 *
 * @date 15 sept. 2015
 * @author Serge Morvan
 */
public class GpsBuoyageGpsEventController
        extends GpsEventsController {

    GpsBuoyageController controller;

    public GpsBuoyageGpsEventController(GpsBuoyageController controller) {
        this.controller = controller;
    }

    @Override
    public void notifyNmeaMessage(GGA data) {
        controller.notifyNmeaMessage(data);
    }

    @Override
    public void notifyNmeaMessage(VTG data) {
        controller.notifyNmeaMessage(data);
    }

    @Override
    public void notifyNmeaMessage(RMC data) {
        controller.notifyNmeaMessage(data);
    }
}
