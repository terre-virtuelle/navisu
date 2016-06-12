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
@XmlRootElement(name = "ouvrage")
@XmlAccessorType(XmlAccessType.FIELD)
public class Book {

    @XmlElements({
        @XmlElement(type = Illustration.class)
    })
    private final List<Illustration> illustration = new ArrayList<>();
    @XmlElements({
        @XmlElement(type = Chapter.class)
    })
    private final List<Chapter> chapitre = new ArrayList<>();
    @XmlElement(name = "nomCourt")
    private String name;
    @XmlElement(name = "nomLong")
    private String longname;
    @XmlElement(name = "nota")
    private String nota;
    //Pour compatibilite avec travaux Julie Niveau 0
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

    public Book() {
    }

    public List<Chapter> getChapters() {
        return chapitre;
    }

    public String getName() {
        return name;
    }

    public String getLongname() {
        return longname;
    }

    public String getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Book{" + "illustration=" + illustration + ", chapitre=" + chapitre + ", name=" + name + ", longname=" + longname + ", nota=" + nota + '}';
    }

}
