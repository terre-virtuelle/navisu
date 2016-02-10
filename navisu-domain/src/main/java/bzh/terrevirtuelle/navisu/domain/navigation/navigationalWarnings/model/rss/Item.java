/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 27 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    private String title;

    private String link;

    private String description;

    private String pubDate;

    
    public Item() {
    }

    public Item(String title, String link, String description, String pubDate) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
    }

    /**
     * Get the value of pubDate
     *
     * @return the value of pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Set the value of pubDate
     *
     * @param pubDate new value of pubDate
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
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
     * Get the value of link
     *
     * @return the value of link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the value of link
     *
     * @param link new value of link
     */
    public void setLink(String link) {
        this.link = link;
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

    @Override
    public String toString() {
        return "Item{" + "title=" + title + ", link=" + link + ", description=" + description + ", pubDate=" + pubDate + '}';
    }

}
