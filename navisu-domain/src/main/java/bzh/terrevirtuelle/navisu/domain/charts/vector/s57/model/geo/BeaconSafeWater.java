package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnsaw")
public class BeaconSafeWater extends Buoyage
        implements  Serializable {

    public BeaconSafeWater(Long id) {
        super(id);
    }

    public BeaconSafeWater() {
    }

    public BeaconSafeWater(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconSafeWater(long id, String geometry) {
        super(id, geometry);
    }

    
}
