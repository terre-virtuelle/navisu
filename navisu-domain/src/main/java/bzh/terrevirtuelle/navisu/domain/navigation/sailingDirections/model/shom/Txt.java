/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "txt")
@XmlAccessorType(XmlAccessType.FIELD)
public class Txt implements TextPart {

    @XmlValue
    private String value;

    public Txt() {
    }

    public Txt(String value) {
        this.value = value;
    }

    /**
     * Get the value of value
     *
     * @return the value of value
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value of value
     *
     * @param value new value of value
     */
    public void setValue(String value) {
        this.value = value;
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
        return "Txt{" + value + '}';
    }

}
