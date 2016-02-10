/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.ship.view;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationView;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 *
 * @author serge
 * @date Jan 25, 2016
 *
 */
public class ShipView
        extends NavigationView {

    @XmlElements({
        @XmlElement(name = "ship", type = Ship.class)
    })
    private Ship data;

    public ShipView() {
    }

    public ShipView(double x, double y) {
        super(x, y);
    }

    public ShipView(Ship data, double x, double y) {
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

    /**
     *
     * @return
     */
    @Override
    public long getId() {
        return data.getId();
    }

    @Override
    public String toString() {
        return "ShipView{" + "data=" + data + super.toString() + '}';
    }

}
