/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.n2k;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Serge
 *
 * Wind Data Direction and speed of Wind. True wind can be referenced to the
 * vessel or to the ground. The Apparent Wind is what is felt standing on the
 * (moving) ship, I.e., the wind measured by the typical mast head instruments.
 * The boat referenced true wind is given by the vector sum of Apparent wind and
 * vessel's heading and speed though the water. The ground referenced true wind
 * is given by the vector sum of Apparent wind and vessel's heading and speed
 * over ground.
 */
@XmlRootElement(name = "pgn130306")
@XmlAccessorType(XmlAccessType.FIELD)
public class PGN130306
        extends N2K {

    private double windSpeed;
    private double windDirection;
    private String windReference;

    public PGN130306() {
    }

    public PGN130306(String sentence, String timeStamp,
            int priority, String src, int dst, 
            int pgn, String description,
            double windSpeed, double windDirection, String windReference) {
        super(sentence, timeStamp, priority, src, dst, pgn, description);
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.windReference = windReference;
    }


    /**
     * Get the value of windReference
     *
     * @return the value of windReference
     */
    public String getWindReference() {
        return windReference;
    }

    /**
     * Set the value of windReference
     *
     * @param windReference new value of windReference
     */
    public void setWindReference(String windReference) {
        this.windReference = windReference;
    }

    /**
     * Get the value of windDirection
     *
     * @return the value of windDirection
     */
    public double getWindDirection() {
        return windDirection;
    }

    /**
     * Set the value of windDirection
     *
     * @param windDirection new value of windDirection
     */
    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Get the value of windDirection
     *
     * @return the value of windDirection
     */
    public double getWindAngle() {
        return windDirection;
    }

    /**
     * Set the value of windDirection
     *
     * @param windDirection new value of windDirection
     */
    public void setWindAngle(double windDirection) {
        this.windDirection = windDirection;
    }

    /**
     * Get the value of windSpeed
     *
     * @return the value of windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * Set the value of windSpeed
     *
     * @param windSpeed new value of windSpeed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    @Override
    public String toString() {
        return "PGN130306{" + "windSpeed=" + windSpeed + ", windDirection=" + windDirection + ", windReference=" + windReference + '}';
    }

}
