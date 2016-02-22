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
@XmlRootElement(name = "titre")
@XmlAccessorType(XmlAccessType.FIELD)
public class MdTitle {

    @XmlAttribute(name = "tTitre")
    private String tTitle;
    @XmlElement(name = "sTitre")
    private String sTitle;
    @XmlElement(name = "pays")
    private String country;
    @XmlElement(name = "region")
    private String region;

    public MdTitle() {
    }

    public MdTitle(String tTitle, String sTitle, String country, String region) {
        this.tTitle = tTitle;
        this.sTitle = sTitle;
        this.country = country;
        this.region = region;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(String region) {
        this.region = region;
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
     * Get the value of sTitle
     *
     * @return the value of sTitle
     */
    public String getsTitle() {
        return sTitle;
    }

    /**
     * Set the value of sTitle
     *
     * @param sTitle new value of sTitle
     */
    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    /**
     * Get the value of tTitle
     *
     * @return the value of tTitle
     */
    public String gettTitle() {
        return tTitle;
    }

    /**
     * Set the value of tTitle
     *
     * @param tTitle new value of tTitle
     */
    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    @Override
    public String toString() {
        return "MdTitle{" + "tTitle=" + tTitle + ", sTitle=" + sTitle + ", country=" + country + ", region=" + region + '}';
    }

}
