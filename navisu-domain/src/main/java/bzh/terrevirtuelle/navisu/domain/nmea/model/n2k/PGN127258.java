/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.n2k;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge
 */
@XmlRootElement(name = "pgn127258")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN127258
        extends N2K {

    private int sid;

    private float variation;

    public PGN127258() {
    }

    public PGN127258(String sentence, String timeStamp,
            int priority, String src, int dst,
            int pgn, String description,
            int sid, float variation) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.sid = sid;
        this.variation = variation;
    }

    /**
     * Get the value of variation
     *
     * @return the value of variation
     */
    public float getVariation() {
        return variation;
    }

    /**
     * Set the value of variation
     *
     * @param variation new value of variation
     */
    public void setVariation(float variation) {
        this.variation = variation;
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
        return "PGN127258{" + "sid=" + sid + ", variation=" + variation + '}';
    }

}
