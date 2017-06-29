/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.Option;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.ConfigurationEvent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.OwnerShipConfEvent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.UserConfEvent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.eventsProducer.ConfEventProducer;
import bzh.terrevirtuelle.navisu.app.guiagent.options.eventsProducer.ConfEventProducerServices;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.component.annotation.ProducedEvent;
import bzh.terrevirtuelle.navisu.app.guiagent.options.events.SerialDeviceConfEvent;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import org.capcaval.c3.component.annotation.UsedService;

/**
 *
 * @author Serge
 */
public class ConfEventProducerImpl
        implements ConfEventProducer, ConfEventProducerServices {

    @ProducedEvent
    protected OwnerShipConfEvent ownerShipConfEvent;
    @ProducedEvent
    protected UserConfEvent userConfEvent;
    @ProducedEvent
    protected SerialDeviceConfEvent devicesConfEvent;

    
    private Map<String, ConfigurationEvent> configurationEvents = null;

    public ConfEventProducerImpl() {
    }

    public void notifyConfEvent(Option option) {
        try {
            if (option != null) {
                configurationEvents.get(option.getClass().getSimpleName()).notifyConfMessageChanged(option);
            }
        } catch (Exception e) {
            Logger.getLogger(ConfEventProducerImpl.class.getName()).log(Level.SEVERE, e.toString(), e);
        }
    }

    // Appelé par le composant principal, lors de l'initialisation
    public void init() {
        configurationEvents = new HashMap<>();

        configurationEvents.put("OwnerShipOption", ownerShipConfEvent);
        configurationEvents.put("UserOption", userConfEvent);
        configurationEvents.put("DevicesOption", devicesConfEvent);
    }
}
