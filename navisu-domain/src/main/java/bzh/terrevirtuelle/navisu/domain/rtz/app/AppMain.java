/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.rtz.app;

import bzh.terrevirtuelle.navisu.domain.rtz.Route;
import bzh.terrevirtuelle.navisu.domain.rtz.Waypoint;
import bzh.terrevirtuelle.navisu.domain.rtz.Waypoints;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author serge
 * @date Apr 21, 2018
 */
public class AppMain {

    public static void main(String[] args) {
        Route route = new Route();
        try {
            route = ImportExportXML.imports(route, "data/dr2_1.rtz");
            System.out.println(route);
            Waypoints waypoints = route.getWaypoints();
            List<Waypoint> wpList = waypoints.getWaypoint();
            wpList.forEach((wp) -> {
                System.out.println(wp);
            });
            
        } catch (FileNotFoundException | JAXBException ex) {
            Logger.getLogger(AppMain.class.getName()).log(Level.SEVERE, ex.toString(), ex);
        }
    }
}
