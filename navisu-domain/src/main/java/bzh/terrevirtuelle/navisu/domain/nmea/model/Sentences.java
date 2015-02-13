/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128267;
import bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.N2K;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VLW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RSD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VPW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VHW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.XTE;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VBW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GLL;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MSK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWV;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBK;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTW;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DPT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBT;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC;
import bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBS;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS135;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS14;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS11;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS09;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS19;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS18;
import java.util.ArrayList;
import java.util.List;
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
        @XmlElement(name = "aam", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.AAM.class),
        @XmlElement(name = "apa", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APA.class),
        @XmlElement(name = "apb", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.APB.class),
        @XmlElement(name = "bec", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BEC.class),
        @XmlElement(name = "bod", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BOD.class),
        @XmlElement(name = "bwc", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWC.class),
        @XmlElement(name = "bwr", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWR.class),
        @XmlElement(name = "bww", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.BWW.class),
        @XmlElement(name = "dbk", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBK.class),
        @XmlElement(name = "dbs", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBS.class),
        @XmlElement(name = "dbt", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DBT.class),
        @XmlElement(name = "dpt", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.DPT.class),
        @XmlElement(name = "gga", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GGA.class),
        @XmlElement(name = "gll", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GLL.class),
        @XmlElement(name = "gsa", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSA.class),
        @XmlElement(name = "gsv", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.GSV.class),
        @XmlElement(name = "hdg", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDG.class),
        @XmlElement(name = "hdm", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDM.class),
        @XmlElement(name = "hdt", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.HDT.class),
        @XmlElement(name = "msk", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MSK.class),
        @XmlElement(name = "mta", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTA.class),
        @XmlElement(name = "mtw", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MTW.class),
        @XmlElement(name = "mwd", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWD.class),
        @XmlElement(name = "mwv", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.MWV.class),
        @XmlElement(name = "rmb", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMB.class),
        @XmlElement(name = "rmc", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMC.class),
        @XmlElement(name = "rmt", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RMT.class),
        @XmlElement(name = "rsd", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RSD.class),
        @XmlElement(name = "rte", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.RTE.class),
        @XmlElement(name = "vbw", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VBW.class),
        @XmlElement(name = "vhw", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VHW.class),
        @XmlElement(name = "vlw", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VLW.class),
        @XmlElement(name = "vpw", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VPW.class),
        @XmlElement(name = "vtg", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VTG.class),
        @XmlElement(name = "vwr", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWR.class),
        @XmlElement(name = "vwt", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.VWT.class),
        @XmlElement(name = "xte", type = bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183.XTE.class),
        @XmlElement(name = "ais1", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS01.class),
        @XmlElement(name = "ais11", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS11.class),
        @XmlElement(name = "ais14", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS14.class),
        @XmlElement(name = "ais18", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS18.class),
        @XmlElement(name = "ais19", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS19.class),
        @XmlElement(name = "ais2", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS02.class),
        @XmlElement(name = "ais3", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS03.class),
        @XmlElement(name = "ais4", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS04.class),
        @XmlElement(name = "ais5", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS05.class),
        @XmlElement(name = "ais135", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS135.class),
        @XmlElement(name = "ais", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage.class),
        @XmlElement(name = "ais9", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS09.class),
        @XmlElement(name = "n2k", type = bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.N2K.class),
        @XmlElement(name = "pgn130306", type = bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN130306.class),
        @XmlElement(name = "pgn128267", type = bzh.terrevirtuelle.navisu.domain.nmea.model.n2k.PGN128267.class)
    })
    List<NMEA> sentences;

    public Sentences() {
        sentences = new ArrayList<>();
    }

    public void add(NMEA data) {
        sentences.add(data);
    }

    public List<NMEA> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        return "Sentences{" + sentences + '}';
    }

}
