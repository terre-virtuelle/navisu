/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *BWR Bearing and Distance to Waypoint – Rhumb Line Latitude
 * @author Serge Morvan
 */
@XmlRootElement(name="BWR")
@XmlAccessorType(XmlAccessType.FIELD)
public class BWR
        extends BWC {

    public BWR(String device, 
            String sentence,
            Calendar date, 
            float latitude, 
            float longitude, 
            float bearingDegreesTrue, 
            float bearingDegreesMagnetic, 
            float distanceToWayPoint, 
            String unitsOfDistanceToWayPoint, 
            String waypointID) {
        super(device, sentence, date, latitude, longitude, bearingDegreesTrue, bearingDegreesMagnetic, distanceToWayPoint, unitsOfDistanceToWayPoint, waypointID);
    }

    public BWR() {
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss"); 
        return "BWR{" +  "UTC=" + formater.format(getUtc().getTime()) 
                + ", latitude=" + getLatitude() 
                + ", longitude=" + getLongitude() 
                + ", bearingDegreesTrue=" + getBearingDegreesTrue() 
                + ", bearingDegreesMagnetic=" + getBearingDegreesMagnetic() 
                + ", distanceToWayPoint=" + getDistanceToWayPoint() 
                + ", unitsOfDistanceToWayPoint=" + getUnitsOfDistanceToWayPoint() 
                + ", waypointID=" + getWaypointID() + '}';
    }
    
}
