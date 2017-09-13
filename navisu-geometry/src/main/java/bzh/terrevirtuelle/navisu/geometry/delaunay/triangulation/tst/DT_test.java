package bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.tst;

import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Contour;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Delaunay_Triangulation;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Izoline;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Point_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.Triangle_dt;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util.DT;
import bzh.terrevirtuelle.navisu.geometry.delaunay.triangulation.util.UtPoint;
import org.junit.Test;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * difference between LineString, LinearRing, Polygon, and hand made method
 * intersection results
 */
public class DT_test {

    public static void main(String[] args) {
        new DT_test();
    }

    public DT_test() {
        t1();
        t3();
        t4();
        t5();
        try {
            test6();
        } catch (Exception ex) {
            Logger.getLogger(DT_test.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            test7();
        } catch (Exception ex) {
            Logger.getLogger(DT_test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void t1() {
        Point_dt p0 = new Point_dt(20.0, 30, 6);
        Point_dt p1 = new Point_dt(20.0, 10, 50);
        Point_dt p3 = new Point_dt(20.0, 20, 50);

        System.out.println(p3.pointLineTest(p0, p1));
    }

    @Test
    public void t3() {
        Delaunay_Triangulation dt = new Delaunay_Triangulation();
        dt.insertPoint(new Point_dt(20.0, 30, 6));
        dt.insertPoint(new Point_dt(20.0, 10, 11));
        dt.insertPoint(new Point_dt(40.0, 20, 25));

        ArrayList<Triangle_dt> convexHull = dt.convexHull();

        Triangle_dt t = dt.get_triangles().get(0);
        Set<Izoline> izolines = DT.getIzolines(t, 2);

        for (Izoline l : izolines) {
            if (l.A.pointLineTest(t.A, t.B) != Point_dt.ONSEGMENT && l.A.pointLineTest(t.B, t.C) != Point_dt.ONSEGMENT
                    && l.A.pointLineTest(t.C, t.A) != Point_dt.ONSEGMENT) {
                System.out.println("error " + l.A);

            }
            if (l.B.pointLineTest(t.A, t.B) != Point_dt.ONSEGMENT && l.B.pointLineTest(t.B, t.C) != Point_dt.ONSEGMENT
                    && l.B.pointLineTest(t.C, t.A) != Point_dt.ONSEGMENT) {
                System.out.println("error " + l.B);
            }
        }

    }

    @Test
    public void t4() {
        Point_dt p0 = new Point_dt(20.0, 30, 6);
        Point_dt p1 = new Point_dt(21.0, 10, 50);
        Point_dt p3 = new Point_dt(20.0, 20, 50);

        Point_dt[] divide = UtPoint.divide(p0, p1, 2);

        for (Point_dt p : divide) {
            if (p.pointLineTest(p0, p1) != Point_dt.ONSEGMENT) {
                System.out.println("error " + p);
            }
        }

    }

    @Test
    public void t5() {
        Point_dt p0 = new Point_dt(20.0, 30, 6);
        Point_dt p1 = new Point_dt(21.0, 10, 50);

        Point_dt[] divide = UtPoint.divide(p0, p1, 2);

        Point_dt p = UtPoint.segment(p0, p1, 4, 44);
        if (p.pointLineTest(p1, p0) != Point_dt.ONSEGMENT) {
            System.out.println("error " + p);
        }

    }

    @Test
    public void test6() throws Exception {
        long l = System.currentTimeMillis();
        int count = 10000;
        final Random rnd = new Random(948731674);

        Delaunay_Triangulation dt = new Delaunay_Triangulation();

        int xMax = 700;
        final int yMax = 700;
        final int zMax = 40;
        for (int i = 0; i < count; i++) {
            double x = rnd.nextInt(xMax);
            double y = rnd.nextInt(yMax);
            double z = rnd.nextInt(zMax);

            Point_dt point_dt = new Point_dt(x, y, z);
            dt.insertPoint(point_dt);
        }

        double avgEdgeLength = dt.avgEdgeLength();
        dt.divideTillEpsilon(avgEdgeLength);
        ArrayList<Contour> contours = DT.connectedLines(dt, 5);
        System.out.println("triangles : " + dt.toString());
        for(Triangle_dt t : dt.get_triangles()){
            System.out.println("t : "+ t.A +" "+t.B+" "+t.C);
        }
        System.out.println("contours size = " + contours.size());
        System.out.println("took: " + (System.currentTimeMillis() - l));
    }

    @Test
    public void test7() throws Exception {
        Point_dt p0 = new Point_dt(0, 0, 6);
        Point_dt p1 = new Point_dt(0, 100, 50);
        Point_dt p2 = new Point_dt(100, 0, 50);
        double v = UtPoint.angleBetween(p1, p0, p2);
        System.out.println(v);

    }
}
