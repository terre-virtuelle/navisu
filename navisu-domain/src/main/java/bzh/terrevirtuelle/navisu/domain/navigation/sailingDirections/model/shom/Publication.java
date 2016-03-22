/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 20, 2016
 *
 */
@XmlRootElement(name = "publication")
@XmlAccessorType(XmlAccessType.FIELD)
public class Publication {

    @XmlElement(name = "titres")
    private Titles titles;
    @XmlElement(name = "maj")
    private Update update;
    @XmlElement(name = "numerotation")
    private Numbering numbering;
    @XmlElement(name = "ouvrage")
    private Book book;
    @XmlElement(name = "copyright")
    private Copyright copyright;

    public Publication() {
    }

    public Publication(Titles titles, Update update, Numbering numbering, Book book, Copyright copyright) {
        this.titles = titles;
        this.update = update;
        this.numbering = numbering;
        this.book = book;
        this.copyright = copyright;
    }

    /**
     * Get the value of copyright
     *
     * @return the value of copyright
     */
    public Copyright getCopyright() {
        return copyright;
    }

    /**
     * Set the value of copyright
     *
     * @param copyright new value of copyright
     */
    public void setCopyright(Copyright copyright) {
        this.copyright = copyright;
    }

    /**
     * Get the value of book
     *
     * @return the value of book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set the value of book
     *
     * @param book new value of book
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get the value of numbering
     *
     * @return the value of numbering
     */
    public Numbering getNumbering() {
        return numbering;
    }

    /**
     * Set the value of numbering
     *
     * @param numbering new value of numbering
     */
    public void setNumbering(Numbering numbering) {
        this.numbering = numbering;
    }

    /**
     * Get the value of update
     *
     * @return the value of update
     */
    public Update getUpdate() {
        return update;
    }

    /**
     * Set the value of update
     *
     * @param update new value of update
     */
    public void setUpdate(Update update) {
        this.update = update;
    }

    /**
     * Get the value of titles
     *
     * @return the value of titles
     */
    public Titles getTitles() {
        return titles;
    }

    /**
     * Set the value of titles
     *
     * @param titles new value of titles
     */
    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public String getStitle() {
        return titles.getStitle();
    }

    public String getCountry() {
        return titles.getCountry();
    }

    public String getRegion() {
        return titles.getRegion();
    }

    @Override
    public String toString() {
        return "Publication{" + "titles=" + titles + ", update=" + update + ", numbering=" + numbering + ", book=" + book + ", copyright=" + copyright + '}';
    }

}
