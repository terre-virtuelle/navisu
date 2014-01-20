/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.impl;

import bzh.terrevirtuelle.navisu.client.nmea.controller.events.AAMEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.APBEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.BECEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.BODEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.BWCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.BWREvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.DBKEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.DBSEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.DBTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.DPTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GGAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GLLEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GSAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.GSVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.HDGEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.HDMevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.HDTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.MSKevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.MTAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.MTWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.MWDevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.MWVEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.NMEAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMBEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMCEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RMTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RSDEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.RTEEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VBWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VHWevent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VLWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VPWEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VTGEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VWREvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.VWTEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.XTEEvent;
import bzh.terrevirtuelle.navisu.client.nmea.controller.events.ZDAEvent;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.NmeaEventProducer;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.NmeaEventProducerServices;
import bzh.terrevirtuelle.navisu.nmea.model.AAM;
import bzh.terrevirtuelle.navisu.nmea.model.APB;
import bzh.terrevirtuelle.navisu.nmea.model.BEC;
import bzh.terrevirtuelle.navisu.nmea.model.BOD;
import bzh.terrevirtuelle.navisu.nmea.model.BWC;
import bzh.terrevirtuelle.navisu.nmea.model.BWR;
import bzh.terrevirtuelle.navisu.nmea.model.DBK;
import bzh.terrevirtuelle.navisu.nmea.model.DBS;
import bzh.terrevirtuelle.navisu.nmea.model.DBT;
import bzh.terrevirtuelle.navisu.nmea.model.DPT;
import bzh.terrevirtuelle.navisu.nmea.model.GGA;
import bzh.terrevirtuelle.navisu.nmea.model.GLL;
import bzh.terrevirtuelle.navisu.nmea.model.GSA;
import bzh.terrevirtuelle.navisu.nmea.model.GSV;
import bzh.terrevirtuelle.navisu.nmea.model.HDG;
import bzh.terrevirtuelle.navisu.nmea.model.HDM;
import bzh.terrevirtuelle.navisu.nmea.model.HDT;
import bzh.terrevirtuelle.navisu.nmea.model.MSK;
import bzh.terrevirtuelle.navisu.nmea.model.MTA;
import bzh.terrevirtuelle.navisu.nmea.model.MTW;
import bzh.terrevirtuelle.navisu.nmea.model.MWD;
import bzh.terrevirtuelle.navisu.nmea.model.MWV;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.nmea.model.RMB;
import bzh.terrevirtuelle.navisu.nmea.model.RMC;
import bzh.terrevirtuelle.navisu.nmea.model.RMT;
import bzh.terrevirtuelle.navisu.nmea.model.RSD;
import bzh.terrevirtuelle.navisu.nmea.model.RTE;
import bzh.terrevirtuelle.navisu.nmea.model.VBW;
import bzh.terrevirtuelle.navisu.nmea.model.VHW;
import bzh.terrevirtuelle.navisu.nmea.model.VLW;
import bzh.terrevirtuelle.navisu.nmea.model.VPW;
import bzh.terrevirtuelle.navisu.nmea.model.VTG;
import bzh.terrevirtuelle.navisu.nmea.model.VWR;
import bzh.terrevirtuelle.navisu.nmea.model.VWT;
import bzh.terrevirtuelle.navisu.nmea.model.XTE;
import bzh.terrevirtuelle.navisu.nmea.model.ZDA;
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

    private Map<Class, NMEAEvent> nmeaEvents = null;

    public NmeaEventProducerImpl() {

    }

    public void notifyNMEAEvent(NMEA nmea) {
        nmeaEvents.get(nmea.getClass()).notifyNmeaMessageChanged(nmea);
    }
    // Appelé par le composant principal, lors de l'initialisation
    public void init() {
        nmeaEvents = new HashMap<>();
        nmeaEvents.put(AAM.class, aamEvent);
        nmeaEvents.put(APB.class, apbEvent);
        nmeaEvents.put(BEC.class, becEvent);
        nmeaEvents.put(BOD.class, bodEvent);
        nmeaEvents.put(BWC.class, bwcEvent);
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
    }
}
