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
@XmlRootElement(name = "numerotation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Numbering {

    @XmlElement(name = "issn")
    private String issn;
    @XmlAttribute(name = "isbn")
    private String isbn;
    @XmlElement(name = "serie")
    private String serie;
    @XmlElement(name = "volume")
    private String volume;

    public Numbering() {
    }

    public Numbering(String issn, String isbn, String serie, String volume) {
        this.issn = issn;
        this.isbn = isbn;
        this.serie = serie;
        this.volume = volume;
    }

    /**
     * Get the value of volume
     *
     * @return the value of volume
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Set the value of volume
     *
     * @param volume new value of volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     * Get the value of serie
     *
     * @return the value of serie
     */
    public String getSerie() {
        return serie;
    }

    /**
     * Set the value of serie
     *
     * @param serie new value of serie
     */
    public void setSerie(String serie) {
        this.serie = serie;
    }

    /**
     * Get the value of isbn
     *
     * @return the value of isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the value of isbn
     *
     * @param isbn new value of isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Get the value of issn
     *
     * @return the value of issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     * Set the value of issn
     *
     * @param issn new value of issn
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    @Override
    public String toString() {
        return "Numbering{" + "issn=" + issn + ", isbn=" + isbn + ", serie=" + serie + ", volume=" + volume + '}';
    }

}
