package bzh.terrevirtuelle.navisu.geometry.isoline.triangulation;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Border contour
 */
public class Border {
    //All pointSet, vertices and izoPoints, check for contains when adding
    public LinkedHashSet<Point_dt> pointSet = new LinkedHashSet<Point_dt>();

    //quick get contour started from point
    public HashMap<Point_dt, Contour> contours = new HashMap<Point_dt, Contour>();
}