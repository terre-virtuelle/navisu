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
@XmlType(name = "symbol")
@XmlAccessorType(XmlAccessType.FIELD)
public class Symbol {

    @XmlAttribute(name = "RCID")
    private String rcid;

    private String name;

    private String description;

    private Bitmap bitmap;
    @XmlElement(name = "color-ref")
    private String colorRef;

    private Vector vector;

    private String definition;

    public Symbol() {
    }

    public Symbol(String rcid, String name, String description, Bitmap bitmap, String colorRef, Vector vector, String definition) {
        this.rcid = rcid;
        this.name = name;
        this.description = description;
        this.bitmap = bitmap;
        this.colorRef = colorRef;
        this.vector = vector;
        this.definition = definition;
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
     * Get the value of bitmap
     *
     * @return the value of bitmap
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * Set the value of bitmap
     *
     * @param bitmap new value of bitmap
     */
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
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
        return "Symbol{" + "rcid=" + rcid + ", name=" + name + ", description=" + description + ", bitmap=" + bitmap + ", colorRef=" + colorRef + ", vector=" + vector + ", definition=" + definition + '}';
    }

}
