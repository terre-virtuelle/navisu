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
 * @date Feb 21, 2016
 *
 */
@XmlRootElement(name = "boitePostale")
@XmlAccessorType(XmlAccessType.FIELD)
public class MailBoxAddress {

    @XmlAttribute(name = "tBoitePostale")
    private String mailBox;
    @XmlValue
    private String value;

    public MailBoxAddress() {
    }

    public MailBoxAddress(String mailBox, String value) {
        this.mailBox = mailBox;
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

    /**
     * Get the value of mailBox
     *
     * @return the value of mailBox
     */
    public String getMailBox() {
        return mailBox;
    }

    /**
     * Set the value of mailBox
     *
     * @param mailBox new value of mailBox
     */
    public void setMailBox(String mailBox) {
        this.mailBox = mailBox;
    }

    @Override
    public String toString() {
        return "MailBoxAddress{" + "mailBox=" + mailBox + ", value=" + value + '}';
    }

}
