/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation;

import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.rss.Rss;
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
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Location;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.SailingDirections;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.Avurnav;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.AvurnavSet;
import bzh.terrevirtuelle.navisu.domain.navigation.avurnav.rss.AvurnavRSS;
import bzh.terrevirtuelle.navisu.domain.ship.model.Ship;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * NaVisu
 *
 * @date 16 oct. 2015
 * @author Serge Morvan
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "navigationDataSet")
@XmlRootElement
public class NavigationDataSet {

    @XmlElements({
        @XmlElement(name = "navigationData", type = NavigationData.class),
        @XmlElement(name = "bcncar", type = BeaconCardinal.class),
        @XmlElement(name = "bcnisd", type = BeaconIsolatedDanger.class),
        @XmlElement(name = "bcnlat", type = BeaconLateral.class),
        @XmlElement(name = "bcnsaw", type = BeaconSafeWater.class),
        @XmlElement(name = "bcnspp", type = BeaconSpecialPurpose.class),
        @XmlElement(name = "boycar", type = BuoyCardinal.class),
        @XmlElement(name = "boyinb", type = BuoyInstallation.class),
        @XmlElement(name = "boyisd", type = BuoyIsolatedDanger.class),
        @XmlElement(name = "boylat", type = BuoyLateral.class),
        @XmlElement(name = "boysaw", type = BuoySafeWater.class),
        @XmlElement(name = "boyssp", type = BuoySpecialPurpose.class),
        @XmlElement(name = "morfac", type = MooringWarpingFacility.class),
        @XmlElement(name = "lndmrk", type = Landmark.class),
        @XmlElement(name = "ship", type = Ship.class),
        @XmlElement(name = "rss", type = Rss.class),
        @XmlElement(name = "avurnavss", type = AvurnavRSS.class),
        @XmlElement(name = "avurnav", type = Avurnav.class),
        @XmlElement(name = "sailingDirections", type = SailingDirections.class),
        @XmlElement(name = "avurnavSet", type = AvurnavSet.class),
        @XmlElement(name = "gpx", type = Gpx.class)
    })

    private final ConcurrentLinkedQueue<NavigationData> navigationDataSet;

    public NavigationDataSet() {
        navigationDataSet = new ConcurrentLinkedQueue<>();
    }

    public void add(NavigationData data) {
        navigationDataSet.add(data);
    }

    public void addAll(Collection<? extends NavigationData> data) {
        navigationDataSet.addAll(data);
    }

    public NavigationData poll() {
        return navigationDataSet.poll();
    }

    public boolean isEmpty() {
        return navigationDataSet.isEmpty();
    }

    public ConcurrentLinkedQueue<NavigationData> getNavigationDataSet() {
        return navigationDataSet;
    }

    public int size() {
        return navigationDataSet.size();
    }

    public void clear() {
        navigationDataSet.clear();
    }

    public List<Location> getLocations() {
        List<Location> tmp = new ArrayList<>();
        navigationDataSet.stream().forEach((nd) -> {
            tmp.add(nd.getLocation());
        });
        return tmp;
    }

    public <T extends NavigationData> List<T> get(Class<T> t) {
        List<T> tmp = new ArrayList<>();
        navigationDataSet.stream().forEach((data) -> {
            if (data.getClass() == t) {
                tmp.add((T) data);
            }
        });
        return tmp;
    }

    public void display() {

        navigationDataSet.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data);
            }
        });
    }

    @Override
    public String toString() {
        return "NavigationDataSet{" + "navigationDataSet=" + navigationDataSet + '}';
    }

    public void print() {
        navigationDataSet.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data.getClass().getName());
            }
        });
    }
}
