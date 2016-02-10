/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 27 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "channel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Channel {

    private String title;
    private String link;
    private String language;
    private String copyright;
    private String pubDate;
    private String lastBuildDate;
    private String description;
    private Image image;
    @XmlElements({
        @XmlElement(name = "item", type = Item.class)
    })
    private List<Item> items;

    public Channel() {
        items = new ArrayList<>();
    }

    public Channel(String title, String link, String language, String copyright, String pubDate, String lastBuildDate, String description, Image image, List<Item> items) {
        this.title = title;
        this.link = link;
        this.language = language;
        this.copyright = copyright;
        this.pubDate = pubDate;
        this.lastBuildDate = lastBuildDate;
        this.description = description;
        this.image = image;
        this.items = items;
    }

    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(Image image) {
        this.image = image;
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
     * Get the value of lastBuildDate
     *
     * @return the value of lastBuildDate
     */
    public String getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * Set the value of lastBuildDate
     *
     * @param lastBuildDate new value of lastBuildDate
     */
    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
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

    /**
     * Get the value of language
     *
     * @return the value of language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the value of language
     *
     * @param language new value of language
     */
    public void setLanguage(String language) {
        this.language = language;
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
        return "Channel{" + "title=" + title + ", link=" + link + ", language=" + language + ", copyright=" + copyright + ", pubDate=" + pubDate + ", lastBuildDate=" + lastBuildDate + ", description=" + description + ", image=" + image + ", items=" + items + '}';
    }

}
