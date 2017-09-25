package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util;

import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Contour;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Izoline;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;

import java.util.*;

/**
 * Util for Delaunay Triangulation
 */
public final class DT {

    private DT() {
    }

    public static Set<Izoline> getIzolines(Triangle_dt t, int elevatedStep) {
        Set<Izoline> lines = new HashSet<Izoline>();
        Point_dt[] divideAB = UtPoint.divide(t.A, t.B, elevatedStep);
        Point_dt[] divideBC = UtPoint.divide(t.B, t.C, elevatedStep);
        Point_dt[] divideCA = UtPoint.divide(t.C, t.A, elevatedStep);
        for (Point_dt pAB : divideAB) {
            for (Point_dt pBC : divideBC) {
                if (pAB.z < pBC.z) {
                    break;//already ordered
                }
                if (pAB.z == pBC.z && !pAB.equals(pBC)) {
                    Izoline line = new Izoline(pAB, pBC, (int) pBC.z);
                    lines.add(line);
                }
            }
            for (Point_dt pCA : divideCA) {
                if (pAB.z < pCA.z) {
                    break;//already ordered
                }
                if (pAB.z == pCA.z && !pAB.equals(pCA)) {
                    Izoline line = new Izoline(pAB, pCA, (int) pCA.z);
                    lines.add(line);
                }
            }
        }
        for (Point_dt pBC : divideBC) {
            for (Point_dt pCA : divideCA) {
                if (pBC.z < pCA.z) {
                    break;
                }

                if (pBC.z == pCA.z && !pBC.equals(pCA)) {
                    Izoline line = new Izoline(pBC, pCA, (int) pCA.z);
                    lines.add(line);
                }
            }
        }

        return lines;
    }

