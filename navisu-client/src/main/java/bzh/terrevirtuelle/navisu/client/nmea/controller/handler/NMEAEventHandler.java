/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.controller.handler;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.AAMService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.APBService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.BECService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.BODService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.BWCService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.BWRService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.DBKService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.DBSService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.DBTService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.DPTService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.GGAService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.GLLService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.GSAService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.GSVService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.HDGService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.HDMService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.HDTService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.MSKService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.MTAService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.MTWService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.MWDService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.MWVService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.NMEAService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.RMBService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.RMCService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.RMTService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.RSDService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.RTEService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VBWService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VHWService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VLWService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VPWService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VTGService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VWRService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.VWTService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.XTEService;
import bzh.terrevirtuelle.navisu.client.nmea.controller.services.ZDAService;
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
    private Map<Class, Collection< ? extends NMEAService>> nmeaServiceProviders = null;

    public NMEAEventHandler() {
        nmeaServices = new HashMap<>();
        nmeaServices.put(AAM.class, AAMService.class);
        nmeaServices.put(APB.class, APBService.class);
        nmeaServices.put(BEC.class, BECService.class);
        nmeaServices.put(BOD.class, BODService.class);
        nmeaServices.put(BWC.class, BWCService.class);
        nmeaServices.put(BWR.class, BWRService.class);
        nmeaServices.put(DBK.class, DBKService.class);
        nmeaServices.put(DBS.class, DBSService.class);
        nmeaServices.put(DBT.class, DBTService.class);
        nmeaServices.put(DPT.class, DPTService.class);
        nmeaServices.put(GGA.class, GGAService.class);
        nmeaServices.put(GLL.class, GLLService.class);
        nmeaServices.put(GSA.class, GSAService.class);
        nmeaServices.put(GSV.class, GSVService.class);
        nmeaServices.put(HDG.class, HDGService.class);
        nmeaServices.put(HDM.class, HDMService.class);
        nmeaServices.put(HDT.class, HDTService.class);
        nmeaServices.put(MSK.class, MSKService.class);
        nmeaServices.put(MTA.class, MTAService.class);
        nmeaServices.put(MTW.class, MTWService.class);
        nmeaServices.put(MWD.class, MWDService.class);
        nmeaServices.put(MWV.class, MWVService.class);
        nmeaServices.put(NMEA.class, NMEAService.class);
        nmeaServices.put(RMB.class, RMBService.class);
        nmeaServices.put(RMC.class, RMCService.class);
        nmeaServices.put(RMT.class, RMTService.class);
        nmeaServices.put(RSD.class, RSDService.class);
        nmeaServices.put(RTE.class, RTEService.class);
        nmeaServices.put(VBW.class, VBWService.class);
        nmeaServices.put(VHW.class, VHWService.class);
        nmeaServices.put(VLW.class, VLWService.class);
        nmeaServices.put(VPW.class, VPWService.class);
        nmeaServices.put(VTG.class, VTGService.class);
        nmeaServices.put(VWR.class, VWRService.class);
        nmeaServices.put(VWT.class, VWTService.class);
        nmeaServices.put(XTE.class, XTEService.class);
        nmeaServices.put(ZDA.class, ZDAService.class);

        nmeaServiceProviders = new HashMap<>();
        Set<Class> keySet = nmeaServices.keySet();
        for (Class claze : keySet) {
          //  nmeaServiceProviders.put(claze, Lookup.getDefault().lookupAll(nmeaServices.get(claze)));
        }
    }

    @Override
    public <T extends NMEA> void doIt(T nmea) {
        Collection<? extends NMEAService> providers;
        providers = nmeaServiceProviders.get(nmea.getClass());
        for (NMEAService c : providers) {
            c.update(nmea); 
        }
    }
}
