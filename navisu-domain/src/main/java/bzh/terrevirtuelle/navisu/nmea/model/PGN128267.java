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
@XmlRootElement(name = "PGN128267")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN128267
        extends N2K {

    private float waterDepth;
    private float offset;

    public PGN128267() {
    }

    public PGN128267(float waterDepth, float offset, String sid, String description) {
        super(sid, description);
        this.waterDepth = waterDepth;
        this.offset = offset;
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

}
