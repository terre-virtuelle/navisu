/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.nmea183;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Names of the waypoints used in an active route. 
 * @author Serge Morvan
 */
@XmlRootElement(name="RTE")
@XmlAccessorType(XmlAccessType.FIELD)
public class RTE
        extends NMEA {

    private int totalNumberOfsentence;
    private int sentenceNumber;
    private String type;
    private List<String> waypoints = new ArrayList<>();
    private String routeIndex = "";

    /**
     * Get the value of routeIndex
     *
     * @return the value of routeIndex
     */
    public String getIndex() {
        return routeIndex;
    }

    /**
     * Set the value of routeIndex
     *
     * @param routeIndex new value of routeIndex
     */
    public void setIndex(String routeIndex) {
        this.routeIndex = routeIndex;
    }

    public RTE(String device, 
            String sentence,
            int totalNumberOfsentence, 
            int sentenceNumber, 
            String type, 
            List<String> wpts) {
        super(device, sentence);
        this.totalNumberOfsentence = totalNumberOfsentence;
        this.sentenceNumber = sentenceNumber;
        this.type = type;
        this.waypoints.addAll(wpts);
        String last = waypoints.remove(waypoints.size() - 1);
        if (waypoints.size() > 1) {
            routeIndex = waypoints.remove(0);
        }
        StringTokenizer st = new StringTokenizer(last, "*");
        if (st.countTokens() > 1) {
            waypoints.add(st.nextToken());
        }
    }

    public RTE() {
    }

    public String getRouteIndex() {
        return routeIndex;
    }

    public void setRouteIndex(String routeIndex) {
        this.routeIndex = routeIndex;
    }

    /**
     * Get the value of waypoints
     *
     * @return the value of waypoints
     */
    public List<String> getWaypoints() {
        return waypoints;
    }

    /**
     * Set the value of waypoints
     *
     * @param waypoints new value of waypoints
     */
    public void setWaypoints(List<String> waypoints) {
        this.waypoints = waypoints;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the value of sentenceNumber
     *
     * @return the value of sentenceNumber
     */
    public int getSentenceNumber() {
        return sentenceNumber;
    }

    /**
     * Set the value of sentenceNumber
     *
     * @param sentenceNumber new value of sentenceNumber
     */
    public void setSentenceNumber(int sentenceNumber) {
        this.sentenceNumber = sentenceNumber;
    }

    /**
     * Get the value of totalNumberOfsentence
     *
     * @return the value of totalNumberOfsentence
     */
    public int getTotalNumberOfsentence() {
        return totalNumberOfsentence;
    }

    /**
     * Set the value of totalNumberOfsentence
     *
     * @param totalNumberOfsentence new value of totalNumberOfsentence
     */
    public void setTotalNumberOfsentence(int totalNumberOfsentence) {
        this.totalNumberOfsentence = totalNumberOfsentence;
    }

    @Override
    public String toString() {
        return "RTE{" + "totalNumberOfsentence=" + totalNumberOfsentence + ", sentenceNumber=" + sentenceNumber + ", type=" + type + ", waypoints=" + waypoints + ", routeIndex=" + routeIndex + '}';
    }

    
   
}
