/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.model.Target;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.PointPlacemark;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author serge
 * @date Dec 8, 2017
 */
@XmlRootElement(name = "TargetCmd")
@XmlAccessorType(XmlAccessType.FIELD)
public class TargetCmd
        implements NavigationCmd {

    private static TargetCmd INSTANCE;
    private NavigationDataSet navigationDataSet;
    private final GeodesyServices geodesyServices;
    private final S57ChartComponentServices s57ChartComponentServices;
    private LayersManagerServices layersManagerServices;
    private Set<S57Controller> s57Controllers;

    private static final String NAME = "TargetCmd";
    protected static final String GROUP = "S57 charts";
    protected RenderableLayer layer;

    public static TargetCmd getInstance(S57ChartComponentServices s57ChartComponentServices,
            GeodesyServices geodesyServices, LayersManagerServices layersManagerServices) {
        if (INSTANCE == null) {
            INSTANCE = new TargetCmd(s57ChartComponentServices, geodesyServices, layersManagerServices);
        }
        return INSTANCE;
    }

    private TargetCmd(S57ChartComponentServices s57ChartComponentServices,
            GeodesyServices geodesyServices, LayersManagerServices layersManagerServices) {
        this.s57ChartComponentServices = s57ChartComponentServices;
        this.geodesyServices = geodesyServices;
        this.layersManagerServices=layersManagerServices;
        layer = layersManagerServices.getLayer(GROUP, NAME);
    }

    @Override
    public NavigationDataSet doIt(NavigationData arg) {
        // Set of beacon, buoys, ... on the chart
        s57Controllers = s57ChartComponentServices.getS57Controllers();
        navigationDataSet = new NavigationDataSet();

        Target target = (Target) arg;
        double lat = target.getLatitude();
        double lon = target.getLongitude();
        double distance = target.getDistance();
        double azimuth = target.getAzimuth();

        //First filter the type of NavigatiobData
        Class type = target.getNavigationData().getClass();
        Set<S57Controller> validS57 = new HashSet<>();
        List<Class> types;
        for (S57Controller s : s57Controllers) {
            types = getSuperClasses(s.getNavigationData());
            if (types.contains(type)) {
                validS57.add(s);
            }
        }
        //Calculate distance from locate point
        double dist;
        double azi;
        Target tgt;
        int id = 0;
        List<Target> targets = new ArrayList<>();
        for (S57Controller s : validS57) {
            dist = geodesyServices.getDistanceM(s.getLat(), s.getLon(), lat, lon);
            azi = geodesyServices.getAzimuth(s.getLat(), s.getLon(), lat, lon);
            System.out.println("dist : " + dist + "azi :  " + azi);
            tgt = new Target(s.getNavigationData(), s.getNavigationData().getLatitude(), s.getNavigationData().getLongitude(), id, dist, azi);

            targets.add(tgt);
            id++;
        }
        targets.sort(Comparator.comparingDouble(Target::getDistance));
        navigationDataSet.add(targets.get(0));
        PointPlacemark pp = new PointPlacemark(Position.fromDegrees(lat,lon, 1e4));
        layer.addRenderable(pp);
        return navigationDataSet;
    }

    public final List<Class> getSuperClasses(Object o) {
        List<Class> classList = new ArrayList<>();
        Class classe = o.getClass();
        classList.add(classe);
        Class superclass = classe.getSuperclass();
        classList.add(superclass);
        while (superclass != null) {
            classe = superclass;
            superclass = classe.getSuperclass();
            if (superclass != null) {
                classList.add(superclass);
            }
        }
        return classList;
    }
    
}
