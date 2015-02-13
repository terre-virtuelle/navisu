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
 * BEC Bearing & Distance to Waypoint – Dead Reckoning
 *
 * @author Serge Morvan
 */
@XmlRootElement(name="BEC")
@XmlAccessorType(XmlAccessType.FIELD)
public class BEC
        extends BWC {

    public BEC(String device,
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

    public BEC() {
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
        return "BEC{" + "UTC=" + formater.format(getUtc().getTime())
                + ", latitude=" + getLatitude()
                + ", longitude=" + getLongitude()
                + ", bearingDegreesTrue=" + getBearingDegreesTrue()
                + ", bearingDegreesMagnetic=" + getBearingDegreesMagnetic()
                + ", distanceToWayPoint=" + getDistanceToWayPoint()
                + ", unitsOfDistanceToWayPoint=" + getUnitsOfDistanceToWayPoint()
                + ", waypointID=" + getWaypointID() + '}';
    }
}
