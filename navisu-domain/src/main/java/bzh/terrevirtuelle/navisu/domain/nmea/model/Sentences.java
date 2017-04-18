/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AISMessageImpl;
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
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.N2K;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN126992;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN127245;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN127250;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN127258;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128259;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128267;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN129025;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWW;
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
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sentences {

    @XmlElements({
        @XmlElement(name = "nmea", type = NMEA.class),
        @XmlElement(name = "aam", type = AAM.class),
        @XmlElement(name = "apa", type = APA.class),
        @XmlElement(name = "apb", type = APB.class),
        @XmlElement(name = "bec", type = BEC.class),
        @XmlElement(name = "bod", type = BOD.class),
        @XmlElement(name = "bwc", type = BWC.class),
        @XmlElement(name = "bwr", type = BWR.class),
        @XmlElement(name = "bww", type = BWW.class),
        @XmlElement(name = "dbk", type = DBK.class),
        @XmlElement(name = "dbs", type = DBS.class),
        @XmlElement(name = "dbt", type = DBT.class),
        @XmlElement(name = "dpt", type = DPT.class),
        @XmlElement(name = "gga", type = GGA.class),
        @XmlElement(name = "gll", type = GLL.class),
        @XmlElement(name = "gsa", type = GSA.class),
        @XmlElement(name = "gsv", type = GSV.class),
        @XmlElement(name = "hdg", type = HDG.class),
        @XmlElement(name = "hdm", type = HDM.class),
        @XmlElement(name = "hdt", type = HDT.class),
        @XmlElement(name = "msk", type = MSK.class),
        @XmlElement(name = "mta", type = MTA.class),
        @XmlElement(name = "mtw", type = MTW.class),
        @XmlElement(name = "mwd", type = MWD.class),
        @XmlElement(name = "mwv", type = MWV.class),
        @XmlElement(name = "rmb", type = RMB.class),
        @XmlElement(name = "rmc", type = RMC.class),
        @XmlElement(name = "rmt", type = RMT.class),
        @XmlElement(name = "rsd", type = RSD.class),
        @XmlElement(name = "rte", type = RTE.class),
        @XmlElement(name = "vbw", type = VBW.class),
        @XmlElement(name = "vhw", type = VHW.class),
        @XmlElement(name = "vlw", type = VLW.class),
        @XmlElement(name = "vpw", type = VPW.class),
        @XmlElement(name = "vtg", type = VTG.class),
        @XmlElement(name = "vwr", type = VWR.class),
        @XmlElement(name = "vwt", type = VWT.class),
        @XmlElement(name = "xte", type = XTE.class),

        @XmlElement(name = "ais", type = AISMessageImpl.class),
        @XmlElement(name = "ais01", type = AIS01.class),
        @XmlElement(name = "ais02", type = AIS02.class),
        @XmlElement(name = "ais03", type = AIS03.class),
        @XmlElement(name = "ais04", type = AIS04.class),
        @XmlElement(name = "ais05", type = AIS05.class),
        @XmlElement(name = "ais06", type = AIS06.class),
        @XmlElement(name = "ais07", type = AIS07.class),
        @XmlElement(name = "ais08", type = AIS08.class),
        @XmlElement(name = "ais09", type = AIS09.class),
        @XmlElement(name = "ais10", type = AIS10.class),
        @XmlElement(name = "ais11", type = AIS11.class),
        @XmlElement(name = "ais12", type = AIS12.class),
        @XmlElement(name = "ais13", type = AIS13.class),
        @XmlElement(name = "ais14", type = AIS14.class),
        @XmlElement(name = "ais15", type = AIS15.class),
        @XmlElement(name = "ais16", type = AIS16.class),
        @XmlElement(name = "ais17", type = AIS17.class),
        @XmlElement(name = "ais18", type = AIS18.class),
        @XmlElement(name = "ais19", type = AIS19.class),
        @XmlElement(name = "ais20", type = AIS20.class),
        @XmlElement(name = "ais21", type = AIS21.class),
        @XmlElement(name = "ais22", type = AIS22.class),
        @XmlElement(name = "ais23", type = AIS23.class),
        @XmlElement(name = "ais24", type = AIS24.class),
        @XmlElement(name = "ais25", type = AIS25.class),
        @XmlElement(name = "ais26", type = AIS26.class),
        @XmlElement(name = "ais27", type = AIS27.class),

        @XmlElement(name = "n2k", type = N2K.class),
        @XmlElement(name = "pgn126992", type = PGN126992.class),
        @XmlElement(name = "pgn127245", type = PGN127245.class),
        @XmlElement(name = "pgn127250", type = PGN127250.class),
        @XmlElement(name = "pgn127258", type = PGN127258.class),
        @XmlElement(name = "pgn128259", type = PGN128259.class),
        @XmlElement(name = "pgn128267", type = PGN128267.class),
        @XmlElement(name = "pgn129025", type = PGN129025.class),
        @XmlElement(name = "pgn130306", type = PGN130306.class)
        
    })
    private final ConcurrentLinkedQueue<NMEA> nmeaQueue;

    public Sentences() {
        nmeaQueue = new ConcurrentLinkedQueue<>();
    }

    public void add(NMEA data) {
        nmeaQueue.add(data);
    }

    public void addAll(Collection<? extends NMEA> data) {
        nmeaQueue.addAll(data);
    }

    public NMEA poll() {
        return nmeaQueue.poll();
    }

    public boolean isEmpty() {
        return nmeaQueue.isEmpty();
    }

    public ConcurrentLinkedQueue<NMEA> getNmeaQueue() {
        return nmeaQueue;
    }

    public int size() {
        return nmeaQueue.size();
    }

    public void clear() {
        nmeaQueue.clear();
    }

    @Override
    public String toString() {
        return "Sentences{" + nmeaQueue + '}';
    }

    public void display() {

        nmeaQueue.stream().forEach((nmea) -> {
            if (nmea != null) {
                System.out.println(nmea);
            }
        });
    }

}
