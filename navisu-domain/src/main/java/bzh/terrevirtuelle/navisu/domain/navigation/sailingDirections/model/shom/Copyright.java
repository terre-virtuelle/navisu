/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 20, 2016
 *
 */
@XmlRootElement(name = "copyright")
@XmlAccessorType(XmlAccessType.FIELD)
public class Copyright {

    @XmlAttribute
    private String label;
    @XmlElement(name = "annee")
    private String year;
    @XmlElement(name = "pays")
    private String country;
    @XmlElement(name = "identite")
    private Identity identity;
    @XmlElement(name = "texte")
    private String text;

    public Copyright() {
    }

    public Copyright(String label, String year, String country, Identity identity, String text) {
        this.label = label;
        this.year = year;
        this.country = country;
        this.identity = identity;
        this.text = text;
    }

    /**
     *
     *
     * /**
     * Get the value of text
     *
     * @return the value of text
     */
    public String getText() {
        return text;
    }

    /**
     * Set the value of text
     *
     * @param text new value of text
     */
    public void setText(String text) {
        this.text = text;
    }

    /*
     * Get the value of identity
     *
     * @return the value of identity
     */
    public Identity getIdentity() {
        return identity;
    }

    /**
     * Set the value of identity
     *
     * @param identity new value of identity
     */
    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the value of year
     *
     * @return the value of year
     */
    public String getYear() {
        return year;
    }

    /**
     * Set the value of year
     *
     * @param year new value of year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Get the value of label
     *
     * @return the value of label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Set the value of label
     *
     * @param label new value of label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Copyright{" + "label=" + label + ", year=" + year + ", country=" + country + ", identity=" + identity + ", text=" + text + '}';
    }

}
