package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnisd")
public class BeaconIsolatedDanger
        extends Beacon
        implements Serializable {

    public BeaconIsolatedDanger() {
    }

    public BeaconIsolatedDanger(Long id) {
        this.id = id;
    }

    public BeaconIsolatedDanger(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconIsolatedDanger(long id, String geometry) {
        super(id, geometry);
    }
    @Override
    public String toString() {
        return "BeaconIsolatedDanger{" + super.toString() + '}';
    }
}
