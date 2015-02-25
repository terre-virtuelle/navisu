/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.nmea183.AAMEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS11Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS14Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS18Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS19Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS01Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS24Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS02Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS03Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS04Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS05Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS06Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS07Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS08Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS09Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS10Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS12Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS135Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS13Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS15Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS16Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS17Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS20Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS21Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS22Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS23Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS25Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS26Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ais.AIS27Event;
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
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.n2k.PGN128267Event;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.n2k.PGN130306Event;
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
    protected AIS135Event ais135Event;

    @ProducedEvent
    protected PGN130306Event pgn130306Event;
    @ProducedEvent
    protected PGN128267Event pgn128267Event;

    private Map<Class, NMEAEvent> nmeaEvents = null;

    public NmeaEventProducerImpl() {

    }

    public void notifyNMEAEvent(NMEA nmea) {
        // System.out.println("nmea "+nmea.getClass().getName());
        // System.out.println("nmea "+ nmea);
        try {
            if (nmea != null) {
                nmeaEvents.get(nmea.getClass()).notifyNmeaMessageChanged(nmea);
            }
        } catch (Exception e) {
              //    System.out.println("e "+ e.getClass().getName()+"  "+nmea);
            System.out.print("");
        }
    }

    // Appelé par le composant principal, lors de l'initialisation
    public void init() {
        nmeaEvents = new HashMap<>();
        nmeaEvents.put(AAM.class, aamEvent);
        nmeaEvents.put(APB.class, apbEvent);
        nmeaEvents.put(BEC.class, becEvent);
        nmeaEvents.put(BOD.class, bodEvent);
        nmeaEvents.put(BWC.class, bwcEvent);
        nmeaEvents.put(BWR.class, bwrEvent);
        nmeaEvents.put(DBK.class, dbkEvent);
        nmeaEvents.put(DBS.class, dbsEvent);
        nmeaEvents.put(DBT.class, dbtEvent);
        nmeaEvents.put(DPT.class, dptEvent);
        nmeaEvents.put(GLL.class, gllEvent);
        nmeaEvents.put(GGA.class, ggaEvent);
        nmeaEvents.put(GSA.class, gsaEvent);
        nmeaEvents.put(GSV.class, gsvEvent);
        nmeaEvents.put(HDG.class, hdgEvent);
        nmeaEvents.put(HDM.class, hdmEvent);
        nmeaEvents.put(HDT.class, hdtEvent);
        nmeaEvents.put(MSK.class, mskEvent);
        nmeaEvents.put(MTA.class, mtaEvent);
        nmeaEvents.put(MTW.class, mtwEvent);
        nmeaEvents.put(MWD.class, mwdEvent);
        nmeaEvents.put(MWV.class, mwvEvent);
        nmeaEvents.put(NMEA.class, nmeaEvent);
        nmeaEvents.put(RMB.class, rmbEvent);
        nmeaEvents.put(RMC.class, rmcEvent);
        nmeaEvents.put(RMT.class, rmtEvent);
        nmeaEvents.put(RSD.class, rsdEvent);
        nmeaEvents.put(RTE.class, rteEvent);
        nmeaEvents.put(VBW.class, vbwEvent);
        nmeaEvents.put(VHW.class, vhwEvent);
        nmeaEvents.put(VLW.class, vlwEvent);
        nmeaEvents.put(VPW.class, vpwEvent);
        nmeaEvents.put(VTG.class, vtgEvent);
        nmeaEvents.put(VWR.class, vwrEvent);
        nmeaEvents.put(VWT.class, vwtEvent);
        nmeaEvents.put(XTE.class, xteEvent);
        nmeaEvents.put(ZDA.class, zdaEvent);

        nmeaEvents.put(AIS01.class, ais01Event);
        nmeaEvents.put(AIS02.class, ais24Event);
        nmeaEvents.put(AIS03.class, ais03Event);
        nmeaEvents.put(AIS04.class, ais04Event);
        nmeaEvents.put(AIS05.class, ais05Event);
        nmeaEvents.put(AIS06.class, ais06Event);
        nmeaEvents.put(AIS07.class, ais06Event);
        nmeaEvents.put(AIS08.class, ais08Event);
        nmeaEvents.put(AIS09.class, ais08Event);
        nmeaEvents.put(AIS10.class, ais10Event);
        nmeaEvents.put(AIS11.class, ais11Event);
        nmeaEvents.put(AIS12.class, ais12Event);
        nmeaEvents.put(AIS13.class, ais13Event);
        nmeaEvents.put(AIS14.class, ais14Event);
        nmeaEvents.put(AIS15.class, ais15Event);
        nmeaEvents.put(AIS16.class, ais16Event);
        nmeaEvents.put(AIS17.class, ais17Event);
        nmeaEvents.put(AIS18.class, ais18Event);
        nmeaEvents.put(AIS19.class, ais19Event);
        nmeaEvents.put(AIS20.class, ais20Event);
        nmeaEvents.put(AIS21.class, ais21Event);
        nmeaEvents.put(AIS22.class, ais22Event);
        nmeaEvents.put(AIS23.class, ais23Event);
        nmeaEvents.put(AIS24.class, ais24Event);
        nmeaEvents.put(AIS25.class, ais25Event);
        nmeaEvents.put(AIS26.class, ais26Event);
        nmeaEvents.put(AIS27.class, ais27Event);

        nmeaEvents.put(PGN130306.class, pgn130306Event);
        nmeaEvents.put(PGN128267.class, pgn128267Event);
    }
}
