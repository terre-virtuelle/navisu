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
@XmlType(name = "sparql")
@XmlAccessorType(XmlAccessType.FIELD)
public class Sparql {

    @XmlAttribute
    private String name;
    private Head head;
    private Results results;

    public Sparql() {
        head = new Head();
        results = new Results();
    }

    public Sparql(String xmlns, Head head, Results results) {
        this.name = xmlns;
        this.head = head;
        this.results = results;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    /*
     * Get the value of head
     *
     * @return the value of head
     */
    public Head getHead() {
        return head;
    }

    /**
     * Set the value of head
     *
     * @param head new value of head
     */
    public void setHead(Head head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sparql{" + "name=" + name + ", head=" + head + ", results=" + results + '}';
    }

}
