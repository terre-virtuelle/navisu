/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 */
@XmlRootElement
/*
@XmlType(name = "s57Chart"
        ,
        propOrder = {
            "id", 
            "number",
            "latitude", 
            "longitude", 
            "geometry", 
            "description"}
)
*/
@XmlAccessorType(XmlAccessType.FIELD)
public class S57Chart
        extends Location
        implements NavigationData {

    private String description;
    private String number;

    public S57Chart() {
        super(0, 0.0, 0.0);
    }

    public S57Chart(long id, String geometry) {
        super(id, geometry);
    }

    public S57Chart(long id, double lat, double lon, String description, String number) {
        super(id, lat, lon);
        this.description = description;
        this.number = number;
    }

    public S57Chart(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
