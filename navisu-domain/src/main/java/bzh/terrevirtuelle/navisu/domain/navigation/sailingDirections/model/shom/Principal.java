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
@XmlRootElement(name = "principal")
@XmlAccessorType(XmlAccessType.FIELD)
public class Principal implements TextPart {

    @XmlAttribute
    private String id;
    @XmlValue
    private String value;

    public Principal() {
    }

    public Principal(String id) {
        this.id = id;
    }

    public Principal(String id, String name) {
        this.id = id;
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
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
        return "Principal{" + "id=" + id + ", name=" + value + '}';
    }

}
