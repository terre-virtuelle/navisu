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
@XmlRootElement(name = "illustration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Illustration {

    @XmlAttribute
    private String tIllustration;
    @XmlAttribute
    private String orientation;

    private String numero;
    
    private Txt txt;

    public Illustration() {
    }

    public Illustration(String tIllustration, String orientation, String numero, Txt txt) {
        this.tIllustration = tIllustration;
        this.orientation = orientation;
        this.numero = numero;
        this.txt = txt;
    }

    /**
     * Get the value of txt
     *
     * @return the value of txt
     */
    public Txt getTxt() {
        return txt;
    }

    /**
     * Set the value of txt
     *
     * @param txt new value of txt
     */
    public void setTxt(Txt txt) {
        this.txt = txt;
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
     * Get the value of orientation
     *
     * @return the value of orientation
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * Set the value of orientation
     *
     * @param orientation new value of orientation
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * Get the value of tIllustration
     *
     * @return the value of tIllustration
     */
    public String gettIllustration() {
        return tIllustration;
    }

    /**
     * Set the value of tIllustration
     *
     * @param tIllustration new value of tIllustration
     */
    public void settIllustration(String tIllustration) {
        this.tIllustration = tIllustration;
    }

    @Override
    public String toString() {
        return "Illustration{" + "tIllustration=" + tIllustration + ", orientation=" + orientation + ", numero=" + numero + ", txt=" + txt + '}';
    }

}
