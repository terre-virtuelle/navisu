/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS06Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS07Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS08Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS09Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS10Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS11Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS12Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS13Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS14Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS15Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS16Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS17Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS18Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS19Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS20Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS21Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS22Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS23Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS24Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS25Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS26Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS27Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.AAMEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.APBEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.BECEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.BODEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.BWCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.BWREvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.DBKEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.DBSEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.DBTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.DPTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GLLEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GSAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.GSVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.HDGEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.HDMevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.HDTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.MSKevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.MTAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.MTWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.MWDevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.MWVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.NMEAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMBEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RMTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RSDEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.RTEEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VBWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VHWevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VLWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VPWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VTGEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VWREvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.VWTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.XTEEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.ZDAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.n2k.PGN128267Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.n2k.PGN130306Event;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.NmeaEventProducer;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.NmeaEventProducerServices;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBS;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DPT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GLL;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MSK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS06;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS07;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS08;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS09;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS10;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS11;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS12;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS13;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS14;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS15;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS16;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS17;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS18;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS19;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS20;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS21;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS22;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS23;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS24;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS25;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS26;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS27;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128267;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RSD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VBW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VHW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VLW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VPW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.XTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.ZDA;
import java.util.HashMap;
import java.util.Map;
import org.capcaval.c3.component.annotation.ProducedEvent;

/**
 *
 * @author Serge
 */
