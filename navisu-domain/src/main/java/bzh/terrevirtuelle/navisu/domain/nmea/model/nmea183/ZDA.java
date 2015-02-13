/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Time & Date – UTC, Day, Month, Year and Local Time Zone
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="ZDA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZDA extends NMEA {

    private Calendar date;

    public ZDA(String device,
            String sentence,
            Calendar date) {
        super(device, sentence);
        this.date = date;
    }

    public ZDA() {
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/YY", Locale.FRENCH);
        simpleDateFormat.setLenient(false);
        return "ZDA{" + "date=" + simpleDateFormat.format(date.getTime()) + '}';
    }
}
