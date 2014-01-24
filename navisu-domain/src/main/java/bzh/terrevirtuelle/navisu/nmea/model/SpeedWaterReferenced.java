/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge
 */
@XmlRootElement(name = "SpeedWaterReferenced")
@XmlAccessorType(XmlAccessType.FIELD)
public class SpeedWaterReferenced
        extends N2K {

    private float speedWaterReferenced;
    private float speedGroundReferenced;
    private int speedWaterReferencedTYpe;

    public SpeedWaterReferenced() {
    }

    public SpeedWaterReferenced(int pgn, String source, String sid) {
        super(pgn, source, sid);
    }

    public SpeedWaterReferenced(int pgn, String source, String sid, float speedWaterReferenced, float speedGroundReferenced, int speedWaterReferencedTYpe) {
        super(pgn, source, sid);
        this.speedWaterReferenced = speedWaterReferenced;
        this.speedGroundReferenced = speedGroundReferenced;
        this.speedWaterReferencedTYpe = speedWaterReferencedTYpe;
    }

    /**
     * Get the value of speedWaterReferencedTYpe
     *
     * @return the value of speedWaterReferencedTYpe
     */
    public int getSpeedWaterReferencedTYpe() {
        return speedWaterReferencedTYpe;
    }

    /**
     * Set the value of speedWaterReferencedTYpe
     *
     * @param speedWaterReferencedTYpe new value of speedWaterReferencedTYpe
     */
    public void setSpeedWaterReferencedTYpe(int speedWaterReferencedTYpe) {
        this.speedWaterReferencedTYpe = speedWaterReferencedTYpe;
    }

    /**
     * Get the value of speedGroundReferenced
     *
     * @return the value of speedGroundReferenced
     */
    public float getSpeedGroundReferenced() {
        return speedGroundReferenced;
    }

    /**
     * Set the value of speedGroundReferenced
     *
     * @param speedGroundReferenced new value of speedGroundReferenced
     */
    public void setSpeedGroundReferenced(float speedGroundReferenced) {
        this.speedGroundReferenced = speedGroundReferenced;
    }

    /**
     * Get the value of speedWaterReferenced
     *
     * @return the value of speedWaterReferenced
     */
    public float getSpeedWaterReferenced() {
        return speedWaterReferenced;
    }

    /**
     * Set the value of speedWaterReferenced
     *
     * @param speedWaterReferenced new value of speedWaterReferenced
     */
    public void setSpeedWaterReferenced(float speedWaterReferenced) {
        this.speedWaterReferenced = speedWaterReferenced;
    }

}