    /**
     * <img src="./../doc_files/subCont.png"/>
     *
     * @param dt triangulation to get isoLines
     * @param elevatedStep step of isoLine
     * @return closed isoLines
     */
    public static ArrayList<Contour> contours(Delaunay_Triangulation dt, int elevatedStep) {

        //get connected lines
        ArrayList<Contour> lines = DT.connectedLines(dt, elevatedStep);
        DT.smooth(lines, 2);
        DT.smoothAngle(lines, 150, 210, 15);

        System.out.printf("[%s] start elevation [%s], triangles [%s]%n", "contours", elevatedStep, dt.trianglesSize());
        long start = System.currentTimeMillis();

        //region grab self closed lines
        ArrayList<Contour> selfClosed = new ArrayList<Contour>();
        for (int i = lines.size() - 1; i >= 0; i--) {
            Contour contour = lines.get(i);
            if (contour.isClosed()) {
                contour.inside = true;
                selfClosed.add(contour);
                lines.remove(i);
            }

        }
        //endregion

        //region border points
        ArrayList<Triangle_dt> convexHull = dt.convexHull();
        LinkedHashSet<Point_dt> pOrderSet = new LinkedHashSet<Point_dt>(convexHull.size() * 2);
        for (Triangle_dt t : convexHull) {
            Point_dt[] divided = UtPoint.divide(t.A, t.B, elevatedStep);

            pOrderSet.add(t.A);
            if (t.A.z > t.B.z) {//reversed way
                for (int i = divided.length - 1; i >= 0; i--) {
                    Point_dt p = divided[i];
                    pOrderSet.add(p);
                }
            } else {
                Collections.addAll(pOrderSet, divided);
            }
            pOrderSet.add(t.B);
        }

        Point_dt[] pArray = new Point_dt[pOrderSet.size()];
        Map<Point_dt, Integer> pIndex = new HashMap<Point_dt, Integer>(pOrderSet.size());
        int index = 0;
        for (Iterator<Point_dt> iterator = pOrderSet.iterator(); iterator.hasNext(); index++) {
            Point_dt p = iterator.next();
            pArray[index] = p;
            pIndex.put(p, index);
        }
        //endregion border points

        //region grab contourEnds
        HashMap<Point_dt, Contour> contourEnds = new HashMap<Point_dt, Contour>(lines.size());
        for (Contour c : lines) {
            Point_dt a = c.getA(), b = c.getB();
            if (pIndex.containsKey(a) && pIndex.containsKey(b)) {
                if (pIndex.get(a) > pIndex.get(b)) {
                    Collections.reverse(c.points);
                }
                contourEnds.put(c.getA(), c);
            }
        }
        System.out.printf("[%s] not closed contours %d%n", "contours", lines.size() - contourEnds.size());
        //endregion

        lines.clear();
        lines.addAll(selfClosed);

        //region middle contour
        int middleIndex = -1;
        for (int i = 0; i < pArray.length; i++) {
            Point_dt pI = pArray[i];
            if (!contourEnds.containsKey(pI)) {
                continue;//ignore
            }
            Contour openC = new Contour(contourEnds.get(pI));
            Collections.reverse(openC.points);
            openC.level = openC.level + elevatedStep;

            for (int j = i - 1; j >= 0; j--) {
                openC.extendB(pArray[j]);
            }

            while (!openC.getA().equals(pArray[i])) {
                i++;
            }
            middleIndex = i;

            for (int j = i + 1; j < pArray.length; j++) {
                Point_dt pJ = pArray[j];
                if (!contourEnds.containsKey(pJ)) {
                    openC.extendA(pJ);
                } else {
                    Contour c = contourEnds.get(pJ);
                    Point_dt p = null;
                    for (Point_dt point : c.points) {
                        p = point;
                        openC.extendA(p);
                    }
                    j = pIndex.get(p);
                }
            }
            openC.extendA(pArray[0]);
            lines.add(openC);
            break;
        }
        //endregion middle contour

        //region grab all lines
        for (int i = 0; i < pArray.length; i++) {
            Point_dt pI = pArray[i];
            if (!contourEnds.containsKey(pI)) {
                continue;//ignore
            }
            Contour openC = contourEnds.get(pI);
            openC.right = (middleIndex > i);

            for (int j = i + 1; j < pArray.length && !openC.isClosed(); j++) {
                Point_dt pJ = pArray[j];
                if (!contourEnds.containsKey(pJ)) {
                    openC.extendA(pJ);
                } else {
                    Contour c = contourEnds.get(pJ);
                    Point_dt p = null;
                    for (Point_dt point : c.points) {
                        p = point;
                        openC.extendA(p);
                    }
                    j = pIndex.get(p);
                }
            }
            lines.add(openC);
        }
        //endregion

        DT.setHoles(lines);
        for (Contour line : lines) {
            if (line.right) {
                line.level = line.level + elevatedStep;
            }
        }
//        desc(lines);
        System.out.printf("[%s] finish in [%d mSec], contours [%d]%n", "contours", (System.currentTimeMillis() - start), lines.size());
        return lines;
    }

