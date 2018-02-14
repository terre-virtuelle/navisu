package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bcnsaw")
public class BeaconSafeWater 
        extends Beacon
        implements  Serializable {

    public BeaconSafeWater(Long id) {
        super(id);
    }

    public BeaconSafeWater() {
    }

    public BeaconSafeWater(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public BeaconSafeWater(long id, String geometry) {
        super(id, geometry);
    }

    @Override
    public String toString() {
        return "BeaconSafeWater{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, bcnshp, colour, colpat, status, datend, datsta";
        request += "\n FROM bcnsaw";
        request += "\n WHERE bcnsaw.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }
}
