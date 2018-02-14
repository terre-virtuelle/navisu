package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boylat")
public class BuoyLateral extends Buoy
        implements Serializable {

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

    @Override
    public String toString() {
        return "BuoyLateral{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, boyshp, catlam, colour, colpat, status, datend, datsta";
        request += "\n FROM boylat";
        request += "\n WHERE boylat.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
