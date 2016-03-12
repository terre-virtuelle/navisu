/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 30 oct. 2015
 * @author Serge Morvan
 */
@XmlRootElement
@XmlType(name = "avurnav",
        propOrder = {"id", "globalZone", "broadcastTime",
            "expirationDate", "description",
            "restriction", "type",
           // "latitude", "longitude",
           // "geometry"
        })
@XmlAccessorType(XmlAccessType.FIELD)
public class NavigationalWarnings
        extends Location
        implements NavigationData {

    private String globalZone;
    private String description;
    private String expirationDate;
    private String broadcastTime;
    private String restriction;
    private String type;

    public NavigationalWarnings() {
        super(0, 0.0, 0.0);
    }

    public NavigationalWarnings(long id, double lat, double lon) {
        super(id, lat, lon);
    }

    public NavigationalWarnings(long id, String geometry) {
        super(id, geometry);
    }

    public NavigationalWarnings(long id, double lat, double lon,
            String globalZone, String description, String expirationDate,
            String broadcastTime, String restriction, String type) {
        super(id, lat, lon);
        this.globalZone = globalZone;
        this.description = description;
        this.expirationDate = expirationDate;
        this.broadcastTime = broadcastTime;
        this.restriction = restriction;
        this.type = type;
    }

    public String getGlobalZone() {
        return globalZone;
    }

    public void setGlobalZone(String globalZone) {
        this.globalZone = globalZone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getBroadcastTime() {
        return broadcastTime;
    }

    public void setBroadcastTime(String broadcastTime) {
        this.broadcastTime = broadcastTime;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Avurnav{" + super.toString() + "globalZone=" + globalZone + ", description=" + description + ", expirationDate=" + expirationDate + ", broadcastTime=" + broadcastTime + ", restriction=" + restriction + ", type=" + type + '}';
    }

    public String printf() {
        String tmp = "";
        if (id != 0) {
            tmp = "Avurnav : " + id + "\n"
                    + "GlobalZone : " + globalZone + "\n"
                    + "BroadcastTime : " + broadcastTime + "\n"
                    + "ExpirationDate : " + expirationDate + "\n"
                    + "Description : " + description + "\n\n";

        }
        return tmp;
    }
}
