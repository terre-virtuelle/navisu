/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.charts.vector.s57.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.instruments.common.controller.GpsEventsController;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * NaVisu
 *
 * @date 12 sept. 2015
 * @author Serge Morvan
 */
@Entity
public class ExtendedBuoyage
        extends GpsEventsController {

    @Id
    private long id;
    private Buoyage buoyage;

    public ExtendedBuoyage() {
    }

    public ExtendedBuoyage(Buoyage buoyage) {
        this.buoyage = buoyage;
        id = buoyage.getId();
        //subscribe();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        buoyage.setId(id);
    }

    public Buoyage getBuoyage() {
        return buoyage;
    }

    public void setBuoyage(Buoyage buoyage) {
        this.buoyage = buoyage;
    }

    @Override
    public void notifyNmeaMessage(RMC data) {
        System.out.println("data " + data);
    }
}