    public static ArrayList<Contour> connectedLines(Delaunay_Triangulation dt, int elevatedStep) {
        System.out.printf("[%s] start elevation [%s], triangles [%s]%n", "connectedLines", elevatedStep, dt.trianglesSize());
        long start = System.currentTimeMillis();

        //to avert vertices connection as isoline
        for (Iterator<Point_dt> verticesIterator = dt.verticesIterator(); verticesIterator.hasNext();) {
            Point_dt next = verticesIterator.next();
            next.z = ((int) next.z) % elevatedStep == 0 ? next.z + 1 : next.z;
        }

        //elevation, point, lines at this point
        HashMap<Integer, HashMap<Point_dt, Set<Izoline>>> lineContainer
                = new HashMap<Integer, HashMap<Point_dt, Set<Izoline>>>();

        for (Triangle_dt t : dt.get_triangles()) {
            if (t == null || t.isHalfplane()) {
                continue;
            }
            Set<Izoline> lines = getIzolines(t, elevatedStep);
            for (Izoline line : lines) {
                int elevation = line.elevation;

                if (!lineContainer.containsKey(elevation)) {
                    lineContainer.put(elevation, new HashMap<Point_dt, Set<Izoline>>());
                }

                Map<Point_dt, Set<Izoline>> pointMap = lineContainer.get(elevation);
                Point_dt a = line.A, b = line.B;

                if (!pointMap.containsKey(a)) {
                    pointMap.put(a, new HashSet<Izoline>(2));
                }
                if (!pointMap.containsKey(b)) {
                    pointMap.put(b, new HashSet<Izoline>(2));
                }

                pointMap.get(a).add(line);
                pointMap.get(b).add(line);
            }
        }
        ArrayList<Contour> contours = new ArrayList<Contour>();
        for (HashMap<Point_dt, Set<Izoline>> pointMap : lineContainer.values()) {
            while (pointMap.size() > 0) {
                Map.Entry<Point_dt, Set<Izoline>> next = pointMap.entrySet().iterator().next();
                Set<Izoline> lines = next.getValue();
                while (lines.size() > 0) {
                    Izoline l = lines.iterator().next();

                    pointMap.get(l.A).remove(l);
                    if (pointMap.get(l.A).size() == 0) {
                        pointMap.remove(l.A);
                    }
                    pointMap.get(l.B).remove(l);
                    if (pointMap.get(l.B).size() == 0) {
                        pointMap.remove(l.B);
                    }

                    Contour c = new Contour(l.elevation);
                    c.points.addFirst(l.A);
                    c.points.addLast(l.B);

                    boolean finished = false;
                    while (!finished) {
                        if (pointMap.containsKey(c.getA())) {
                            l = pointMap.get(c.getA()).iterator().next();
                            c.extendTo(l);

                            pointMap.get(l.A).remove(l);
                            if (pointMap.get(l.A).size() == 0) {
                                pointMap.remove(l.A);
                            }
                            pointMap.get(l.B).remove(l);
                            if (pointMap.get(l.B).size() == 0) {
                                pointMap.remove(l.B);
                            }

                        } else if (pointMap.containsKey(c.getB())) {
                            l = pointMap.get(c.getB()).iterator().next();
                            c.extendTo(l);

                            pointMap.get(l.A).remove(l);
                            if (pointMap.get(l.A).size() == 0) {
                                pointMap.remove(l.A);
                            }
                            pointMap.get(l.B).remove(l);
                            if (pointMap.get(l.B).size() == 0) {
                                pointMap.remove(l.B);
                            }
                        } else {
                            finished = true;
                        }
                    }

                    contours.add(c);
                }
                pointMap.remove(next.getKey());
            }
        }
        System.out.printf("[%s] finish in [%d mSec]%n", "connectedLines", (System.currentTimeMillis() - start));
        return contours;
    }

    /**
     * Small contours first
     *
     * @param contours for sorting
     */
    public static void sort(Contour... contours) {
        Arrays.sort(contours, new Comparator<Contour>() {
            public int compare(Contour ca, Contour cb) {
                double caPolygonArea = ca.getArea();
                double cbPolygonArea = cb.getArea();
                if (caPolygonArea > cbPolygonArea) {
                    return 1;
                }
                if (caPolygonArea < cbPolygonArea) {
                    return -1;
                }
                return 0;
            }
        });
    }

