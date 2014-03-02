/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Serge
 */
@XmlTransient
public class N2K
        extends NMEA {

    private int sid;
    private String description = "";
    private Calendar timeStamp;

    /**
     * Get the value of timeStamp
     *
     * @return the value of timeStamp
     */
    public Calendar getTimeStamp() {
        return timeStamp;
    }

    /**
     * Set the value of timeStamp
     *
     * @param timeStamp new value of timeStamp
     */
    public void setTimeStamp(Calendar timeStamp) {
        this.timeStamp = timeStamp;
    }

    public N2K() {
    }

    public N2K(String description) {
        this.description = description;
    }

    public N2K(int sid, String description) {
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
    public int getSid() {
        return sid;
    }

    /**
     * Set the value of sid
     *
     * @param sid new value of sid
     */
    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "N2K{" + "sid=" + sid + ", description=" + description + '}';
    }

    
}
