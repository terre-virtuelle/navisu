package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnisd")
public class BeaconIsolatedDanger extends Beacon
        implements Serializable {

    public BeaconIsolatedDanger(Long id) {
        this.id = id;
    }

    public BeaconIsolatedDanger(String wkt) {
        super(wkt);
    }

    public BeaconIsolatedDanger(double lat, double lon) {
        super(lat, lon);
    }

    public BeaconIsolatedDanger() {
    }
}
