/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.navigation.view;

import bzh.terrevirtuelle.navisu.domain.charts.vector.s57.view.BuoyageView;
import bzh.terrevirtuelle.navisu.domain.gpx.view.GpxView;
import bzh.terrevirtuelle.navisu.domain.gpx.view.HighwayView;
import bzh.terrevirtuelle.navisu.domain.navigation.navigationalWarnings.view.AvurnavView;
import bzh.terrevirtuelle.navisu.domain.navigation.sailingDirections.view.SailingDirectionsViewOld;
import bzh.terrevirtuelle.navisu.domain.ship.view.ShipView;
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
 *
 * @author serge
 * @date Jan 24, 2016
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "navigationViewSet")
@XmlRootElement
public class NavigationViewSet {

    @XmlElements({
        @XmlElement(name = "buoyageView", type = BuoyageView.class)
        ,
        @XmlElement(name = "shipView", type = ShipView.class)
        ,
        @XmlElement(name = "gpxView", type = GpxView.class)
        ,
        @XmlElement(name = "highwayView", type = HighwayView.class)
        ,
        @XmlElement(name = "sailingDirectionsViewOld", type = SailingDirectionsViewOld.class)
        ,
        @XmlElement(name = "avurnavView", type = AvurnavView.class)

    })
    private ConcurrentLinkedQueue<NavigationView> navigationViewQueue;

    public NavigationViewSet() {
        navigationViewQueue = new ConcurrentLinkedQueue<>();
    }

    public void add(NavigationView data) {
        navigationViewQueue.add(data);
    }

    public void addAll(Collection<? extends NavigationView> data) {
        navigationViewQueue.addAll(data);
    }

    public NavigationView poll() {
        return navigationViewQueue.poll();
    }

    public boolean isEmpty() {
        return navigationViewQueue.isEmpty();
    }

    @SuppressWarnings("unchecked")
    public void setNavigationViewQueue(ConcurrentLinkedQueue<NavigationView> navigationViewQueue) {
        this.navigationViewQueue = navigationViewQueue;
    }

    @SuppressWarnings("unchecked")
    public List<NavigationView> getNNavigationViewList() {
        return new ArrayList(navigationViewQueue);
    }

    public ConcurrentLinkedQueue<NavigationView> getNavigationViewQueue() {
        return navigationViewQueue;
    }

    public int size() {
        return navigationViewQueue.size();
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        navigationViewQueue.clear();
    }

    @SuppressWarnings("unchecked")
    public <T extends NavigationView> List<T> get(Class<T> t) {
        List<T> tmp = new ArrayList<>();
        navigationViewQueue.stream().forEach((data) -> {
            if (data.getClass() == t) {
                tmp.add((T) data);
            }
        });
        return tmp;
    }

    public void display() {

        navigationViewQueue.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data);
            }
        });
    }

    @Override
    public String toString() {
        return "NavigationViewSet{" + "navigationViewQueue=" + navigationViewQueue + '}';
    }

    public void print() {
        navigationViewQueue.stream().forEach((data) -> {
            if (data != null) {
                System.out.println(data.getClass().getName());
            }
        });
    }
}
