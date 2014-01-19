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
@XmlRootElement(name = "SystemTime")
@XmlAccessorType(XmlAccessType.FIELD)
public class SystemTime
        extends N2K {

    private Calendar date;

    public SystemTime() {
    }

    public SystemTime(int pgn, String source, Calendar date) {
        super(pgn, source);
        this.date = date;
    }

    public SystemTime(int pgn, String source, String sid, Calendar date) {
        super(pgn, source, sid);
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
