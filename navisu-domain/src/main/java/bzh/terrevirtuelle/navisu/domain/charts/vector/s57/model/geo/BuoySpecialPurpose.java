package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;

public class BuoySpecialPurpose extends Buoyage
        implements Serializable {

    public BuoySpecialPurpose(Long id) {
        this.id = id;
    }

    public BuoySpecialPurpose() {
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        categoryOfMark = value;
    }
}
