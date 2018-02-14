package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.CATLAM;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnlat")
public class BeaconLateral
        extends Beacon
        implements Serializable {

    public BeaconLateral() {
    }

    public BeaconLateral(Long id) {
        super(id);
    }

    public BeaconLateral(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconLateral(long id, String geometry) {
        super(id, geometry);
    }

    public String getCategoryOfLateralMark() {
        return categoryOfMark;
    }

    @Override
    public String getCategoryOfMarkMeaning(String cat) {
        return CATLAM.ATT.get(cat);
    }

    public void setCategoryOfLateralMark(String value) {
        this.categoryOfMark = value;
    }
    @Override
    public String toString() {
        return "BeaconLateral{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, bcnshp, catlam, colour, colpat, status, datend, datsta";
        request += "\n FROM bcnlat";
        request += "\n WHERE bcnlat.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }
}
