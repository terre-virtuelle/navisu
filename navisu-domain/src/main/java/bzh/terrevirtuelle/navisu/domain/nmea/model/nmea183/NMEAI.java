/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

/**
 *
 * @author Serge Morvan
 */
public interface NMEAI {

    /**
     *
     * @return
     */
    String getArrivalCircleEntered();

    /**
     *
     * @return
     */
    float getCircleRadius();

    /**
     *
     * @return
     */
    String getPerpendicularPassed();

    /**
     *
     * @return
     */
    String getWaypointName();

    /**
     *
     * @param arrivalCircleEntered
     */
    void setArrivalCircleEntered(String arrivalCircleEntered);

    /**
     *
     * @param circleRadius
     */
    void setCircleRadius(float circleRadius);

    /**
     *
     * @param perpendicularPassed
     */
    void setPerpendicularPassed(String perpendicularPassed);

    /**
     *
     * @param waypointName
     */
    void setWaypointName(String waypointName);

    /**
     *
     * @return
     */
    String toString();
    
}
