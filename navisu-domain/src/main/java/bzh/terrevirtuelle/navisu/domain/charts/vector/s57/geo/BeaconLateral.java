package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.parameters.CATLAM;
import java.io.Serializable;

public class BeaconLateral extends Beacon
        implements Serializable {

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
