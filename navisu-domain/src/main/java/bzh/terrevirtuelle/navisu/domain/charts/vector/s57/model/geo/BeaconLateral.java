package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.CATLAM;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnlat")
public class BeaconLateral extends Beacon
        implements  Serializable {

    public BeaconLateral(Long id) {
        this.id = id;
    }

    public BeaconLateral() {
    }

    public String getCategoryOfLateralMark() {
        return categoryOfMark;
    }

    @Override
    public String getCategoryOfMarkMeaning(String cat) {
        return CATLAM.ATT.get(cat);
    }

    public void setCategoryOfLateralMark(String value) {
        this.categoryOfMark = value;
    }
}
