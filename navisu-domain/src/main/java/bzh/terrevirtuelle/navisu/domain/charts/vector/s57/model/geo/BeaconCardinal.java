package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.CATCAM;
import java.io.Serializable;

public class BeaconCardinal extends Beacon
        implements Serializable {

    public BeaconCardinal(Long id) {
        this.id = id;
    }

    public BeaconCardinal() {
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

    
}
