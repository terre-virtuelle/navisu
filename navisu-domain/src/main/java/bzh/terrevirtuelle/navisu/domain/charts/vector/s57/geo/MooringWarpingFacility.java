package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import java.io.Serializable;

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
}
