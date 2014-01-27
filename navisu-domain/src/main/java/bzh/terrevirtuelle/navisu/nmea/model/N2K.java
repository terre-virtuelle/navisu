/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

/**
 *
 * @author Serge
 */
public class N2K
        extends NMEA {

    private String sid;
    private String description;

    public N2K() {
    }

    public N2K(String description) {
        this.description = description;
    }

    public N2K(String sid, String description) {
        this.sid = sid;
        this.description = description;
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
     * Get the value of sid
     *
     * @return the value of sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * Set the value of sid
     *
     * @param sid new value of sid
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "N2K{" + "sid=" + sid + ", description=" + description + '}';
    }

    
}
