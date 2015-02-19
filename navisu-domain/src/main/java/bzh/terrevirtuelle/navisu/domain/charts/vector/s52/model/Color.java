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
@XmlType(name = "color", propOrder = {"name", "r", "g", "b"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Color {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private String r;
    @XmlAttribute
    private String g;
    @XmlAttribute
    private String b;

    public Color() {
    }

    public Color(String name, String r, String g, String b) {
        this.name = name;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Get the value of b
     *
     * @return the value of b
     */
    public String getB() {
        return b;
    }

    /**
     * Set the value of b
     *
     * @param b new value of b
     */
    public void setB(String b) {
        this.b = b;
    }

    /**
     * Get the value of g
     *
     * @return the value of g
     */
    public String getG() {
        return g;
    }

    /**
     * Set the value of g
     *
     * @param g new value of g
     */
    public void setG(String g) {
        this.g = g;
    }

    /**
     * Get the value of r
     *
     * @return the value of r
     */
    public String getR() {
        return r;
    }

    /**
     * Set the value of r
     *
     * @param r new value of r
     */
    public void setR(String r) {
        this.r = r;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{" + "name=" + name + ", r=" + r + ", g=" + g + ", b=" + b + '}';
    }

}
