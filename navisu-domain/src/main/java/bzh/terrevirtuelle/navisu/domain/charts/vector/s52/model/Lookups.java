/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

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
 * @date 13 juil. 2014 NaVisu project
 */
@XmlRootElement(name = "lookups")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lookups {

    @XmlElements({
        @XmlElement(name = "lookup", type = Lookup.class)
    })
    List<Lookup> lookups;

    public Lookups() {
        lookups = new ArrayList<>();
    }

    public void add(Lookup data) {
        lookups.add(data);
    }

    public List<Lookup> getLookups() {
        return lookups;
    }

    @Override
    public String toString() {
        return "Lookups{" + "lookups=" + lookups + '}';
    }

}
