/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

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
        @XmlElement(name = "nmea", type = NMEA.class),
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
        @XmlElement(name = "ais1", type = AIS1.class),
        @XmlElement(name = "ais11", type = AIS11.class),
        @XmlElement(name = "ais14", type = AIS14.class),
        @XmlElement(name = "ais18", type = AIS18.class),
        @XmlElement(name = "ais19", type = AIS19.class),
        @XmlElement(name = "ais3", type = AIS3.class),
        @XmlElement(name = "ais4", type = AIS4.class),
        @XmlElement(name = "ais5", type = AIS5.class),
        @XmlElement(name = "ais135", type = AIS135.class),
        @XmlElement(name = "ais", type = AISMessage.class),
        @XmlElement(name = "ais9", type = AIS9.class),
        @XmlElement(name = "n2k", type = N2K.class),
        @XmlElement(name = "pgn130306", type = PGN130306.class),
        @XmlElement(name = "pgn128267", type = PGN128267.class)
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
