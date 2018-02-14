package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boyisd")
public class BuoyIsolatedDanger extends Buoy
        implements Serializable {

    public BuoyIsolatedDanger(Long id) {
        this.id = id;
    }

    public BuoyIsolatedDanger() {
    }

    @Override
    public String toString() {
        return "BuoyIsolatedDanger{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, boyshp,  colour, colpat, status, datend, datsta";
        request += "\n FROM boyisd";
        request += "\n WHERE boyisd.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
