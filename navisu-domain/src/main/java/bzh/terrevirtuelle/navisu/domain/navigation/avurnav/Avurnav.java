/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.avurnav;

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
@XmlType(name = "avurnav",
        propOrder = {"id", "globalZone", "broadcastTime",
            "expirationDate", "description",
            "wkt", "restriction", "type"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Avurnav implements NavigationData {

    private long id;
    private String globalZone;
    private String description;
    private String expirationDate;
    private String broadcastTime;
    private String wkt;
    private String restriction;
    private String type;

    public Avurnav() {
    }

    public Avurnav(long id, String globalZone, String description, String expirationDate, String broadcastTime, String wkt, String restriction, String type) {
        this.id = id;
        this.globalZone = globalZone;
        this.description = description;
        this.expirationDate = expirationDate;
        this.broadcastTime = broadcastTime;
        this.wkt = wkt;
        this.restriction = restriction;
        this.type = type;
    }

    /**
     * Get the value of restriction
     *
     * @return the value of restriction
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * Set the value of restriction
     *
     * @param restriction new value of restriction
     */
    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    /**
     * Get the value of geometry
     *
     * @return the value of geometry
     */
    @Override
    public String getGeometry() {
        return wkt;
    }

    /**
     * Set the value of geometry
     *
     * @param geometry new value of geometry
     */
    public void setGeometry(String geometry) {
        this.wkt = geometry;
    }

    public String getWkt() {
        return wkt;
    }

    public void setWkt(String wkt) {
        this.wkt = wkt;
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
        return "Avurnav{" + "id=" + id + ", globalZone=" + globalZone + ", description=" + description + ", expirationDate=" + expirationDate + ", broadcastTime=" + broadcastTime + ", geometry=" + wkt + ", restriction=" + restriction + ", type=" + type + '}';
    }

    public String printf() {
        String tmp = "";
        if (id != 0) {
            tmp = "Avurnav : " + id + "\n"
                    + "GlobalZone : " + globalZone + "\n"
                    + "BroadcastTime : " + broadcastTime + "\n"
                    + "ExpirationDate : " + expirationDate + "\n"
                    + "Description : " + description + "\n\n";
            
        }
        return tmp;
    }
}
