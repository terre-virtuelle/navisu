package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnisd")
public class BeaconIsolatedDanger
        extends Beacon
        implements Serializable {

    public BeaconIsolatedDanger() {
    }

    public BeaconIsolatedDanger(Long id) {
        this.id = id;
    }

    public BeaconIsolatedDanger(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconIsolatedDanger(long id, String geometry) {
        super(id, geometry);
    }
    @Override
    public String toString() {
        return "BeaconIsolatedDanger{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, bcnshp,  colour, colpat, status, datend, datsta";
        request += "\n FROM bcnisd";
        request += "\n WHERE bcnisd.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }
}
