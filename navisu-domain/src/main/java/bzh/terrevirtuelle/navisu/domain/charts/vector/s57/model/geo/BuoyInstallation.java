package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boyinb")
public class BuoyInstallation extends Buoy
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

    @Override
    public String toString() {
        return "BuoyInstallation{" + "categoryOfInstallationBuoy=" + categoryOfInstallationBuoy + ", product=" + product + super.toString() + '}';
    }

    @Override
    public String spatialRequest(double lat0, double lon0, double lat1, double lon1, String epsg) {
  String request = "SELECT ST_AsText(ST_GeometryN(geom, 1)),"
                + " objnam, rcid, boyshp, catinb, colour, colpat, status, datend, datsta";
        request += "\n FROM boyinb";
        request += "\n WHERE boyinb.geom && ST_MakeEnvelope("
                + Double.toString(lat0) + ", " + Double.toString(lon0) + ", "
                + Double.toString(lat1) + ", " + Double.toString(lon1) + ", "
                + epsg + ");";
        return request;  }

}
