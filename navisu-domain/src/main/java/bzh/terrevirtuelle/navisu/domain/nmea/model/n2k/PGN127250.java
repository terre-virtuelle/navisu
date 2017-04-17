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
@XmlRootElement(name = "pgn127250")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN127250
        extends N2K {

    private float headingSensorReading;
    private float deviation;
    private float variation;
    private int headingSensorReference;

    public PGN127250() {
    }

    public PGN127250(String sentence, String timeStamp,
            int priority, String src, int dst, 
            int pgn, String description,
            float headingSensorReading, float deviation, float variation, int headingSensorReference) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.headingSensorReading = headingSensorReading;
        this.deviation = deviation;
        this.variation = variation;
        this.headingSensorReference = headingSensorReference;
    }

    
    /**
     * Get the value of headingSensorReference
     *
     * @return the value of headingSensorReference
     */
    public int getHeadingSensorReference() {
        return headingSensorReference;
    }

    /**
     * Set the value of headingSensorReference
     *
     * @param headingSensorReference new value of headingSensorReference
     */
    public void setHeadingSensorReference(int headingSensorReference) {
        this.headingSensorReference = headingSensorReference;
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

    @Override
    public String toString() {
        return "VesselHeading{" + "headingSensorReading=" + headingSensorReading + ", deviation=" + deviation + ", variation=" + variation + ", headingSensorReference=" + headingSensorReference + '}';
    }

}
