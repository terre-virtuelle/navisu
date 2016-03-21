/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.view;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationView;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 *
 * @author serge
 * @date Jan 25, 2016
 *
 */
public class SailingDirectionsViewOld
        extends NavigationView {

    @XmlElements({
        @XmlElement(name = "sailingDirections", type = SailingDirections.class)
    })
    
    private SailingDirections data;

    public SailingDirectionsViewOld() {
    }

    public SailingDirectionsViewOld(double x, double y) {
        super(x, y);
    }

    public SailingDirectionsViewOld(SailingDirections data, double x, double y) {
        super(x, y);
        this.data = data;
    }

    

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    @Override
    public NavigationData getData() {
        return data;
    }

    @Override
    public double getLatitude() {
        if (data != null) {
            return data.getLatitude();
        } else {
            return 0.0;
        }
    }

    @Override
    public double getLongitude() {
        if (data != null) {
            return data.getLongitude();
        } else {
            return 0.0;
        }
    }

    @Override
    public String getGeometry() {
        if (data != null) {
            return data.getGeometry();
        } else {
            return "";
        }
    }

    @Override
    public String toString() {
        return "SailingDirectionsView{" + "data=" + data + super.toString() + '}';
    }


}
