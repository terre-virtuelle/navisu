/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlType(name = "attrib-code")
@XmlAccessorType(XmlAccessType.FIELD)
public class AttribCode {

    @XmlAttribute
    private String index;

    public AttribCode() {
    }

    public AttribCode(String index) {
        this.index = index;
    }

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public String getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "AttribCode{" + "index=" + index + '}';
    }

}
