package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATCAM;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcncar")
public class BeaconCardinal extends Beacon
        implements Serializable {

    public BeaconCardinal() {
    }

    public BeaconCardinal(Long id) {
        this.id = id;
    }

    public BeaconCardinal(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconCardinal(long id, String geometry) {
        super(id, geometry);
    }

    public String getCategoryOfCardinalMark() {
        return categoryOfMark;
    }

    @Override
    public String getCategoryOfMarkMeaning(String cat) {
        return CATCAM.ATT.get(cat);
    }

    public void setCategoryOfCardinalMark(String value) {
        categoryOfMark = value;
    }

    @Override
    public String toString() {
        return "BeaconCardinal{" + super.toString() + '}';
    }

}
