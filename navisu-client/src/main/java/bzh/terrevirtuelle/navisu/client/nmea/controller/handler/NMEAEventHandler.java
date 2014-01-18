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

    private Map<Class, Class> nmeaServices = null;
    private Map<Class, Collection< ? extends NMEAEvent>> nmeaServiceProviders = null;

    public NMEAEventHandler() {
        nmeaServices = new HashMap<>();
        nmeaServices.put(AAM.class, AAMEvent.class);
        nmeaServices.put(APB.class, APBEvent.class);
        nmeaServices.put(BEC.class, BECEvent.class);
        nmeaServices.put(BOD.class, BODEvent.class);
        nmeaServices.put(BWC.class, BWCEvent.class);
        nmeaServices.put(BWR.class, BWREvent.class);
        nmeaServices.put(DBK.class, DBKEvent.class);
        nmeaServices.put(DBS.class, DBSEvent.class);
        nmeaServices.put(DBT.class, DBTEvent.class);
        nmeaServices.put(DPT.class, DPTEvent.class);
        nmeaServices.put(GGA.class, GGAEvent.class);
        nmeaServices.put(GLL.class, GLLEvent.class);
        nmeaServices.put(GSA.class, GSAEvent.class);
        nmeaServices.put(GSV.class, GSVEvent.class);
        nmeaServices.put(HDG.class, HDGEvent.class);
        nmeaServices.put(HDM.class, HDMevent.class);
        nmeaServices.put(HDT.class, HDTEvent.class);
        nmeaServices.put(MSK.class, MSKevent.class);
        nmeaServices.put(MTA.class, MTAEvent.class);
        nmeaServices.put(MTW.class, MTWEvent.class);
        nmeaServices.put(MWD.class, MWDevent.class);
        nmeaServices.put(MWV.class, MWVEvent.class);
        nmeaServices.put(NMEA.class, NMEAEvent.class);
        nmeaServices.put(RMB.class, RMBEvent.class);
        nmeaServices.put(RMC.class, RMCEvent.class);
        nmeaServices.put(RMT.class, RMTEvent.class);
        nmeaServices.put(RSD.class, RSDEvent.class);
        nmeaServices.put(RTE.class, RTEEvent.class);
        nmeaServices.put(VBW.class, VBWEvent.class);
        nmeaServices.put(VHW.class, VHWevent.class);
        nmeaServices.put(VLW.class, VLWEvent.class);
        nmeaServices.put(VPW.class, VPWEvent.class);
        nmeaServices.put(VTG.class, VTGEvent.class);
        nmeaServices.put(VWR.class, VWREvent.class);
        nmeaServices.put(VWT.class, VWTEvent.class);
        nmeaServices.put(XTE.class, XTEEvent.class);
        nmeaServices.put(ZDA.class, ZDAEvent.class);

        nmeaServiceProviders = new HashMap<>();
        Set<Class> keySet = nmeaServices.keySet();
        for (Class claze : keySet) {
          //  nmeaServiceProviders.put(claze, Lookup.getDefault().lookupAll(nmeaServices.get(claze)));
        }
    }

    @Override
    public <T extends NMEA> void doIt(T nmea) {
        Collection<? extends NMEAEvent> providers;
        providers = nmeaServiceProviders.get(nmea.getClass());
        for (NMEAEvent c : providers) {
            c.update(nmea); 
        }
    }
}
