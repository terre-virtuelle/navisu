/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Feb 19, 2016
 *
 */
@XmlRootElement(name = "metaData")
@XmlAccessorType(XmlAccessType.FIELD)
public class Metadata {

    @XmlAttribute(name = "xmlns:xsi")
    private String xmlns;

    @XmlElement(name = "auteur")
    private Author author;
    @XmlElement(name = "editeur")
    private Editor editor;
    @XmlElement(name = "imprimeur")
    private Publisher publisher;
    @XmlElement(name = "publication")
    private Publication publication;
    @XmlElement(name = "copyright")
    private Copyright copyright;

    public Metadata() {
    }

    public Metadata(Author author, Editor editor, Publisher publisher, Publication publication, Copyright copyright) {
        this.author = author;
        this.editor = editor;
        this.publisher = publisher;
        this.publication = publication;
        this.copyright = copyright;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
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
     * Get the value of publication
     *
     * @return the value of publication
     */
    public Publication getPublication() {
        return publication;
    }

    /**
     * Set the value of publication
     *
     * @param publication new value of publication
     */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /**
     * Get the value of publisher
     *
     * @return the value of publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Set the value of publisher
     *
     * @param publisher new value of publisher
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Get the value of editor
     *
     * @return the value of editor
     */
    public Editor getEditor() {
        return editor;
    }

    /**
     * Set the value of editor
     *
     * @param editor new value of editor
     */
    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    /**
     * Get the value of author
     *
     * @return the value of author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Set the value of author
     *
     * @param author new value of author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Metadata{" + "author=" + author + ", editor=" + editor + ", publisher=" + publisher + ", publication=" + publication + ", copyright=" + copyright + '}';
    }

}
