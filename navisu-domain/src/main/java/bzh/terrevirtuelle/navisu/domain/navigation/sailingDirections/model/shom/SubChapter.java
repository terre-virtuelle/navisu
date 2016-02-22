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
@XmlRootElement(name = "schapitre")
@XmlAccessorType(XmlAccessType.FIELD)
public class SubChapter {

    private Title titre;
    @XmlElements({
        @XmlElement(type = Alinea.class)
    })
    private List<Alinea> alinea = new ArrayList<>();

    @XmlElements({
        @XmlElement(type = Para.class)
    })
    private List<Para> para = new ArrayList<>();

    public SubChapter() {
    }

    public List<Para> getPara() {
        return para;
    }

    /**
     * Get the value of paraList
     *
     * @return the value of paraList
     */
    

    public List<Alinea> getAlinea() {
        return alinea;
    }

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
        return "SChapitre{" + "titre=" + titre + ", alinea=" + alinea + ", para=" + para + '}';
    }

    
    
}
