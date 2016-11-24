package bzh.terrevirtuelle.navisu.geometry.isoline.triangulation;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Contour
 */
public class Contour {
    // area == INIT_AREA means area is not calculated
    private static final double INIT_AREA = 0.0;

    //isoline level
    public int level;
    public boolean inside;
    public boolean right;

    //points ordered way
    public LinkedList<Point_dt> points = new LinkedList<>();

    //contours lied inside
    public ArrayList<Contour> holes = new ArrayList<>();

    //for comparison, sort
    private double area = INIT_AREA;
    private Area cachedGeom;

    public Contour(int level) {
        this.level = level;
    }

    public Contour(int level, boolean inside, boolean right) {
        this.level = level;
        this.inside = inside;
        this.right = right;
    }

    public Contour(Contour c) {
        this.level = c.level;
        this.inside = c.inside;
        this.right = c.right;
        this.points.addAll(c.points);
    }

    /**
     * checks points size larger > 1 and last point equals first
     *
     * @return true if getA equals getB
     */
    public boolean isClosed() {
        return points.size() > 1 && getA().equals(getB());
    }

    public void extendA(Point_dt pA) {
        if (!getB().equals(pA) && points.contains(pA)) {
            throw new RuntimeException(String.format("self intersecting by point %s", pA));
        }
        points.addFirst(pA);
    }

    public void extendB(Point_dt pB) {
        if (!getA().equals(pB) && points.contains(pB)) {
            throw new RuntimeException(String.format("self intersecting by point %s", pB));
        }
        points.addLast(pB);
    }

    //region example

    //   lA=cA,lB <> cB --  cA change to ===> lB, not closed
    //
    //     /*------*\
    //    /          \
    //   /            \
    //  * lA=cA        * cB
    //   \
    //    \
    //     * lB

    // endregion
    public boolean extendTo(Izoline l) {
        if (isClosed()) {
            throw new RuntimeException("already closed " + this);
        }
        boolean extended = false;
        if (getA().equals(l.B)) {
            extendA(l.A);
            extended = true;
        } else if (getA().equals(l.A)) {
            extendA(l.B);
            extended = true;
        } else if (getB().equals(l.A)) {
            extendB(l.B);
            extended = true;
        } else if (getB().equals(l.B)) {
            extendB(l.A);
            extended = true;
        }

        return extended;

    }

    /**
     * @return first point in line
     */
    public Point_dt getA() {
        return points.getFirst();
    }

    /**
     * @return last point in line
     */
    public Point_dt getB() {
        return points.getLast();
    }

    public double getArea() {
        if (!isClosed()) {
            throw new RuntimeException("calling for non closed points");
        }
        if (area == INIT_AREA) {
            double sum_but_no_result = 0;
            ListIterator<Point_dt> itr = points.listIterator();
            Point_dt curr = itr.next();//throws Exception if points size = 0

            while (curr != null && itr.hasNext()) {
                Point_dt next = itr.next();
                sum_but_no_result += curr.x * next.y - curr.y * next.x;
                curr = next;
            }
            area = Math.abs(sum_but_no_result / 2);
        }
        return area;
    }

    /**
     * @return The bounding rectangle between the minimum and maximum coordinates
     */
    public BoundingBox getBoundingBox() {
        double maxX = getA().x, maxY = getA().y, minX = maxX, minY = maxY;
        for (Point_dt A : points) {
            if (A.x > maxX) {
                maxX = A.x;
            } else if (A.x < minX) {
                minX = A.x;
            }
            if (A.y > maxY) {
                maxY = A.y;
            } else if (A.y < minY) {
                minY = A.y;
            }
        }
        return new BoundingBox(minX, maxX, minY, maxY);
    }

    @Override
    public String toString() {
        return "start:" + getA().toString() + "end:" + getB().toString();
    }

    /**
     * always calculates new Path from points
     *
     * @return new Path2D object
     */
    public Path2D getPath2D() {
        ListIterator<Point_dt> itr = points.listIterator();
        Point_dt a = itr.next();//throws Exception if points size = 0
        Path2D poly = new Path2D.Double();
        boolean isFirst = true;
        while (a != null && itr.hasNext()) {
            Point_dt b = itr.next();
            double x = a.x;
            double y = a.y;

            if (isFirst) {
                poly.moveTo(x, y);
                isFirst = false;
            } else {
                poly.lineTo(x, y);
            }
            a = b;
        }
        poly.closePath();
        return poly;
    }

    public Area cachedArea() {
        if (this.cachedGeom == null) {
            reBuildArea();
        }
        return this.cachedGeom;
    }

    public void reBuildArea() {
        this.cachedGeom = new Area(getPath2D());
    }
}
