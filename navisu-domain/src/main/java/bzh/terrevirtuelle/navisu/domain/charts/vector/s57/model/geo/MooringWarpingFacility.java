package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "morfac")
public class MooringWarpingFacility extends Buoyage
        implements Serializable {

    private String buoyShape;
    private String categoryOfMooring;
    private String waterLevelEffect;

    public MooringWarpingFacility(Long id) {
        this.id = id;
    }

    public MooringWarpingFacility() {
    }

    public String getBuoyShape() {
        return buoyShape;
    }

    public void setBuoyShape(String value) {
        this.buoyShape = value;
    }

    public String getCategoryOfMooring() {
        return categoryOfMooring;
    }

    public void setCategoryOfMooring(String value) {
        this.categoryOfMooring = value;
    }

    @Override
    public void setCategoryOfMark(String value) {
        this.categoryOfMooring = categoryOfMark = value;
    }

    @Override
    public String getCategoryOfMark() {
        return categoryOfMark;
    }

    public String getWaterLevelEffect() {
        return waterLevelEffect;
    }

    public void setWaterLevelEffect(String value) {
        this.waterLevelEffect = value;
    }

    @Override
    public String toString() {
        return "MooringWarpingFacility{" + "buoyShape=" 
                + buoyShape + ", categoryOfMooring=" 
                + categoryOfMooring + ", waterLevelEffect=" 
                + waterLevelEffect + super.toString() + '}';
    }
    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
        String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + "objnam, rcid, boyshp, catcam, colour, colpat, status, datend, datsta";

        return request;
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1) {
        return spatialRequest(lat0, lon0, lat1, lon1, "4326");
    }
}
