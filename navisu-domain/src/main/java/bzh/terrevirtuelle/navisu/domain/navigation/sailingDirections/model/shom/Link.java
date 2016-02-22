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
@XmlRootElement(name = "lien")
@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements TextPart {

    @XmlAttribute
    private String tlien;

    private String numero;

    public Link() {
    }

    public Link(String tlien, String numero) {
        this.tlien = tlien;
        this.numero = numero;
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
     * Get the value of tlien
     *
     * @return the value of tlien
     */
    public String getTlien() {
        return tlien;
    }

    /**
     * Set the value of tlien
     *
     * @param tlien new value of tlien
     */
    public void setTlien(String tlien) {
        this.tlien = tlien;
    }

    @Override
    public boolean contains(CharSequence str) {
        return numero.contains(str);
    }

    @Override
    public String shorten() {
        return "";
    }

    @Override
    public String toString() {
        return "Lien{" + "tlien=" + tlien + ", numero=" + numero + '}';
    }

}
