/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.constants.BCNSHP;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge Morvan
 * @date 2 oct. 2014 NaVisu project
 */
//@XmlTransient
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Beacon
        extends Buoyage {

    public Beacon() {
    }

    public Beacon(long id) {
        super(id);
    }

    public Beacon(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public Beacon(long id, String geometry) {
        super(id, geometry);
    }

    public String getBeaconShape() {
        return shape;
    }

    public void setBeaconShape(String value) {
        shape = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     *
     * @param shape
     * @return
     */
    @Override
    public String getShapeMeaning(String shape) {
        return super.getShapeMeaning(BCNSHP.ATT.get(shape));
    }

    
}
