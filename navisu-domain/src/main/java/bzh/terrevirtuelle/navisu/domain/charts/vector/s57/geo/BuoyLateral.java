package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import java.io.Serializable;

public class BuoyLateral extends Buoyage
        implements Serializable {

    public BuoyLateral(Long id) {
        this.id = id;
    }

    public BuoyLateral() {
    }

    public String getCategoryOfLateralMark() {
        return categoryOfMark;
    }

    public void setCategoryOfLateralMark(String value) {
        categoryOfMark = value;
    }

}
