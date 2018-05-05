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

    @Override
    public String toString() {
     return "BeaconSpecialPurpose{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, bcnshp, catspm, colour, colpat, status, datend, datsta";
        request += "\n FROM bcnspp";
        request += "\n WHERE bcnspp.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
