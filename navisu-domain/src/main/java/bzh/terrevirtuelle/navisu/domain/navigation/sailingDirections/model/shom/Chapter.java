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
@XmlRootElement(name = "chapitre")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chapter {

    @XmlElement(name = "titre")
    private Title titre;
    //Pour compatibilite avec travaux Julie Niveau 1
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
        @XmlElement(type = SubChapter.class)
    })
    private final List<SubChapter> sChapitre = new ArrayList<>();

    public Chapter() {
    }

    public List<SubChapter> getsChapitre() {
        return sChapitre;
    }

    /**
     * Get the value of sChapitreList
     *
     * @return the value of sChapitreList
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

    /*
    @Override
    public String toString() {
        return "Chapitre{"
                + titre != null ? "titre=" + titre : ""
                        + sChapitre != null ? ", sChapitre=" + sChapitre : ""
                                + '}';
    }
     */
    @Override
    public String toString() {
        return "Chapter{" + "titre=" + titre + ", sChapitre=" + sChapitre + '}';
    }

}
