package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;

public class Daymark extends Buoyage
        implements Serializable {

    public Daymark(Long id) {
        this.id = id;
    }

    public Daymark() {
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        setCategoryOfMark(categoryOfMark);
    }
}
