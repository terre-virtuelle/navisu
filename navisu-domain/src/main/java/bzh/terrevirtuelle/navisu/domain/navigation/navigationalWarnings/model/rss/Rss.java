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
@XmlType(name = "rss")
@XmlAccessorType(XmlAccessType.FIELD)
public class Rss
        implements  Serializable, Cloneable {

    Channel channel;

    public Rss() {
        channel = new Channel();
    }

    public Rss(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Rss{" + "channel=" + channel + '}';
    }
    
}
