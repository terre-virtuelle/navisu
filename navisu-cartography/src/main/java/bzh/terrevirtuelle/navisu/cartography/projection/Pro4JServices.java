/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.cartography.projection;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3DGeo;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date @author Serge Morvan
 */
public interface Pro4JServices
        extends ComponentService {

    Point3DGeo convertLambert93ToWGS84(double lat, double lon);

    Point3DGeo convertLambert93ToWGS84(Point3DGeo pt);

    List<Point3DGeo> convertLambert93ToWGS84(List<Point3DGeo> pt);
    
    Point3DGeo convertCoordinates(String epsgSrc, String epsgdest, Point3DGeo pt);

    String convertObjLambert93ToObjWGS84(String filename);
     
}
