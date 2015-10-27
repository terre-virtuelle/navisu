package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boylat")
public class BuoyLateral extends Buoyage
        implements  Serializable {

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
