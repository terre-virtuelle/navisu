/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.transponder.impl.events;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import gov.nasa.worldwind.layers.RenderableLayer;
import java.util.List;
import org.capcaval.c3.component.ComponentEvent;

/**
 * @date 2 mars 2015
 * @author Serge Morvan
 */
public interface TransponderActivateEvent
        extends ComponentEvent {

    public void notifyActivateMessageChanged(RenderableLayer layer, List<NavigationData> target);

}
