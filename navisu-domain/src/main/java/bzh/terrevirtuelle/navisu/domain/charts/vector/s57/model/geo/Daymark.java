package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "daymark")
public class Daymark extends Buoyage
        implements Serializable {

    public Daymark(Long id) {
        this.id = id;
    }

    public Daymark() {
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        setCategoryOfMark(categoryOfMark);
    }
    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + "objnam, rcid, topshp, catcam, colour, colpat, status, datend, datsta";

        return request;
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1) {
        return spatialRequest(lat0, lon0, lat1, lon1, "4326");
    }
}
