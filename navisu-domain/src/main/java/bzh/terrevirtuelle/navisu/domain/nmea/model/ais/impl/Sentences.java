/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
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
        @XmlElement(name = "ais01", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS01.class),
        @XmlElement(name = "ais02", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS02.class),
        @XmlElement(name = "ais30", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS03.class),
        @XmlElement(name = "ais04", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS04.class),
        @XmlElement(name = "ais05", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS05.class),
        @XmlElement(name = "ais09", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS09.class),
        @XmlElement(name = "ais10", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS10.class),
        @XmlElement(name = "ais11", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS11.class),
        @XmlElement(name = "ais12", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS12.class),
        @XmlElement(name = "ais13", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS13.class),
        @XmlElement(name = "ais14", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS14.class),
        @XmlElement(name = "ais15", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS15.class),
        @XmlElement(name = "ais16", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS16.class),
        @XmlElement(name = "ais17", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS17.class),
        @XmlElement(name = "ais18", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS18.class),
        @XmlElement(name = "ais19", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS19.class),
        @XmlElement(name = "ais20", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS20.class),
        @XmlElement(name = "ais21", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS21.class),
        @XmlElement(name = "ais22", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS22.class),
        @XmlElement(name = "ais23", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS23.class),
        @XmlElement(name = "ais24", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS24.class),
        @XmlElement(name = "ais25", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS25.class),
        @XmlElement(name = "ais26", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS26.class),
        @XmlElement(name = "ais27", type = bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.AIS27.class)
    })
    List<NMEA> sentences;

    /**
     *
     */
    public Sentences() {
        sentences = new ArrayList<>();
    }

    /**
     *
     * @param data
     */
    public void add(NMEA data) {
        sentences.add(data);
    }

    /**
     *
     * @return
     */
    public List<NMEA> getSentences() {
        return sentences;
    }

    @Override
    public String toString() {
        return "Sentences{" + sentences + '}';
    }

}
