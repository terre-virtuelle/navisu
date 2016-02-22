/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "para")
@XmlAccessorType(XmlAccessType.FIELD)
public class Para {

    private Title titre;

    @XmlElements({
        @XmlElement(type = Alinea.class)
    })
    private final List<Alinea> alinea = new ArrayList<>();
    @XmlElements({
        @XmlElement(type = SubParagrah.class)
    })
    private final List<SubParagrah> sPara = new ArrayList<>();

    public Para() {
    }

    public List<Alinea> getAlinea() {
        return alinea;
    }

    public List<SubParagrah> getSpara() {
        return sPara;
    }

    /**
     * Get the value of alineaList
     *
     * @return the value of alineaList
     */
    

    

    /**
     * Get the value of titre
     *
     * @return the value of titre
     */
    public Title getTitre() {
        return titre;
    }

    /**
     * Set the value of titre
     *
     * @param titre new value of titre
     */
    public void setTitre(Title titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Para{" + "titre=" + titre + ", alinea=" + alinea + ", sPara=" + sPara + '}';
    }

    
   

}
