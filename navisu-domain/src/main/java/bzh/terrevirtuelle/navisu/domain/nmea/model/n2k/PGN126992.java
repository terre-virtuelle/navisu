package bzh.terrevirtuelle.navisu.domain.nmea.model.n2k;

import java.text.SimpleDateFormat;
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
@XmlRootElement(name = "pgn126992")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN126992
        extends N2K {

    private Calendar date;
    private String source;

    public PGN126992() {
    }

    public PGN126992(String sentence, String timeStamp,
            int priority, String src, int dst,
            int pgn, String description,
            String source, Calendar date) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.source = source;
        this.date = date;
    }

    public PGN126992(String sentence, String timeStamp,
            int priority, String src, int dst,
            int pgn, String description,
            String source, String date) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.source = source;
        //  this.date = date;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setCalendar(date);
        String sDate = dateFormat.format(date.getTime());
        return "PGN126992{" + "date=" + sDate + ", source=" + source + '}';
    }

}
