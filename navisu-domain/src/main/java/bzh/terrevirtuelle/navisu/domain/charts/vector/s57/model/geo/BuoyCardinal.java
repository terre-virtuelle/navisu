package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boycar")
public class BuoyCardinal extends Buoy
        implements Serializable {

    public BuoyCardinal(Long id) {
        this.id = id;
    }

    public BuoyCardinal() {
    }

    @Override
    public String toString() {
        return "BuoyCardinal{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, boyshp, catcam, colour, colpat, status, datend, datsta";
        request += "\n FROM boycar";
        request += "\n WHERE boycar.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
