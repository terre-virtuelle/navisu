/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.visualization.view;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;
import bzh.terrevirtuelle.navisu.geometry.isoline.triangulation.Triangle_dt;
import com.vividsolutions.jts.geom.Geometry;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.Material;
import gov.nasa.worldwind.render.Path;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 15 aout 2017
 * @author Serge Morvan
 */
public interface DisplayServices
        extends ComponentService {

    // InstrumentDriver getDriver();
    void displayPoints3D(List<Point3D> points, RenderableLayer layer);

    void displayPlane(double minLat, double minLon, double maxLat, double maxLon, double height,
            Material material, RenderableLayer l);

    void displayTriangle(Triangle_dt t, double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    void displayDelaunay(List<Triangle_dt> triangles,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    void displayConcaveHull(Geometry concaveHull,
            double height, double verticalExaggeration,
            Material material, RenderableLayer l);

    Path createPath(List<Position> pathPositions, Material material);

    List<Triangle_dt> filterLargeEdges(List<Triangle_dt> triangles, double threshold);

}
