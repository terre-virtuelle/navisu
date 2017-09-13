package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util;

import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Util for Point manipulating
 */
public final class UtPoint {
    private UtPoint() {
    }

    //region use for gathering isoline from triangulation
    public static Point_dt[] divide(final Point_dt p0, final Point_dt p1, int step) {
        Point_dt lower, higher;
        if (p0.equals(p1)) {
            return new Point_dt[0];
        }

        if (p0.z == p1.z) {
            if (p0.z % step == 0) {
                return new Point_dt[]{p0, p1};
            }
            return new Point_dt[0];
        }

        if (p0.z < p1.z) {
            lower = p0;
            higher = p1;
        } else {
            lower = p1;
            higher = p0;
        }


        //round the coordinates to take same points for IsoLines
        int roundLower = (int) lower.z % step;
        if (roundLower > 0) {
            int tmpSecCount = (int) higher.z - (int) lower.z;
            Point_dt temp = segment(lower, higher, step - roundLower, tmpSecCount);
            temp.z = lower.z + (step - roundLower);
            lower = temp;
        }

        int roundHigher = (int) higher.z % step;
        if (roundHigher > 0) {
            int tmpSecCount = (int) higher.z - (int) lower.z;
            Point_dt temp = segment(lower, higher, tmpSecCount - roundHigher, tmpSecCount);
            temp.z = higher.z - roundHigher;
            higher = temp;
        }

        if (higher.z - lower.z < step) {
            if (higher.z == lower.z) {
                return new Point_dt[]{lower};
            }
            return new Point_dt[0];
        }

        int segCount = (int) ((higher.z - lower.z) / step);
        Point_dt[] coords = new Point_dt[segCount + 1];
        coords[0] = lower;
        coords[coords.length - 1] = higher;

        for (int i = 1; i < segCount; i++) {
            Point_dt coordinate = coords[i] = segment(lower, higher, i, segCount);
            coordinate.z = lower.z + (i * step);
        }
        return coords;
    }

    public static Point_dt segment(final Point_dt p0, final Point_dt p1, int segment, int segCount) {
        double k = segment / (double) segCount;
        return new Point_dt(p0.x + k * (p1.x - p0.x), p0.y + k * (p1.y - p0.y));
    }
    //endregion

    //region use for Divide till Epsilon triangulation edges

    /**
     * computes and sets z value by
     * See also the method {@link #computeZ(Set, Point_dt)}.
     *
     * @param t triangle, used Three neighbours vertexes to get z value
     * @param p point from compute the weights or vertexes by distance from this point
     */
    public static void computeZ(Triangle_dt t, Point_dt p) {
        Set<Point_dt> points = new HashSet<Point_dt>();
        points.add(t.A);
        points.add(t.B);
        points.add(t.C);
        if (t.abnext != null) {
            points.add(t.abnext.A);
            points.add(t.abnext.B);
            points.add(t.abnext.C);
        }
        if (t.bcnext != null) {
            points.add(t.bcnext.A);
            points.add(t.bcnext.B);
            points.add(t.bcnext.C);
        }
        if (t.canext != null) {
            points.add(t.canext.A);
            points.add(t.canext.B);
            points.add(t.canext.C);
        }

        computeZ(points, p);
    }

    /**
     * computes and sets z value by
     * <a href="https://en.wikipedia.org/wiki/Inverse_distance_weighting">Inverse distance weighting</a>
     * method
     *
     * @param points points which to get z value with weight
     * @param p      point from compute the weights or vertexes by distance from this point
     */
    public static void computeZ(Set<Point_dt> points, Point_dt p) {
        double weightSum = 0;
        ArrayList<Double> weights = new ArrayList<Double>(points.size());
        ArrayList<Double> zVals = new ArrayList<Double>(points.size());
        for (Point_dt point : points) {
            if (point == null) continue;
            double dist = Math.pow(point.distance(p), 2);
            if (dist != 0) {
                double wI = 1 / dist;
                weightSum += wI;
                weights.add(wI);
                zVals.add(point.z);
            } else {
                //wI = infinitive, same point
                p.z = point.z;
                return;
            }
        }

        double avgSum = 0;

        for (int i = 0; i < weights.size(); i++) {
            Double wI = weights.get(i);
            Double zVal = zVals.get(i);
            avgSum += (wI * zVal) / weightSum;
        }

        p.z = (int) avgSum;
    }

    /**
     * used to get segments of line in ordered way to avoid Double Calculating Error <code>a</code> and <code>b</code>
     *
     * @param a        first point of line
     * @param b        second point of line
     * @param segment  segment
     * @param segCount all segments
     * @return if {@code a={0,0}, b={10,10}, i= 1, segCount = 10}} point = {1,1}
     * <p>the same as</p>
     * <p>
     * if {@code a={10,10}, b={0,0}, segment=1, segCount=10}} >>> point={1,1}
     * </p>
     */
    public static Point_dt segmentByOrder(Point_dt a, Point_dt b, int segment, int segCount) {
        Point_dt p1, p2;
        if (a.x > b.x) {
            p1 = a;
            p2 = b;
        } else if (b.x > a.x) {
            p1 = b;
            p2 = a;
        } else if (a.y > b.y) {
            p1 = a;
            p2 = b;
        } else if (b.y > a.y) {
            p1 = b;
            p2 = a;
        } else {
            throw new RuntimeException("points are same");
        }
        double k = segment / (double) segCount;
        return new Point_dt(p1.x + k * (p2.x - p1.x), p1.y + k * (p2.y - p1.y));
    }

    public static double angleBetween(Point_dt center, Point_dt first, Point_dt last) {

        double v1x = first.x - center.x;
        double v1y = first.y - center.y;

        //need to normalize:
        double l1 = Math.sqrt(v1x * v1x + v1y * v1y);
        v1x /= l1;
        v1y /= l1;

        double v2x = last.x - center.x;
        double v2y = last.y - center.y;

        //need to normalize:
        double l2 = Math.sqrt(v2x * v2x + v2y * v2y);
        v2x /= l2;
        v2y /= l2;

        double rad = Math.acos(v1x * v2x + v1y * v2y);

        return Math.toDegrees(rad);
    }
    //endregion
}
