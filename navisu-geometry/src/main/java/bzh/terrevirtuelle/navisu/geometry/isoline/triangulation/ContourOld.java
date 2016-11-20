package bzh.terrevirtuelle.navisu.geometry.isoline.triangulation;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Contour from izolines
 */
public class ContourOld {
    public static final double INIT_AREA = 0.0;

    public Contour outer;
    private ArrayList<Contour> inners = new ArrayList<Contour>(0);
    public int elevation;

    private double polygonArea = INIT_AREA;
    //all border lines(polygon)
    private LinkedList<Izoline> izolines;
    //initial lines(intersects the border convex hull or closes itself)
    private LinkedList<Izoline> selfLines;
    private Area shape;

    public ContourOld(ContourOld c) {
        //copy one by one, not the link to the list
        this.izolines = new LinkedList<Izoline>(c.izolines);
        this.selfLines = c.getSelfLines();
        this.elevation = c.elevation;
    }

    public ContourOld(Izoline line) {
        elevation = line.elevation;
        izolines = new LinkedList<Izoline>();
        izolines.add(line);
    }

    @Override
    public String toString() {
        return "start:" + izolines.getFirst().toString() + "end:" + izolines.getLast().toString();
    }

    public boolean isClosed() {
        return getA().equals(getB());
    }

    public void extendA(Point_dt pA) {
        if (!pA.equals(getA())) {
            izolines.addFirst(new Izoline(pA, getA(), elevation));
        }
    }

    public void extendB(Point_dt pB) {
        if (!pB.equals(getB())) {
            izolines.addLast(new Izoline(getB(), pB, elevation));
        }
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
        if (izolines.contains(l)) {
            throw new RuntimeException("already contains");
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
     * shorthand for izolines.getFirst().A
     *
     * @return first point in line
     */
    public Point_dt getA() {
        return izolines.getFirst().A;
    }

    /**
     * shorthand for izolines.getLast().B
     *
     * @return last point in line
     */
    public Point_dt getB() {
        return izolines.getLast().B;
    }

    public double getPolygonArea() {
        if (!isClosed()) {
            throw new RuntimeException("not closed- not a polygon");
        }
        if (polygonArea == INIT_AREA) {
            calcArea();
        }
        return polygonArea;
    }

    public void setPolygonArea(double area) {
        polygonArea = area;
    }

    private void calcArea() {
        double sum_but_no_result = 0;
        for (Izoline l : izolines) {
            sum_but_no_result += l.A.x * l.B.y - l.A.y * l.B.x;
        }
        polygonArea = Math.abs(sum_but_no_result / 2);
        /*double maxX = getA().x, maxY = getA().y, minX = maxX, minY = maxY;
        for (Izoline l : izolines) {
            if (l.A.x > maxX) {
                maxX = l.A.x;
            }else if (l.A.x < minX) {
                minX = l.A.x;
            }
            if (l.A.y > maxY) {
                maxY = l.A.y;
            }else if (l.A.y < minY) {
                minY = l.A.y;
            }
        }*/
    }

    public void reverse() {
        Collections.reverse(izolines);
        for (Izoline l : izolines) {
            l.reverse();
        }
    }

    public LinkedList<Izoline> getIzolines() {
        return izolines;
    }

    public void resetSelfLine() {
        this.selfLines = new LinkedList<Izoline>(this.izolines);
    }

    public LinkedList<Izoline> getSelfLines() {
        return selfLines;
    }

    public Area getShape() {
        if (shape == null) {
            Path2D poly = new Path2D.Double();
            boolean isFirst = true;
            for (Izoline l : izolines) {
                double x = l.A.x;
                double y = l.A.y;

                if (isFirst) {
                    poly.moveTo(x, y);
                    isFirst = false;
                } else {
                    poly.lineTo(x, y);
                }
            }
            poly.closePath();
            shape = new Area(poly);
        }
        return shape;
    }

    public void setShape(Area shape) {
        this.shape = shape;
    }

    public ArrayList<Contour> getInners() {
        return inners;
    }

    public void setInners(ArrayList<Contour> inners) {
        this.inners = inners;
    }
}
