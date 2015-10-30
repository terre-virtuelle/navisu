/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.avurnav;

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
@XmlType(name = "avurnav", propOrder = {"id", "globalZone", "broadcastTime", "expirationDate", "description"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Avurnav {

    private long id;

    private String globalZone;

    private String description;

    private String expirationDate;

    private String broadcastTime;

    public Avurnav() {
    }

    public Avurnav(long id, String globalZone, String description, String expirationDate, String broadcastTime) {
        this.id = id;
        this.globalZone = globalZone;
        this.description = description;
        this.expirationDate = expirationDate;
        this.broadcastTime = broadcastTime;
    }

    /**
     * Get the value of broadcastTime
     *
     * @return the value of broadcastTime
     */
    public String getBroadcastTime() {
        return broadcastTime;
    }

    /**
     * Set the value of broadcastTime
     *
     * @param broadcastTime new value of broadcastTime
     */
    public void setBroadcastTime(String broadcastTime) {
        this.broadcastTime = broadcastTime;
    }

    /**
     * Get the value of expirationDate
     *
     * @return the value of expirationDate
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Set the value of expirationDate
     *
     * @param expirationDate new value of expirationDate
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
     * Get the value of globalZone
     *
     * @return the value of globalZone
     */
    public String getGlobalZone() {
        return globalZone;
    }

    /**
     * Set the value of globalZone
     *
     * @param globalZone new value of globalZone
     */
    public void setGlobalZone(String globalZone) {
        this.globalZone = globalZone;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
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
        return "Avurnav{" + "id=" + id 
                + ", globalZone=" + globalZone 
                + ", description=" + description 
                + ", expirationDate=" + expirationDate 
                + ", broadcastTime=" + broadcastTime + '}';
    }

    public void print() {
        System.out.println("Avurnav " + id);
        System.out.println("  globalZone : " + globalZone);
        System.out.println("  broadcastTime : " + broadcastTime);
        System.out.println("  expirationDate : " + expirationDate);
        System.out.println("  description : " + description);
    }
}
