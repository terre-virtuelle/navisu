/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands.impl;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.impl.controller.navigation.S57Controller;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.domain.navigation.model.Target;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmd;
import bzh.terrevirtuelle.navisu.geometry.geodesy.GeodesyServices;
import java.util.ArrayList;
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
    private Set<S57Controller> s57Controllers;
    
    public static TargetCmd getInstance(S57ChartComponentServices s57ChartComponentServices,
            GeodesyServices geodesyServices) {
        if (INSTANCE == null) {
            INSTANCE = new TargetCmd(s57ChartComponentServices, geodesyServices);
        }
        return INSTANCE;
    }
    
    private TargetCmd(S57ChartComponentServices s57ChartComponentServices,
            GeodesyServices geodesyServices) {
        this.s57ChartComponentServices=s57ChartComponentServices;
        this.geodesyServices = geodesyServices;
    }
    
    @Override
    public NavigationDataSet doIt(NavigationData arg) {
      //  s57Controllers = new HashSet<>();
      //  System.out.println("arg : " + arg);
      Target target = (Target)arg;
        System.out.println("request : " +target.getNavigationData().getClass().getSimpleName());
       // System.out.println(target.getLatitude());
       // System.out.println(target.getLongitude());
        //System.out.println(target.getDistance());
       // System.out.println(target.getAzimuth());
        s57Controllers = s57ChartComponentServices.getS57Controllers();
      //  System.out.println("s57Controllers : " + s57Controllers);
      for(S57Controller s : s57Controllers){
          System.out.println("s : " + s.getNavigationData().getClass().getSimpleName());
      }
       /*
        Depth depth = (Depth) arg;
        double lat = depth.getLatitude();
        double lon = depth.getLongitude();
       
        navigationDataSet = new NavigationDataSet();
        List<Point3D> points = bathymetryDBServices.retrieveIn(lat, lon, lat + 0.0015, lon + 0.0015);
        points.forEach((p) -> {
            navigationDataSet.add(p);
        });
*/
        return navigationDataSet;
    }
    public final List<Class> getSuperClasses(Object o) {
        List<Class> classList = new ArrayList<>();
        Class classe = o.getClass();
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
