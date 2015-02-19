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
@XmlType(name = "pattern")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pattern {

    @XmlAttribute(name = "RCID")
    private String rcid;
    private String name;
    private String definition;
    private String filltype;
    private String spacing;
    private Vector vector;
    private String description;
    @XmlElement(name = "color-ref")
    private String colorRef;
    @XmlElement(name = "HPGL")
    private String hpgl;

    public Pattern() {
    }

    public Pattern(String rcid, String name, String definition, String filltype, String spacing, Vector vector, String description, String colorRef, String hpgl) {
        this.rcid = rcid;
        this.name = name;
        this.definition = definition;
        this.filltype = filltype;
        this.spacing = spacing;
        this.vector = vector;
        this.description = description;
        this.colorRef = colorRef;
        this.hpgl = hpgl;
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
     * Get the value of spacing
     *
     * @return the value of spacing
     */
    public String getSpacing() {
        return spacing;
    }

    /**
     * Set the value of spacing
     *
     * @param spacing new value of spacing
     */
    public void setSpacing(String spacing) {
        this.spacing = spacing;
    }

    /**
     * Get the value of filltype
     *
     * @return the value of filltype
     */
    public String getFilltype() {
        return filltype;
    }

    /**
     * Set the value of filltype
     *
     * @param filltype new value of filltype
     */
    public void setFilltype(String filltype) {
        this.filltype = filltype;
    }

    /**
     * Get the value of definition
     *
     * @return the value of definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Set the value of definition
     *
     * @param definition new value of definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
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

    @Override
    public String toString() {
        return "Pattern{" + "rcid=" + rcid + ", name=" + name + ", definition=" + definition + ", filltype=" + filltype + ", spacing=" + spacing + ", vector=" + vector + ", description=" + description + ", colorRef=" + colorRef + ", hpgl=" + hpgl + '}';
    }

}
