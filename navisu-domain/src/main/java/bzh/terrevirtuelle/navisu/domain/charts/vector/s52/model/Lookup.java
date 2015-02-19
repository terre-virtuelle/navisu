/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.charts.vector.s52.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Serge Morvan
 * @date 11 juil. 2014 NaVisu project
 */
@XmlType(name = "lookup")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lookup {

    @XmlAttribute
    private String id;
    @XmlAttribute(name = "RCID")
    private String rcid;
    @XmlAttribute
    private String name;
    private String type;
    @XmlElement(name = "disp-prio")
    private String dispPrio;
    @XmlElement(name = "radar-prio")
    private String radarPrio;
    @XmlElement(name = "table-name")
    private String tableName;
    private AttribCode attribCode;
    private String instruction;
    @XmlElement(name = "display-cat")
    private String displayCat;
    private String comment;

    public Lookup() {
    }

    public Lookup(String id, String rcid, String name, String type, String dispPrio, String radarPrio, String tableName, AttribCode attribCode, String instruction, String displayCat, String comment) {
        this.id = id;
        this.rcid = rcid;
        this.name = name;
        this.type = type;
        this.dispPrio = dispPrio;
        this.radarPrio = radarPrio;
        this.tableName = tableName;
        this.attribCode = attribCode;
        this.instruction = instruction;
        this.displayCat = displayCat;
        this.comment = comment;
    }

    public Lookup(String id, String rcid, String name, String type, String dispPrio, String radarPrio, String tableName, String instruction, String displayCat, String comment) {
        this.id = id;
        this.rcid = rcid;
        this.name = name;
        this.type = type;
        this.dispPrio = dispPrio;
        this.radarPrio = radarPrio;
        this.tableName = tableName;
        this.instruction = instruction;
        this.displayCat = displayCat;
        this.comment = comment;
    }

     /**
     * Get the value of attribCode
     *
     * @return the value of attribCode
     */
    public AttribCode getAttribCode() {
        return attribCode;
    }

    /**
     * Set the value of attribCode
     *
     * @param attribCode new value of attribCode
     */
    public void setAttribCode(AttribCode attribCode) {
        this.attribCode = attribCode;
    }

    
    /**
     * Get the value of comment
     *
     * @return the value of comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Set the value of comment
     *
     * @param comment new value of comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Get the value of displayCat
     *
     * @return the value of displayCat
     */
    public String getDisplayCat() {
        return displayCat;
    }

    /**
     * Set the value of displayCat
     *
     * @param displayCat new value of displayCat
     */
    public void setDisplayCat(String displayCat) {
        this.displayCat = displayCat;
    }

    /**
     * Get the value of instruction
     *
     * @return the value of instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Set the value of instruction
     *
     * @param instruction new value of instruction
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Get the value of tableName
     *
     * @return the value of tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Set the value of tableName
     *
     * @param tableName new value of tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the value of radarPrio
     *
     * @return the value of radarPrio
     */
    public String getRadarPrio() {
        return radarPrio;
    }

    /**
     * Set the value of radarPrio
     *
     * @param radarPrio new value of radarPrio
     */
    public void setRadarPrio(String radarPrio) {
        this.radarPrio = radarPrio;
    }

    /**
     * Get the value of dispPrio
     *
     * @return the value of dispPrio
     */
    public String getDispPrio() {
        return dispPrio;
    }

    /**
     * Set the value of dispPrio
     *
     * @param dispPrio new value of dispPrio
     */
    public void setDispPrio(String dispPrio) {
        this.dispPrio = dispPrio;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
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

    /**
     * Get the value of rcid
     *
     * @return the value of rcid
     */
    public String getRcid() {
        return rcid;
    }

    /**
     * Set the value of rcid
     *
     * @param rcid new value of rcid
     */
    public void setRcid(String rcid) {
        this.rcid = rcid;
    }

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

    @Override
    public String toString() {
        return "Lookup{" + "id=" + id + ", rcid=" + rcid + ", name=" + name + ", type=" + type + ", dispPrio=" + dispPrio + ", radarPrio=" + radarPrio + ", tableName=" + tableName + ", instruction=" + instruction + ", displayCat=" + displayCat + ", comment=" + comment + '}';
    }

}
