/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlType(name = "line-style")
@XmlAccessorType(XmlAccessType.FIELD)
public class LineStyle {

    @XmlAttribute(name = "RCID")
    private String rcid;
    private String name;
    private Vector vector;
     private String description;
     @XmlElement(name = "HPGL")
    private String hpgl;

    @XmlElement(name = "color-ref")
    private String colorRef;
    

    public LineStyle() {
    }

    public LineStyle(String rcid, String name, Vector vector, String description, String hpgl, String colorRef) {
        this.rcid = rcid;
        this.name = name;
        this.vector = vector;
        this.description = description;
        this.hpgl = hpgl;
        this.colorRef = colorRef;
    }

   

    /**
     * Get the value of hpgl
     *
     * @return the value of hpgl
     */
    public String getHpgl() {
        return hpgl;
    }

    /**
     * Set the value of hpgl
     *
     * @param hpgl new value of hpgl
     */
    public void setHpgl(String hpgl) {
        this.hpgl = hpgl;
    }

   
    /**
     * Get the value of vector
     *
     * @return the value of vector
     */
    public Vector getVector() {
        return vector;
    }

    /**
     * Set the value of vector
     *
     * @param vector new value of vector
     */
    public void setVector(Vector vector) {
        this.vector = vector;
    }

    /**
     * Get the value of colorRef
     *
     * @return the value of colorRef
     */
    public String getColorRef() {
        return colorRef;
    }

    /**
     * Set the value of colorRef
     *
     * @param colorRef new value of colorRef
     */
    public void setColorRef(String colorRef) {
        this.colorRef = colorRef;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
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

   
}
