/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RSD RADAR System Data
 * @author Serge Morvan
 */
@XmlRootElement(name="RSD")
@XmlAccessorType(XmlAccessType.FIELD)
public class RSD
        extends NMEA {
    
}
