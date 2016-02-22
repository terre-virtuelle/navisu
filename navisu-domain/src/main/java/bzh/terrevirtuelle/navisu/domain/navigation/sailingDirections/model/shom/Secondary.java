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
@XmlRootElement(name = "secondaire")
@XmlAccessorType(XmlAccessType.FIELD)
public class Secondary implements TextPart {

    @XmlAttribute
    private String idref;
    @XmlValue
    private String value;

    public Secondary() {
    }

    public Secondary(String idref) {
        this.idref = idref;
    }

    public Secondary(String idref, String name) {
        this.idref = idref;
        this.value = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIdref() {
        return idref;
    }

    public void setIdref(String idref) {
        this.idref = idref;
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
        return "Secondaire{" + "idref=" + idref + ", name=" + value + '}';
    }

}
