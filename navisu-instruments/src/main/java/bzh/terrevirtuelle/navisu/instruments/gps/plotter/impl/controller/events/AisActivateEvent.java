/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.gps.plotter.impl.controller.events;

import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import org.capcaval.c3.component.ComponentEvent;

/**
 * @date 2 mars 2015
 * @author Serge Morvan
 */
public interface AisActivateEvent
        extends ComponentEvent {

    public void notifyAisActivateMessageChanged(RenderableLayer layer, List<String> target);

}
