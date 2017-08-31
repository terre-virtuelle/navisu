/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.view;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import com.vividsolutions.jts.geom.Geometry;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 15 aout 2017
 * @author Serge Morvan
 */
public interface DisplayBathymetryServices
        extends ComponentService {

    InstrumentDriver getDriver();

    Geometry getConcaveHull(List<Point3D> points, double threshold);

    Delaunay_Triangulation getTriangulation(List<Point3D> points);

    List<Triangle_dt> filterLargeEdges(ArrayList<Triangle_dt> triangles, double threshold);

    void displaySounding(List<Point3D> points);

    void displayAllSounding();

}
