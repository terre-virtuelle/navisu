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

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "alinea")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alinea {

    private int nmrAlinea;

    @XmlAttribute
    private String tDate;
    @XmlAttribute
    private String tMaj;
    private Text texte;
    private TextList liste;
    private Tabular tableau;

    public Alinea() {
    }

    public Alinea(int nmrAlinea, String tDate, String tMaj, Text texte, TextList liste, Tabular tableau, Illustration illustration) {
        this.nmrAlinea = nmrAlinea;
        this.tDate = tDate;
        this.tMaj = tMaj;
        this.texte = texte;
        this.liste = liste;
        this.tableau = tableau;
        this.illustration = illustration;
    }

    /**
     * Get the value of tMaj
     *
     * @return the value of tMaj
     */
    public String gettMaj() {
        return tMaj;
    }

    /**
     * Get the value of tableau
     *
     * @return the value of tableau
     */
    public Tabular getTableau() {
        return tableau;
    }

    /**
     * Set the value of tableau
     *
     * @param tableau new value of tableau
     */
    public void setTableau(Tabular tableau) {
        this.tableau = tableau;
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

    public TextList getListe() {
        return liste;
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

    @Override
    public String toString() {
        return "Alinea{" + "nmrAlinea=" + nmrAlinea + ", tDate=" + tDate + ", tMaj=" + tMaj + ", texte=" + texte + ", liste=" + liste + ", tableau=" + tableau + ", illustration=" + illustration + '}';
    }

   

}
