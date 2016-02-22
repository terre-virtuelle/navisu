/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date   Feb 19, 2016
 *
 */
@XmlRootElement(name = "tableau")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tabular {

    private String numero;

    private Txt txt;

    public Tabular() {
    }

    public Tabular(String numero, Txt txt) {
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

    @Override
    public String toString() {
        return "Tableau{" + "numero=" + numero + ", txt=" + txt + '}';
    }

}
