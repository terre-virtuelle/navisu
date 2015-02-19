/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlType(name = "origin", propOrder = {"x", "y"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Origin {

    @XmlAttribute
    private String x;
    @XmlAttribute
    private String y;

    public Origin() {
    }

    public Origin(String x, String y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the value of y
     *
     * @return the value of y
     */
    public String getY() {
        return y;
    }

    /**
     * Set the value of y
     *
     * @param y new value of y
     */
    public void setY(String y) {
        this.y = y;
    }

    /**
     * Get the value of x
     *
     * @return the value of x
     */
    public String getX() {
        return x;
    }

    /**
     * Set the value of x
     *
     * @param x new value of x
     */
    public void setX(String x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "Origin{" + "x=" + x + ", y=" + y + '}';
    }
    
}
