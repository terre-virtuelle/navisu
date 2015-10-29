/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.photos.exif;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 29 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class Author {

    private String name;

    private String copyright;

    public Author() {
    }

    public Author(String artist, String copyright) {
        this.name = artist;
        this.copyright = copyright;
    }

    /**
     * Get the value of copyright
     *
     * @return the value of copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Set the value of copyright
     *
     * @param copyright new value of copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author{" + "name=" + name + ", copyright=" + copyright + '}';
    }

}
