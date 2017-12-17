package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boycar")
public class BuoyCardinal extends Buoy
        implements  Serializable {

    public BuoyCardinal(Long id) {
        this.id = id;
    }

    public BuoyCardinal() {
    }

}
