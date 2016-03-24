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
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "titre")
@XmlAccessorType(XmlAccessType.FIELD)
public class Title {

    private int nmrAlinea;

    @XmlAttribute
    private String tDate;
    @XmlAttribute(name = "tTitre")
    private String ttitle;
    @XmlAttribute
    private String tMaj;
    private Text texte;
    private String numero;
    @XmlElement(name = "sTitre")
    private String stitle;
    @XmlElement(name = "pays")
    private String country;
    @XmlElement(name = "region")
    private String region;

    public Title() {
    }

    public Title(int nmrAlinea, String tDate, String tMaj, Text texte, String numero, Illustration illustration) {
        this.nmrAlinea = nmrAlinea;
        this.tDate = tDate;
        this.tMaj = tMaj;
        this.texte = texte;
        this.numero = numero;
        this.illustration = illustration;
    }

    public Title(String stitle, String country, String region) {
        this.stitle = stitle;
        this.country = country;
        this.region = region;
    }

    public String getTtitle() {
        return ttitle;
    }

    public void setTtitle(String ttitle) {
        this.ttitle = ttitle;
    }

    /**
     * Get the value of numero
     *
     * @return the value of numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Set the value of numero
     *
     * @param numero new value of numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Get the value of tMaj
     *
     * @return the value of tMaj
     */
    public String gettMaj() {
        return tMaj;
    }
    private Illustration illustration;

    /**
     * Get the value of illustration
     *
     * @return the value of illustration
     */
    public Illustration getIllustration() {
        return illustration;
    }

    /**
     * Set the value of illustration
     *
     * @param illustration new value of illustration
     */
    public void setIllustration(Illustration illustration) {
        this.illustration = illustration;
    }

    /**
     * Set the value of tMaj
     *
     * @param tMaj new value of tMaj
     */
    public void settMaj(String tMaj) {
        this.tMaj = tMaj;
    }

    /**
     * Get the value of tDate
     *
     * @return the value of tDate
     */
    public String gettDate() {
        return tDate;
    }

    /**
     * Set the value of tDate
     *
     * @param tDate new value of tDate
     */
    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    /**
     * Get the value of texte
     *
     * @return the value of texte
     */
    public Text getTexte() {
        return texte;
    }

    /**
     * Set the value of texte
     *
     * @param texte new value of texte
     */
    public void setTexte(Text texte) {
        this.texte = texte;
    }

    /**
     * Get the value of nmrAlinea
     *
     * @return the value of nmrAlinea
     */
    public int getNmrAlinea() {
        return nmrAlinea;
    }

    /**
     * Set the value of nmrAlinea
     *
     * @param nmrAlinea new value of nmrAlinea
     */
    public void setNmrAlinea(int nmrAlinea) {
        this.nmrAlinea = nmrAlinea;
    }

    public String getStitle() {
        return stitle;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Title{"
                + nmrAlinea != null ? "nmrAlinea=" + nmrAlinea : ""
                        + tDate != null ? ", tDate=" + tDate : ""
                                + ttitle != null ? ", ttitle=" + ttitle : ""
                                        + tMaj != null ? ", tMaj=" + tMaj : ""
                                                + texte != null ? ", texte=" + texte : ""
                                                        + numero != null ? ", numero=" + numero : ""
                                                                + stitle != null ? ", stitle=" + stitle : ""
                                                                        + country != null ? ", country=" + country : ""
                                                                                + region != null ? ", region=" + region : ""
                                                                                        + illustration != null ? ", illustration=" + illustration : ""
                                                                                                + '}';
    }

}
