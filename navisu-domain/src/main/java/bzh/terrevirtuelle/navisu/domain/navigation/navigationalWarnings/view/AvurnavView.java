/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.view;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.NavigationalWarnings;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationView;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 *
 * @author serge
 * @date Jan 25, 2016
 *
 */
public class AvurnavView
        extends NavigationView {

    @XmlElements({
        @XmlElement(name = "avurnav", type = NavigationalWarnings.class)
    })
    private NavigationalWarnings data;

    public AvurnavView() {
    }

    public AvurnavView(double x, double y) {
        super(x, y);
    }

    public AvurnavView(NavigationalWarnings data, double x, double y) {
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
        return "AvurnavView{" + "data=" + data + super.toString() + '}';
    }

}