public class NmeaEventProducerImpl
        implements NmeaEventProducer, NmeaEventProducerServices {

    @ProducedEvent
    protected AAMEvent aamEvent;
    @ProducedEvent
    protected APBEvent apbEvent;
    @ProducedEvent
    protected BECEvent becEvent;
    @ProducedEvent
    protected BODEvent bodEvent;
    @ProducedEvent
    protected BWCEvent bwcEvent;
    @ProducedEvent
    protected BWREvent bwrEvent;
    @ProducedEvent
    protected DBKEvent dbkEvent;
    @ProducedEvent
    protected DBSEvent dbsEvent;
    @ProducedEvent
    protected DBTEvent dbtEvent;
    @ProducedEvent
    protected DPTEvent dptEvent;
    @ProducedEvent
    protected GLLEvent gllEvent;
    @ProducedEvent
    protected GGAEvent ggaEvent;
    @ProducedEvent
    protected GSAEvent gsaEvent;
    @ProducedEvent
    protected GSVEvent gsvEvent;
    @ProducedEvent
    protected HDGEvent hdgEvent;
    @ProducedEvent
    protected HDMevent hdmEvent;
    @ProducedEvent
    protected HDTEvent hdtEvent;
    @ProducedEvent
    protected MSKevent mskEvent;
    @ProducedEvent
    protected MTAEvent mtaEvent;
    @ProducedEvent
    protected MTWEvent mtwEvent;
    @ProducedEvent
    protected MWDevent mwdEvent;
    @ProducedEvent
    protected MWVEvent mwvEvent;
    @ProducedEvent
    protected NMEAEvent nmeaEvent;
    @ProducedEvent
    protected RMBEvent rmbEvent;
    @ProducedEvent
    protected RMCEvent rmcEvent;
    @ProducedEvent
    protected RMTEvent rmtEvent;
    @ProducedEvent
    protected RSDEvent rsdEvent;
    @ProducedEvent
    protected RTEEvent rteEvent;
    @ProducedEvent
    protected VBWEvent vbwEvent;
    @ProducedEvent
    protected VHWevent vhwEvent;
    @ProducedEvent
    protected VLWEvent vlwEvent;
    @ProducedEvent
    protected VPWEvent vpwEvent;
    @ProducedEvent
    protected VTGEvent vtgEvent;
    @ProducedEvent
    protected VWREvent vwrEvent;
    @ProducedEvent
    protected VWTEvent vwtEvent;
    @ProducedEvent
    protected XTEEvent xteEvent;
    @ProducedEvent
    protected ZDAEvent zdaEvent;

    @ProducedEvent
    protected AIS01Event ais01Event;
    @ProducedEvent
    protected AIS02Event ais02Event;
    @ProducedEvent
    protected AIS03Event ais03Event;
    @ProducedEvent
    protected AIS04Event ais04Event;
    @ProducedEvent
    protected AIS05Event ais05Event;
    @ProducedEvent
    protected AIS06Event ais06Event;
    @ProducedEvent
    protected AIS07Event ais07Event;
    @ProducedEvent
    protected AIS08Event ais08Event;
    @ProducedEvent
    protected AIS09Event ais09Event;
    @ProducedEvent
    protected AIS10Event ais10Event;
    @ProducedEvent
    protected AIS11Event ais11Event;
    @ProducedEvent
    protected AIS12Event ais12Event;
    @ProducedEvent
    protected AIS13Event ais13Event;
    @ProducedEvent
    protected AIS14Event ais14Event;
    @ProducedEvent
    protected AIS15Event ais15Event;
    @ProducedEvent
    protected AIS16Event ais16Event;
    @ProducedEvent
    protected AIS17Event ais17Event;
    @ProducedEvent
    protected AIS18Event ais18Event;
    @ProducedEvent
    protected AIS19Event ais19Event;
    @ProducedEvent
    protected AIS20Event ais20Event;
    @ProducedEvent
    protected AIS21Event ais21Event;
    @ProducedEvent
    protected AIS22Event ais22Event;
    @ProducedEvent
    protected AIS23Event ais23Event;
    @ProducedEvent
    protected AIS24Event ais24Event;
    @ProducedEvent
    protected AIS25Event ais25Event;
    @ProducedEvent
    protected AIS26Event ais26Event;
    @ProducedEvent
    protected AIS27Event ais27Event;

    @ProducedEvent
    protected PGN130306Event pgn130306Event;
    @ProducedEvent
    protected PGN128267Event pgn128267Event;

    private Map<String, NMEAEvent> nmeaEvents = null;

    public NmeaEventProducerImpl() {
    }

    public void notifyNMEAEvent(NMEA nmea) {
        try {
            if (nmea != null) {
                nmeaEvents.get(nmea.getClass().getSimpleName()).notifyNmeaMessageChanged(nmea);
            }
        } catch (Exception e) {
            //Pb sur emission des Ais05, quelques fois Ais03
            //Le premier souscripteur reçoit l'evt
            //puis tir d'une exception java.lang.reflect.UndeclaredThrowableException
            //System.out.println("e " + e + "  " + nmea);
        }
    }

    // Appelé par le composant principal, lors de l'initialisation
    public void init() {
        nmeaEvents = new HashMap<>();
        nmeaEvents.put("AAM", aamEvent);
        nmeaEvents.put("APB", apbEvent);
        nmeaEvents.put("BEC", becEvent);
        nmeaEvents.put("BOD", bodEvent);
        nmeaEvents.put("BWC", bwcEvent);
        nmeaEvents.put("BWR", bwrEvent);
        nmeaEvents.put("DBK", dbkEvent);
        nmeaEvents.put("DBS", dbsEvent);
        nmeaEvents.put("DBT", dbtEvent);
        nmeaEvents.put("DPT", dptEvent);
        nmeaEvents.put("GLL", gllEvent);
        nmeaEvents.put("GGA", ggaEvent);
        nmeaEvents.put("GSA", gsaEvent);
        nmeaEvents.put("GSV", gsvEvent);
        nmeaEvents.put("HDG", hdgEvent);
        nmeaEvents.put("HDM", hdmEvent);
        nmeaEvents.put("HDT", hdtEvent);
        nmeaEvents.put("MSK", mskEvent);
        nmeaEvents.put("MTA", mtaEvent);
        nmeaEvents.put("MTW", mtwEvent);
        nmeaEvents.put("MWD", mwdEvent);
        nmeaEvents.put("MWV", mwvEvent);
        nmeaEvents.put("NMEA", nmeaEvent);
        nmeaEvents.put("RMB", rmbEvent);
        nmeaEvents.put("RMC", rmcEvent);
        nmeaEvents.put("RMT", rmtEvent);
        nmeaEvents.put("RSD", rsdEvent);
        nmeaEvents.put("RTE", rteEvent);
        nmeaEvents.put("VBW", vbwEvent);
        nmeaEvents.put("VHW", vhwEvent);
        nmeaEvents.put("VLW", vlwEvent);
        nmeaEvents.put("VPW", vpwEvent);
        nmeaEvents.put("VTG", vtgEvent);
        nmeaEvents.put("VWR", vwrEvent);
        nmeaEvents.put("VWT", vwtEvent);
        nmeaEvents.put("XTE", xteEvent);
        nmeaEvents.put("ZDA", zdaEvent);

        nmeaEvents.put("AIS01", ais01Event);
        nmeaEvents.put("AIS02", ais02Event);
        nmeaEvents.put("AIS03", ais03Event);
        nmeaEvents.put("AIS04", ais04Event);
        nmeaEvents.put("AIS05", ais05Event);
        nmeaEvents.put("AIS06", ais06Event);
        nmeaEvents.put("AIS07", ais07Event);
        nmeaEvents.put("AIS08", ais08Event);
        nmeaEvents.put("AIS09", ais09Event);
        nmeaEvents.put("AIS10", ais10Event);
        nmeaEvents.put("AIS11", ais11Event);
        nmeaEvents.put("AIS12", ais12Event);
        nmeaEvents.put("AIS13", ais13Event);
        nmeaEvents.put("AIS14", ais14Event);
        nmeaEvents.put("AIS15", ais15Event);
        nmeaEvents.put("AIS16", ais16Event);
        nmeaEvents.put("AIS17", ais17Event);
        nmeaEvents.put("AIS18", ais18Event);
        nmeaEvents.put("AIS19", ais19Event);
        nmeaEvents.put("AIS20", ais20Event);
        nmeaEvents.put("AIS21", ais21Event);
        nmeaEvents.put("AIS22", ais22Event);
        nmeaEvents.put("AIS23", ais23Event);
        nmeaEvents.put("AIS24", ais24Event);
        nmeaEvents.put("AIS25", ais25Event);
        nmeaEvents.put("AIS26", ais26Event);
        nmeaEvents.put("AIS27", ais27Event);

        nmeaEvents.put("PGN130306", pgn130306Event);
        nmeaEvents.put("PGN128267", pgn128267Event);
        System.out.println(nmeaEvents.entrySet());
    }
}
