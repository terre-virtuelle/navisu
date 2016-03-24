/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
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
public class NavigationalWarningsSet {

    @XmlElements({
        @XmlElement(name = "avurnav", type = NavigationalWarnings.class)
    })
    private List<NavigationalWarnings> avurnavs;

    public NavigationalWarningsSet() {
        avurnavs = new ArrayList<>();
    }

    public NavigationalWarningsSet(List<NavigationalWarnings> avurnavs) {
        this.avurnavs = avurnavs;
    }

    public List<NavigationalWarnings> getAvurnavs() {
        return avurnavs;
    }

    public void setAvurnavs(List<NavigationalWarnings> avurnavs) {
        this.avurnavs = avurnavs;
    }

    public void add(NavigationalWarnings avurnav) {
        avurnavs.add(avurnav);
    }
}
