/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.controller.events;

import bzh.terrevirtuelle.navisu.domain.bathymetry.model.DEM;
import org.capcaval.c3.component.ComponentEvent;

/**
 * NaVisu
 *
 * @date 4 avr. 2015
 * @author Serge Morvan
 */
public interface BathymetryEvent
        extends ComponentEvent {

    /**
     *
     * @param data
     */
    public void notifyBathymetryMessageChanged(DEM data);
}
