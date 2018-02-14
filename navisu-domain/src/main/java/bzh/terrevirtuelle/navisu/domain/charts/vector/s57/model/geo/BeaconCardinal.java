package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATCAM;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcncar")
public class BeaconCardinal
        extends Beacon
        implements Serializable {

    public BeaconCardinal() {
    }

    public BeaconCardinal(Long id) {
        this.id = id;
    }

    public BeaconCardinal(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconCardinal(long id, String geometry) {
        super(id, geometry);
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

    @Override
    public String toString() {
        return "BeaconCardinal{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, bcnshp, catcam, colour, colpat, status, datend, datsta";
        request += "\n FROM bcncar";
        request += "\n WHERE bcncar.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
