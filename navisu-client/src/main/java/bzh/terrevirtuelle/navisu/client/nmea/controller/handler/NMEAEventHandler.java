/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.controller.handler;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author Serge Morvan
 */
public class NMEAEventHandler
        extends Handler {

    private Map<Class, Class> nmeaEvents = null;
    private Map<Class, Collection< ? extends NMEAEvent>> nmeaServiceProviders = null;

    public NMEAEventHandler() {
        nmeaEvents = new HashMap<>();
        nmeaEvents.put(AAM.class, AAMEvent.class);
        nmeaEvents.put(APB.class, APBEvent.class);
        nmeaEvents.put(BEC.class, BECEvent.class);
        nmeaEvents.put(BOD.class, BODEvent.class);
        nmeaEvents.put(BWC.class, BWCEvent.class);
        nmeaEvents.put(BWR.class, BWREvent.class);
        nmeaEvents.put(DBK.class, DBKEvent.class);
        nmeaEvents.put(DBS.class, DBSEvent.class);
        nmeaEvents.put(DBT.class, DBTEvent.class);
        nmeaEvents.put(DPT.class, DPTEvent.class);
        nmeaEvents.put(GGA.class, GGAEvent.class);
        nmeaEvents.put(GLL.class, GLLEvent.class);
        nmeaEvents.put(GSA.class, GSAEvent.class);
        nmeaEvents.put(GSV.class, GSVEvent.class);
        nmeaEvents.put(HDG.class, HDGEvent.class);
        nmeaEvents.put(HDM.class, HDMevent.class);
        nmeaEvents.put(HDT.class, HDTEvent.class);
        nmeaEvents.put(MSK.class, MSKevent.class);
        nmeaEvents.put(MTA.class, MTAEvent.class);
        nmeaEvents.put(MTW.class, MTWEvent.class);
        nmeaEvents.put(MWD.class, MWDevent.class);
        nmeaEvents.put(MWV.class, MWVEvent.class);
        nmeaEvents.put(NMEA.class, NMEAEvent.class);
        nmeaEvents.put(RMB.class, RMBEvent.class);
        nmeaEvents.put(RMC.class, RMCEvent.class);
        nmeaEvents.put(RMT.class, RMTEvent.class);
        nmeaEvents.put(RSD.class, RSDEvent.class);
        nmeaEvents.put(RTE.class, RTEEvent.class);
        nmeaEvents.put(VBW.class, VBWEvent.class);
        nmeaEvents.put(VHW.class, VHWevent.class);
        nmeaEvents.put(VLW.class, VLWEvent.class);
        nmeaEvents.put(VPW.class, VPWEvent.class);
        nmeaEvents.put(VTG.class, VTGEvent.class);
        nmeaEvents.put(VWR.class, VWREvent.class);
        nmeaEvents.put(VWT.class, VWTEvent.class);
        nmeaEvents.put(XTE.class, XTEEvent.class);
        nmeaEvents.put(ZDA.class, ZDAEvent.class);

        nmeaServiceProviders = new HashMap<>();
        Set<Class> keySet = nmeaEvents.keySet();
        for (Class claze : keySet) {
          //  nmeaServiceProviders.put(claze, Lookup.getDefault().lookupAll(nmeaEvents.get(claze)));
        }
    }

    @Override
    public <T extends NMEA> void doIt(T nmea) {
        Collection<? extends NMEAEvent> providers;
        providers = nmeaServiceProviders.get(nmea.getClass());
        for (NMEAEvent c : providers) {
            //c.update(nmea); 
        }
    }
}