    /**
     * Large contours first
     *
     * @param contours for sorting
     */
    public static void desc(Contour... contours) {
        Arrays.sort(contours, new Comparator<Contour>() {
            public int compare(Contour ca, Contour cb) {
                double caPolygonArea = ca.getArea();
                double cbPolygonArea = cb.getArea();
                if (caPolygonArea > cbPolygonArea) {
                    return -1;
                }
                if (caPolygonArea < cbPolygonArea) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * smoothing polyline edges with cutting its edges by constant -20%
     * <p>
     * and connecting them with new line for <code>loop</code> times</p>
     *
     * @param contours polyline to smooth
     * @param loop for times, cutting for 5 times is good for now
     */
    public static void smooth(ArrayList<Contour> contours, int loop) {
        for (int k = 0; k < loop; k++) {
            for (Contour c : contours) {
                for (int i = 1; i < c.points.size() - 1; i++) {
                    Point_dt prev = c.points.get(i - 1);
                    Point_dt curr = c.points.get(i);
                    Point_dt next = c.points.get(i + 1);

                    Point_dt calc1 = UtPoint.segment(curr, prev, 20, 100);
                    Point_dt calc2 = UtPoint.segment(curr, next, 20, 100);

                    curr.x = calc1.x;
                    curr.y = calc1.y;

                    c.points.add(++i, calc2);
                }
            }
        }
    }

    public static void smoothAngle(ArrayList<Contour> contours, int from, int to, int perCent) {
        int HUNDRED_PERCENT = 100;
        for (Contour c : contours) {
            if (c.points.size() < 3) {
                continue;
            }
            ListIterator<Point_dt> itr = c.points.listIterator();
            Point_dt prev = itr.next();
            Point_dt curr = itr.next();
            Point_dt next;

            while (itr.hasNext()) {
                next = itr.next();

                double degree = UtPoint.angleBetween(curr, prev, next);
                if (degree > from && degree < to) {
                    prev = curr;
                    curr = next;
                } else {
                    Point_dt calc1 = UtPoint.segment(curr, prev, perCent, HUNDRED_PERCENT);
                    curr.x = calc1.x;
                    curr.y = calc1.y;

                    Point_dt calc2 = UtPoint.segment(curr, next, perCent, HUNDRED_PERCENT);
                    itr.previous();
                    itr.add(calc2);
                    itr.previous();
                }
            }

            if (c.isClosed()) {
                Point_dt second = c.points.get(1);

                itr.add(new Point_dt(second.x, second.y));
                itr.previous();
                while (itr.hasNext()) {
                    next = itr.next();

                    double degree = UtPoint.angleBetween(curr, prev, next);
                    if (degree > from && degree < to) {
                        prev = curr;
                        curr = next;
                    } else {
                        Point_dt calc1 = UtPoint.segment(curr, prev, perCent, HUNDRED_PERCENT);
                        curr.x = calc1.x;
                        curr.y = calc1.y;

                        Point_dt calc2 = UtPoint.segment(curr, next, perCent, HUNDRED_PERCENT);
                        itr.previous();
                        itr.add(calc2);
                        itr.previous();
                    }
                }
                c.points.removeFirst();
            }
        }
    }

    public static void setHoles(ArrayList<Contour> contours) {

        System.out.printf("[%s] start, contours [%d]%n", "setHoles", contours.size());
        long start = System.currentTimeMillis();

        //sort, small contours first
        sort(contours);

        //next large contour stands for parent, if that contains this
        for (int i = 0; i < contours.size() - 1; i++) {
            Contour small = contours.get(i);
            if (!small.inside) {
                break;
            }

            for (int j = i + 1; j < contours.size(); j++) {
                Contour large = contours.get(j);
                double x = small.getA().x;
                double y = small.getA().y;

                if (large.cachedArea().contains(x, y)) {
                    if (small.level == large.level) {
                        large.holes.addAll(small.holes);
                        contours.remove(i);
                        i--;
                    } else {
                        large.holes.add(small);
                        small.right = large.right;
                    }
                    break;
                }
            }
        }
        System.out.printf("[%s] finish in [%d mSec] contour [%d]%n", "setHoles", (System.currentTimeMillis() - start), contours.size());
    }

    /**
     * Small contours first
     *
     * @param contours for sorting
     */
    public static void sort(ArrayList<Contour> contours) {
        Collections.sort(contours, new Comparator<Contour>() {
            public int compare(Contour ca, Contour cb) {
                if (ca.inside && !cb.inside) {
                    return -1;
                }
                if (!ca.inside && cb.inside) {
                    return 1;
                }
                double caPolygonArea = ca.getArea();
                double cbPolygonArea = cb.getArea();
                if (caPolygonArea < cbPolygonArea) {
                    return -1;
                }
                if (caPolygonArea > cbPolygonArea) {
                    return 1;
                }
                return 0;
            }
        });
    }

    /**
     * Large contours first
     *
     * @param contours for sorting
     */
    public static void desc(ArrayList<Contour> contours) {
        Collections.sort(contours, new Comparator<Contour>() {
            public int compare(Contour ca, Contour cb) {
                if (!ca.inside && cb.inside) {
                    return -1;
                }
                if (ca.inside && !cb.inside) {
                    return 1;
                }
                double caPolygonArea = ca.getArea();
                double cbPolygonArea = cb.getArea();
                if (caPolygonArea > cbPolygonArea) {
                    return -1;
                }
                if (caPolygonArea < cbPolygonArea) {
                    return 1;
                }
                return 0;
            }
        });
    }
}
