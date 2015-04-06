/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.controller.eventsProducer;

import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Bathymetry;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public interface BathymetryEventProducerServices
        extends ComponentService {

    public void setBathymetry(Bathymetry data);

}
