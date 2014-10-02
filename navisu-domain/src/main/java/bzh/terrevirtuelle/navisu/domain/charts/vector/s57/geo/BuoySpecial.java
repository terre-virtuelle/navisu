package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import java.io.Serializable;

public class BuoySpecial extends Buoyage
        implements Serializable {

    public BuoySpecial(Long id) {
        this.id = id;
    }

    public BuoySpecial() {
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        categoryOfMark = value;
    }
}
