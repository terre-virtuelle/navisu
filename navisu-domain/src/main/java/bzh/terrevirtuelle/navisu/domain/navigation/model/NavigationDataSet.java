/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.model;

import bzh.terrevirtuelle.navisu.domain.bathymetry.model.Depth;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.S57Chart;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss.Rss;
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
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Daymark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.Landmark;
import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.model.geo.MooringWarpingFacility;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.domain.geometry.model.Area;
import bzh.terrevirtuelle.navisu.domain.gpx.model.Gpx;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Book;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.shom.Text;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.model.rss.NavigationalWarningsRSS;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.model.SailingDirections;
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
        @XmlElement(name = "area", type = Area.class),
        @XmlElement(name = "avurnavss", type = NavigationalWarningsRSS.class),
        @XmlElement(name = "buoyage", type = Buoyage.class),
        @XmlElement(name = "bcncar", type = BeaconCardinal.class),
        @XmlElement(name = "bcnisd", type = BeaconIsolatedDanger.class),
        @XmlElement(name = "bcnlat", type = BeaconLateral.class),
        @XmlElement(name = "bcnsaw", type = BeaconSafeWater.class),
        @XmlElement(name = "bcnspp", type = BeaconSpecialPurpose.class),
        @XmlElement(name = "buoycar", type = BuoyCardinal.class),
        @XmlElement(name = "buoyinb", type = BuoyInstallation.class),
        @XmlElement(name = "buoyisd", type = BuoyIsolatedDanger.class),
        @XmlElement(name = "buoylat", type = BuoyLateral.class),
        @XmlElement(name = "buoysaw", type = BuoySafeWater.class),
        @XmlElement(name = "buoyssp", type = BuoySpecialPurpose.class),
        @XmlElement(name = "camera", type = Camera.class),
        @XmlElement(name = "daymark", type = Daymark.class),
        @XmlElement(name = "gpx", type = Gpx.class),
        @XmlElement(name = "lndmrk", type = Landmark.class),
        @XmlElement(name = "morfac", type = MooringWarpingFacility.class),
        @XmlElement(name = "rss", type = Rss.class),
        @XmlElement(name = "sailingDirections", type = SailingDirections.class),
        @XmlElement(name = "ship", type = Ship.class),
        @XmlElement(name = "s57Chart", type = S57Chart.class),
        @XmlElement(name = "ouvrage", type = Book.class),
        @XmlElement(name = "texte", type = Text.class),
        @XmlElement(name = "point3d", type = Point3D.class),
        @XmlElement(name = "depth", type = Depth.class),
        @XmlElement(name = "target", type = Target.class)
    }
    )
    private ConcurrentLinkedQueue<NavigationData> navigationDataQueue;

    public NavigationDataSet() {
        navigationDataQueue = new ConcurrentLinkedQueue<>();
    }

    @SuppressWarnings("unchecked")
    public void add(NavigationData data) {
        navigationDataQueue.add(data);
    }

    public void addAll(Collection<? extends NavigationData> data) {
        navigationDataQueue.addAll(data);
    }

    public NavigationData poll() {
        return navigationDataQueue.poll();
    }

    public boolean isEmpty() {
        return navigationDataQueue.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public void setNavigationDataQueue(ConcurrentLinkedQueue<NavigationData> navigationDataQueue) {
        this.navigationDataQueue = navigationDataQueue;
    }

    @SuppressWarnings("unchecked")
    public List<NavigationData> getNavigationDataList() {
        return new ArrayList(navigationDataQueue);
    }

    public ConcurrentLinkedQueue<NavigationData> getNavigationDataQueue() {
        return navigationDataQueue;
    }

    public int size() {
        return navigationDataQueue.size();
    }

    public void clear() {
        navigationDataQueue.clear();
    }

    @SuppressWarnings("unchecked")
    public <T extends NavigationData> List<T> get(Class<T> t) {
        List<T> tmp = new ArrayList<>();
        navigationDataQueue.stream().forEach((data) -> {
            if (data.getClass() == t) {
                tmp.add((T) data);
            }
        });
        return tmp;
    }

    public void display() {

        navigationDataQueue.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data);
            }
        });
    }

    @Override
    public String toString() {
        return "NavigationDataSet{" + "navigationDataSet=" + navigationDataQueue + '}';
    }

    public void print() {
        navigationDataQueue.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data);
            }
        });
    }
}
