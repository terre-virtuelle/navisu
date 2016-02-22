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
@XmlRootElement(name = "identite")
@XmlAccessorType(XmlAccessType.FIELD)
public class Identity {

    @XmlElement(name = "prenom")
    private String firstname;
    @XmlElement(name = "nom")
    private String lastname;
    @XmlElement(name = "qualite")
    private String grade;
    @XmlElement(name = "fonction")
    private String occupation;
    @XmlElement(name = "organisation")
    private String organization;
    @XmlElement(name = "abrev")
    private String breviation;

    public Identity() {
    }

    public Identity(String firstname, String lastname, String grade, String occupation, String organization, String breviation) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.grade = grade;
        this.occupation = occupation;
        this.organization = organization;
        this.breviation = breviation;
    }

    /**
     * Get the value of breviation
     *
     * @return the value of breviation
     */
    public String getBreviation() {
        return breviation;
    }

    /**
     * Set the value of breviation
     *
     * @param breviation new value of breviation
     */
    public void setBreviation(String breviation) {
        this.breviation = breviation;
    }


    /**
     * Get the value of organization
     *
     * @return the value of organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Set the value of organization
     *
     * @param organization new value of organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }


    /**
     * Get the value of occupation
     *
     * @return the value of occupation
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Set the value of occupation
     *
     * @param occupation new value of occupation
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    /**
     * Get the value of grade
     *
     * @return the value of grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Set the value of grade
     *
     * @param grade new value of grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Get the value of lastname
     *
     * @return the value of lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the value of lastname
     *
     * @param lastname new value of lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Get the value of firstname
     *
     * @return the value of firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the value of firstname
     *
     * @param firstname new value of firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Identity{" + "firstname=" + firstname + ", lastname=" + lastname + ", grade=" + grade + ", occupation=" + occupation + ", organization=" + organization + ", breviation=" + breviation + '}';
    }

}
