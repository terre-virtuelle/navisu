package bzh.terrevirtuelle.navisu.nmea.model;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Serge
 */
@XmlRootElement(name = "PGN126992")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN126992
        extends N2K {

    private Calendar date;

    public PGN126992() {
    }

    public PGN126992(Calendar date, String sid, String description) {
        super(sid, description);
        this.date = date;
    }

    

    /**
     * Get the value of date
     *
     * @return the value of date
     */
    public Calendar getDate() {
        return date;
    }

    /**
     * Set the value of date
     *
     * @param date new value of date
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SystemTime{" + "date=" + date + '}';
    }

}
