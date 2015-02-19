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
@XmlType(name = "vector")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vector {

    @XmlAttribute
    private String width;
    @XmlAttribute
    private String height;

    private Distance distance;
    private Pivot pivot;
    private Origin origin;
    @XmlElement(name = "HPGL")
    private String hpgl;

    public Vector() {
    }

    public Vector(String width, String height, Distance distance, Pivot pivot, Origin origin, String hpgl) {
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.pivot = pivot;
        this.origin = origin;
        this.hpgl = hpgl;
    }

    public Vector(String width, String height, Distance distance, Pivot pivot, Origin origin) {
        this.width = width;
        this.height = height;
        this.distance = distance;
        this.pivot = pivot;
        this.origin = origin;
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
     * Get the value of origin
     *
     * @return the value of origin
     */
    public Origin getOrigin() {
        return origin;
    }

    /**
     * Set the value of origin
     *
     * @param origin new value of origin
     */
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    /**
     * Get the value of pivot
     *
     * @return the value of pivot
     */
    public Pivot getPivot() {
        return pivot;
    }

    /**
     * Set the value of pivot
     *
     * @param pivot new value of pivot
     */
    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    /**
     * Get the value of distance
     *
     * @return the value of distance
     */
    public Distance getDistance() {
        return distance;
    }

    /**
     * Set the value of distance
     *
     * @param distance new value of distance
     */
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    /**
     * Get the value of height
     *
     * @return the value of height
     */
    public String getHeight() {
        return height;
    }

    /**
     * Set the value of height
     *
     * @param height new value of height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public String getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Vector{" + "width=" + width + ", height=" + height + ", distance=" + distance + ", pivot=" + pivot + ", origin=" + origin + ", hpgl=" + hpgl + '}';
    }

}
