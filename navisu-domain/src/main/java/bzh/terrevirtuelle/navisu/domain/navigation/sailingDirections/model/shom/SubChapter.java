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
    //Pour compatibilite avec travaux Julie Niveau 2
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "id")
    private String id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

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
