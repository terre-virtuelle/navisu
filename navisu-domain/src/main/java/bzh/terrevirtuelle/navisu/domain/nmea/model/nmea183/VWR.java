/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Relative Wind Speed and Angle 
 * @author Serge Morvan
 */
@XmlRootElement(name="VWR")
@XmlAccessorType(XmlAccessType.FIELD)
public class VWR
        extends NMEA {
    
    private int windDirectionMagnitude;
    private String windDirectionOfBow;
    private float speed; // In knots

    public VWR(String device, 
            String sentence,
            int windDirectionMagnitude, 
            String windDirectionOfBow, 
            float speed ) {
        super(device, sentence);
        this.windDirectionMagnitude = windDirectionMagnitude;
        this.windDirectionOfBow = windDirectionOfBow;
        this.speed = speed;
    }

    public VWR() {
    }

    /**
     * Get the value of speed
     *
     * @return the value of speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Set the value of speed
     *
     * @param speed new value of speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Get the value of windDirectionOfBow
     *
     * @return the value of windDirectionOfBow
     */
    public String getWindDirectionOfBow() {
        return windDirectionOfBow;
    }

    /**
     * Set the value of windDirectionOfBow
     *
     * @param windDirectionOfBow new value of windDirectionOfBow
     */
    public void setWindDirectionOfBow(String windDirectionOfBow) {
        this.windDirectionOfBow = windDirectionOfBow;
    }

    /**
     * Get the value of windDirectionMagnitude
     *
     * @return the value of windDirectionMagnitude
     */
    public int getWindDirectionMagnitude() {
        return windDirectionMagnitude;
    }

    /**
     * Set the value of windDirectionMagnitude
     *
     * @param windDirectionMagnitude new value of windDirectionMagnitude
     */
    public void setWindDirectionMagnitude(int windDirectionMagnitude) {
        this.windDirectionMagnitude = windDirectionMagnitude;
    }

    @Override
    public String toString() {
        return "VWR{" + "windDirectionMagnitude=" + windDirectionMagnitude + ", windDirectionOfBow=" + windDirectionOfBow + ", speed=" + speed + '}';
    }

   

}
