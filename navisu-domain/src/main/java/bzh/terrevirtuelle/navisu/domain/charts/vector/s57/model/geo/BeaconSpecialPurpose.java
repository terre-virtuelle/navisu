package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnspp")
public class BeaconSpecialPurpose 
        extends Beacon
        implements Serializable {

    private String beaconShape;
    private String categoryOfSpecialPurposeMark;

    public BeaconSpecialPurpose() {
    }

    public BeaconSpecialPurpose(Long id) {
        this.id = id;
    }

    public BeaconSpecialPurpose(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconSpecialPurpose(long id, String geometry) {
        super(id, geometry);
    }

    @Override
    public String getBeaconShape() {
        return beaconShape;
    }

    public void setBeaconShape(String value) {
        this.beaconShape = value;
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfSpecialPurposeMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        this.categoryOfSpecialPurposeMark = value;
    }

    private String marksNavigationalSystemof;

    public String getMarksNavigationalSystemof() {
        return marksNavigationalSystemof;
    }

    public void setMarksNavigationalSystemof(String value) {
        this.marksNavigationalSystemof = value;
    }

}
