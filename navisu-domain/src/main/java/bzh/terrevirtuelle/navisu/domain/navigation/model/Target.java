/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.model;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BeaconSpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyCardinal;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyInstallation;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyIsolatedDanger;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoyLateral;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySafeWater;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.BuoySpecialPurpose;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Buoyage;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.geometry.model.Area;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss.NavigationalWarningsRSS;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author serge
 * @param <T>
 * @date Dec 8, 2017
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "target", propOrder = {
    "navigationData",
    "latitude",
    "longitude",
    "distance",
    "azimuth",
    "id"
})
@XmlRootElement
public class Target<T extends NavigationData>
        implements NavigationData {

    @XmlElements({
        @XmlElement(name = "area", type = Area.class) ,
        @XmlElement(name = "avurnavss", type = NavigationalWarningsRSS.class),
        @XmlElement(name = "buoyage", type = Buoyage.class),
        @XmlElement(name = "bcncar", type = BeaconCardinal.class) ,
        @XmlElement(name = "bcnisd", type = BeaconIsolatedDanger.class),
        @XmlElement(name = "bcnlat", type = BeaconLateral.class) ,
        @XmlElement(name = "bcnsaw", type = BeaconSafeWater.class),
        @XmlElement(name = "bcnspp", type = BeaconSpecialPurpose.class),
        @XmlElement(name = "buoycar", type = BuoyCardinal.class),
        @XmlElement(name = "buoyinb", type = BuoyInstallation.class),
        @XmlElement(name = "buoyisd", type = BuoyIsolatedDanger.class) ,
        @XmlElement(name = "buoylat", type = BuoyLateral.class),
        @XmlElement(name = "buoysaw", type = BuoySafeWater.class),
        @XmlElement(name = "buoyssp", type = BuoySpecialPurpose.class),
        @XmlElement(name = "morfac", type = MooringWarpingFacility.class),
        @XmlElement(name = "lndmrk", type = Landmark.class),
        @XmlElement(name = "ship", type = Ship.class),
    })
    private T navigationData;

    private double latitude;

    private double longitude;

    private long id = 0;

    private double distance = -1;

    private double azimuth = 511;

    public Target() {
    }

    public Target(T navigationData, double latitude, double longitude, long id, double distance, double azimuth) {
        this.navigationData = navigationData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
        this.distance = distance;
        this.azimuth = azimuth;
    }

    public Target(T navigationData, double latitude, double longitude) {
        this.navigationData = navigationData;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Target(T navigationData, double latitude, double longitude, double distance) {
        this.navigationData = navigationData;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    /**
     * Get the value of azimuth
     *
     * @return the value of azimuth
     */
    public double getAzimuth() {
        return azimuth;
    }

    /**
     * Set the value of azimuth
     *
     * @param azimuth new value of azimuth
     */
    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    /**
     * Get the value of distance
     *
     * @return the value of distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Set the value of distance
     *
     * @param distance new value of distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of navigationData
     *
     * @return the value of navigationData
     */
    public NavigationData getNavigationData() {
        return navigationData;
    }

    /**
     * Get the value of navigationData
     *
     * @return the value of navigationData
     */
    public NavigationData getTarget() {
        return navigationData;
    }

    /**
     * Set the value of navigationData
     *
     * @param navigationData new value of navigationData
     */
    public void setNavigationData(T navigationData) {
        this.navigationData = navigationData;
    }

    /**
     * Set the value of navigationData
     *
     * @param navigationData new value of navigationData
     */
    public void setTarget(T navigationData) {
        this.navigationData = navigationData;
    }

    @Override
    public String toString() {
        return "Target{" + "navigationData=" + navigationData + ", latitude=" + latitude + ", longitude=" + longitude + ", id=" + id + ", distance=" + distance + ", azimuth=" + azimuth + '}';
    }
    
}
