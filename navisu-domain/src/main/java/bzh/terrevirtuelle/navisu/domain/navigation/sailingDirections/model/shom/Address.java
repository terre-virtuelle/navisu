/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 20, 2016
 *
 */
@XmlRootElement(name = "adresse")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

    @XmlElement(name = "numero")
    private String number;
    @XmlElement(name = "rue")
    private String street;
    @XmlElement(name = "courseSpeciale")
    private String cedex0;
    @XmlElement(name = "boitePostale")
    private MailBoxAddress mailBoxAddress;
    @XmlElement(name = "ville")
    private String town;
    @XmlElement(name = "cedex")
    private String cedex;
    @XmlElement(name = "pays")
    private String country;

    public Address() {
    }

    public Address(String number, String street, String cedex0, MailBoxAddress mailBoxAddress, String town, String cedex, String country) {
        this.number = number;
        this.street = street;
        this.cedex0 = cedex0;
        this.mailBoxAddress = mailBoxAddress;
        this.town = town;
        this.cedex = cedex;
        this.country = country;
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
     * Get the value of cedex
     *
     * @return the value of cedex
     */
    public String getCedex() {
        return cedex;
    }

    /**
     * Set the value of cedex
     *
     * @param cedex new value of cedex
     */
    public void setCedex(String cedex) {
        this.cedex = cedex;
    }

    /**
     * Get the value of town
     *
     * @return the value of town
     */
    public String getTown() {
        return town;
    }

    /**
     * Set the value of town
     *
     * @param town new value of town
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * Get the value of mailBoxAddress
     *
     * @return the value of mailBoxAddress
     */
    public MailBoxAddress getMailBoxAddress() {
        return mailBoxAddress;
    }

    /**
     * Set the value of mailBoxAddress
     *
     * @param mailBoxAddress new value of mailBoxAddress
     */
    public void setMailBoxAddress(MailBoxAddress mailBoxAddress) {
        this.mailBoxAddress = mailBoxAddress;
    }

    /**
     * Get the value of cedex
     *
     * @return the value of cedex
     */
    public String getCedex0() {
        return cedex0;
    }

    /**
     * Set the value of cedex
     *
     * @param cedex new value of cedex
     */
    public void setCedex0(String cedex) {
        this.cedex0 = cedex;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the value of number
     *
     * @return the value of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set the value of number
     *
     * @param number new value of number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" + "number=" + number + ", street=" + street + ", cedex0=" + cedex0 + ", mailBoxAddress=" + mailBoxAddress + ", town=" + town + ", cedex=" + cedex + ", country=" + country + '}';
    }

}
