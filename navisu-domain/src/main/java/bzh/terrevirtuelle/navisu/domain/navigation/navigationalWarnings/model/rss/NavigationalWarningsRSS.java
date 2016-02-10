/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 27 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "avurnavrss")
@XmlAccessorType(XmlAccessType.FIELD)
public class NavigationalWarningsRSS
        implements  Serializable, Cloneable {

    private Rss rss;
    private Long id;

    public NavigationalWarningsRSS() {
    }

    public NavigationalWarningsRSS(Rss rss) {
        this.rss = rss;
    }

    /**
     * Get the value of rss
     *
     * @return the value of rss
     */
    public Rss getRss() {
        return rss;
    }

    /**
     * Set the value of rss
     *
     * @param rss new value of rss
     */
    public void setRss(Rss rss) {
        this.rss = rss;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
