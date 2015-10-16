/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 16 oct. 2015
 * @author Serge Morvan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "poi")
@XmlRootElement
public class Poi {

    @XmlElements({   
    @XmlElement(name = "bcncar", type = BeaconCardinal.class),
    @XmlElement(name = "bcnisd", type = BeaconIsolatedDanger.class),
    @XmlElement(name = "bcnlat", type = BeaconLateral.class),
    @XmlElement(name = "bcnsaw", type = BeaconSafeWater.class),
    @XmlElement(name = "bcnspp", type = BeaconSpecialPurpose.class),
    @XmlElement(name = "boycar", type = BuoyCardinal.class),
    @XmlElement(name = "boyinb", type = BuoyInstallation.class),
    @XmlElement(name = "boyisd", type = BuoyIsolatedDanger.class),
    @XmlElement(name = "boylat", type = BuoyLateral.class),
    @XmlElement(name = "boysaw", type = BuoySafeWater.class),
    @XmlElement(name = "boyssp", type = BuoySpecialPurpose.class),
    @XmlElement(name = "morfac", type = MooringWarpingFacility.class),
    @XmlElement(name = "lndmrk", type = Landmark.class)
    })

    protected List<Location> locations = new ArrayList<>();
    
    public Poi() {
    }

    public void add(Location o) {
        locations.add(o);
    }

    @Override
    public String toString() {
        return "Poi{" + "locations=" + locations + '}';
    }

}
