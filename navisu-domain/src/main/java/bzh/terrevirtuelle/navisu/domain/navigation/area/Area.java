/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.area;

import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.*;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "area", propOrder = {"id", "wkt", "book", "zoneName", "description"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Area
        implements NavigationData {

    private long id;
    private String wkt;
    private String book;
    private String zoneName;
    private String description;

    public Area() {
    }

    public Area(long id, String wkt, String book, String zoneName, String description) {
        this.id = id;
        this.wkt = wkt;
        this.book = book;
        this.zoneName = zoneName;
        this.description = description;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of zoneName
     *
     * @return the value of zoneName
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Set the value of zoneName
     *
     * @param zoneName new value of zoneName
     */
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    /**
     * Get the value of book
     *
     * @return the value of book
     */
    public String getBook() {
        return book;
    }

    /**
     * Set the value of book
     *
     * @param book new value of book
     */
    public void setBook(String book) {
        this.book = book;
    }

    /**
     * Get the value of wkt
     *
     * @return the value of wkt
     */
    public String getWkt() {
        return wkt;
    }

    /**
     * Set the value of wkt
     *
     * @param wkt new value of wkt
     */
    public void setWkt(String wkt) {
        this.wkt = wkt;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Area{" + "id=" + id + ", wkt=" + wkt + ", book=" + book + ", zoneName=" + zoneName + ", description=" + description + '}';
    }

}
