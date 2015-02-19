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
@XmlType(name = "distance", propOrder = {"min", "max"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Distance {

    @XmlAttribute
    private String min;
    @XmlAttribute
    private String max;

    public Distance() {
    }

    public Distance(String min, String max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Get the value of max
     *
     * @return the value of max
     */
    public String getMax() {
        return max;
    }

    /**
     * Set the value of max
     *
     * @param max new value of max
     */
    public void setMax(String max) {
        this.max = max;
    }

    /**
     * Get the value of min
     *
     * @return the value of min
     */
    public String getMin() {
        return min;
    }

    /**
     * Set the value of min
     *
     * @param min new value of min
     */
    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "Distance{" + "min=" + min + ", max=" + max + '}';
    }

}
