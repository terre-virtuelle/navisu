/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.cartography.projection;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date @author Serge Morvan
 */
public interface Pro4JServices
        extends ComponentService {

    Point3D convertLambert93ToWGS84(double lat, double lon);

    Point3D convertLambert93ToWGS84(Point3D pt);

    List<Point3D> convertLambert93ToWGS84(List<Point3D> pt);
    
    Point3D convertCoordinates(String epsgSrc, String epsgdest, Point3D pt);
}
