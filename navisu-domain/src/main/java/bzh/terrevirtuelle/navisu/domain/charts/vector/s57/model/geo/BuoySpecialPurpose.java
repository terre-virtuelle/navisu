package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boyspp")
public class BuoySpecialPurpose extends Buoy
        implements NavigationData, Serializable {

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
