/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.avurnav;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.SailingDirections;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 */
@XmlRootElement
@XmlType(name = "avurnavSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class AvurnavSet {

    @XmlElements({
        @XmlElement(name = "avurnav", type = Avurnav.class)
    })
    private List<Avurnav> avurnavs;

    public AvurnavSet() {
        avurnavs = new ArrayList<>();
    }

    public AvurnavSet(List<Avurnav> avurnavs) {
        this.avurnavs = avurnavs;
    }

    public List<Avurnav> getAvurnavs() {
        return avurnavs;
    }

    public void setAvurnavs(List<Avurnav> avurnavs) {
        this.avurnavs = avurnavs;
    }

    public void add(Avurnav avurnav) {
        avurnavs.add(avurnav);
    }
}
