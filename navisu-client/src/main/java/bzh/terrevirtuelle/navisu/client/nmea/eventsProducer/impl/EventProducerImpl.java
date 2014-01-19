/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.NMEAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.EventProducer;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.EventProducerServices;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author Serge
 */
public class EventProducerImpl
        implements EventProducer, EventProducerServices {

    private final Map<Class, Class> nmeaEvents = null;
    private final Map<Class, Collection< ? extends NMEAEvent>> nmeaServiceProviders = null;
}
