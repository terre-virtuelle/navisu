package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lndmrk")
public class Landmark extends Buoyage
        implements Serializable {

    public Landmark(Long id) {
        this.id = id;
    }

    public Landmark() {
    }

    public String getCategoryOfLandMark() {
        return categoryOfMark;
    }

    public void setCategoryOfLandMark(String value) {
        this.categoryOfMark = value;
    }

    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String value) {
        this.function = value;
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + "objnam, rcid, lnma, catcam, colour, colpat, status, datend, datsta";

        return request;
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1) {
        return spatialRequest(lat0, lon0, lat1, lon1, "4326");
    }

    @Override
    public String toString() {
        return "Landmark{" + super.toString()+'}';
    }
    
}
