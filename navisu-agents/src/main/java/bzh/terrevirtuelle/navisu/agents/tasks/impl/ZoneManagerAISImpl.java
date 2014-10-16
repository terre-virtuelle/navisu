/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Temp lates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.agents.tasks.impl;

import bzh.terrevirtuelle.navisu.agents.tasks.ZoneManager;
import bzh.terrevirtuelle.navisu.agents.tasks.ZoneManagerServices;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS1Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS2Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS3Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS4Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AIS5Event;
import java.util.logging.Logger;
import org.capcaval.c3.component.ComponentEventSubscribe;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge Morvan
 * @date 15 oct. 2014 NaVisu project
 */ 
public class ZoneManagerAISImpl
        implements ZoneManager, ZoneManagerServices, ComponentState {

    protected static final Logger LOGGER = Logger.getLogger(ZoneManagerAISImpl.class.getName());

    ComponentManager cm = ComponentManager.componentManager;
    ComponentEventSubscribe<AIS1Event> ais1ES = cm.getComponentEventSubscribe(AIS1Event.class);
    ComponentEventSubscribe<AIS2Event> ais2ES = cm.getComponentEventSubscribe(AIS2Event.class);
    ComponentEventSubscribe<AIS3Event> ais3ES = cm.getComponentEventSubscribe(AIS3Event.class);
    ComponentEventSubscribe<AIS4Event> ais4ES = cm.getComponentEventSubscribe(AIS4Event.class);
    ComponentEventSubscribe<AIS5Event> ais5ES = cm.getComponentEventSubscribe(AIS5Event.class);

    @Override
    public void componentInitiated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentStarted() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentStopped() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
