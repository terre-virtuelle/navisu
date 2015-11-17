/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.rdf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "binding")
@XmlAccessorType(XmlAccessType.FIELD)
public class Binding {

    @XmlAttribute
    private String name;

    private String uri;
    private String literal;

    public Binding() {
    }

    public Binding(String name, String uri, String literal) {
        this.name = name;
        this.uri = uri;
        this.literal = literal;
    }

    

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    /**
     * Get the value of uri
     *
     * @return the value of uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * Set the value of uri
     *
     * @param uri new value of uri
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Binding{" + "name=" + name + ", uri=" + uri + ", literal=" + literal + '}';
    }

    

}
