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
@XmlRootElement(name = "VesselHeading")
@XmlAccessorType(XmlAccessType.FIELD)
public class VesselHeading
        extends N2K {

    private float headingSensorReading;
    private float deviation;

    private float variation;

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
     * Get the value of deviation
     *
     * @return the value of deviation
     */
    public float getDeviation() {
        return deviation;
    }

    /**
     * Set the value of deviation
     *
     * @param deviation new value of deviation
     */
    public void setDeviation(float deviation) {
        this.deviation = deviation;
    }

    public VesselHeading(int pgn, String source, int sid) {
        super(pgn, source, sid);
    }

    /**
     * Get the value of headingSensorReading
     *
     * @return the value of headingSensorReading
     */
    public float getHeadingSensorReading() {
        return headingSensorReading;
    }

    /**
     * Set the value of headingSensorReading
     *
     * @param headingSensorReading new value of headingSensorReading
     */
    public void setHeadingSensorReading(float headingSensorReading) {
        this.headingSensorReading = headingSensorReading;
    }

}
