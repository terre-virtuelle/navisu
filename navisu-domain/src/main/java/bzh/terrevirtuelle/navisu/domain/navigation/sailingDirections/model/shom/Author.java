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
@XmlRootElement(name = "auteur")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    @XmlElement(name = "identite")
    private Identity identity;
    @XmlElement(name = "adresse")
    private Address address;
    @XmlElement(name = "contact")
    private Contact contact;
    

    public Author() {
    }

    public Author(Identity identity, Address address, Contact contact) {
        this.identity = identity;
        this.address = address;
        this.contact = contact;
    }

    /**
     * Get the value of contact
     *
     * @return the value of contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * Set the value of contact
     *
     * @param contact new value of contact
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
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

    @Override
    public String toString() {
        return "Author{" + "identity=" + identity + ", address=" + address + ", contact=" + contact + '}';
    }

}
