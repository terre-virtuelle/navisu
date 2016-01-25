/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.ship.view;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationView;
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
        implements NavigationView {

    @XmlElements({
        @XmlElement(name = "ship", type = Ship.class)
    })
    private NavigationData data;
    private double x;
    private double y;

    public ShipView() {
    }

    public ShipView(NavigationData data, double x, double y) {
        this.data = data;
        this.x = x;
        this.y = y;
    }

    @Override
    public NavigationData getData() {
        return data;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
}
