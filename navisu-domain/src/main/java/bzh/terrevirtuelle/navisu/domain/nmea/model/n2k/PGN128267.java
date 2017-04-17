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
@XmlRootElement(name = "pgn128267")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN128267
        extends N2K {

    private float waterDepth;
    private float offset;
    private int sid;

    public PGN128267() {
    }

    public PGN128267(String sentence, String timeStamp,
            int priority, String src, int dst,
            int pgn, String description,
            int sid, float waterDepth, float offset) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.sid = sid;
        this.waterDepth = waterDepth;
        this.offset = offset;
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

    /**
     * Get the value of offset
     *
     * @return the value of offset
     */
    public float getOffset() {
        return offset;
    }

    /**
     * Set the value of offset
     *
     * @param offset new value of offset
     */
    public void setOffset(float offset) {
        this.offset = offset;
    }

    /**
     * Get the value of waterDepth
     *
     * @return the value of waterDepth
     */
    public float getWaterDepth() {
        return waterDepth;
    }

    /**
     * Set the value of waterDepth
     *
     * @param waterDepth new value of waterDepth
     */
    public void setWaterDepth(float waterDepth) {
        this.waterDepth = waterDepth;
    }

    @Override
    public String toString() {
        return "PGN128267{" + "waterDepth=" + waterDepth + ", offset=" + offset + ", sid=" + sid + '}';
    }

}
