package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boyspp")
public class BuoySpecialPurpose 
        extends Buoy
        implements NavigationData, Serializable {

    public BuoySpecialPurpose(Long id) {
        this.id = id;
    }

    public BuoySpecialPurpose() {
    }

    public String getCategoryOfSpecialPurposeMark() {
        return categoryOfMark;
    }

    public void setCategoryOfSpecialPurposeMark(String value) {
        categoryOfMark = value;
    }

    @Override
    public String toString() {
        return "BuoySpecialPurpose{" + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, boyshp, catspm, colour, colpat, status, datend, datsta";
        request += "\n FROM boyspp";
        request += "\n WHERE boyspp.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;
    }

}
