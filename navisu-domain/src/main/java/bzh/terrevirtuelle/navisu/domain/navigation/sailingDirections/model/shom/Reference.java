/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "reference")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reference implements TextPart {

    @XmlAttribute
    private String tReference;
    @XmlValue
    private String value;

    public Reference() {
    }

    public Reference(String tReference, String name) {
        this.tReference = tReference;
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the value of tReference
     *
     * @return the value of tReference
     */
    public String gettReference() {
        return tReference;
    }

    /**
     * Set the value of tReference
     *
     * @param tReference new value of tReference
     */
    public void settReference(String tReference) {
        this.tReference = tReference;
    }

    @Override
    public boolean contains(CharSequence str) {
        return value.contains(str);
    }

    @Override
    public String shorten() {
        return value;
    }

    @Override
    public String toString() {
        return "Reference{" + "tReference=" + tReference + ", name=" + value + '}';
    }

}
