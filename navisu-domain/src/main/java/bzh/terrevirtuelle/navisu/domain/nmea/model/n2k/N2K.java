/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.n2k;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Serge
 */
@XmlTransient
public class N2K
        extends NMEA {

    private String description;
    private String timeStamp;
    private int priority;
    private int dst;
    private int pgn;

    public N2K() {
    }


    public N2K(String sentence, String timeStamp, int priority, String src, int dst, int pgn, String description ) {
        super(src, sentence);
        this.description = description;
        this.timeStamp = timeStamp;
        this.priority = priority;
        this.dst = dst;
        this.pgn = pgn;
    }

    /**
     * Get the value of pgn
     *
     * @return the value of pgn
     */
    public int getPgn() {
        return pgn;
    }

    /**
     * Set the value of pgn
     *
     * @param pgn new value of pgn
     */
    public void setPgn(int pgn) {
        this.pgn = pgn;
    }

    /**
     * Get the value of dst
     *
     * @return the value of dst
     */
    public int getDst() {
        return dst;
    }

    /**
     * Set the value of dst
     *
     * @param dst new value of dst
     */
    public void setDst(int dst) {
        this.dst = dst;
    }

    /**
     * Get the value of priority
     *
     * @return the value of priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Set the value of priority
     *
     * @param priority new value of priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Get the value of timeStamp
     *
     * @return the value of timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Set the value of timeStamp
     *
     * @param timeStamp new value of timeStamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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

}
