/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.bathymetry.controller.events.BathymetryEvent;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducer;
import bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer.BathymetryEventProducerServices;
import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import org.capcaval.c3.component.annotation.ProducedEvent;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public class BathymetryEventProducerImpl
        implements BathymetryEventProducer, BathymetryEventProducerServices {

    @ProducedEvent
    protected BathymetryEvent bathymetryEvent;

    /**
     *
     * @param evt
     */
    @Override
    public void setBathymetry(DEM evt) {
        bathymetryEvent.notifyBathymetryMessageChanged(evt);
    }

}
