package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.geo;

import java.io.Serializable;

public class BuoyInstallation extends Buoyage
        implements Serializable {

    private String categoryOfInstallationBuoy;
    private String product;

    public BuoyInstallation(Long id) {
        this.id = id;
    }

    public BuoyInstallation() {
    }

    public String getCategoryOfInstallationBuoy() {
        return categoryOfInstallationBuoy;
    }

    public void setCategoryOfInstallationBuoy(String value) {
        this.categoryOfInstallationBuoy = value;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String value) {
        this.product = value;
    }

}
