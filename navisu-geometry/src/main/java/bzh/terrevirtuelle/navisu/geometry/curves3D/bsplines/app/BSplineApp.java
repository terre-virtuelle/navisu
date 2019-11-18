/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.app;

import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl.BSpline;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl.BSplineComponentImpl;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;

/**
 *
 * @author serge
 * @date Mar 15, 2019
 */
public class BSplineApp {

    public static final double DELTA = 1.0E-5;

    public static void main(String[] args) {
        BSplineApp nurbsApp = new BSplineApp();
        nurbsApp.pointAt();
    }

    public void pointAt() {
        Point3D[] points = new Point3D[]{
            new Point3D(0, 0, 0), 
            new Point3D(1.0, 1.0, 10.0),
            new Point3D(3.0, 2.0, 0), 
            new Point3D(4.0, 1.0, 0),
            new Point3D(5.0, -1.0, 5)
        };
        double[] knots = new double[]{0.0, 0.0, 0.0, 1.0, 2.0, 3.0, 3.0, 3.0};
        double[] w = new double[]{1.0, 4.0, 1.0, 1.0, 1.0};
        BSpline n = new BSpline(points, knots, w, 2);
       // int index = n.findSpawnIndex(1.0);

       // Point3D p = n.getPointAt(index, 1.0);
        
      //  System.out.println("p : " + p);
       double inc = .1;
       double x=0.0;
        for(int i = 0; i < 1000; i++){
            System.out.println(n.getPointAt(x));
            x+=inc;
        }
    }

    public void baseFunctions1() {
        Point3D[] points = new Point3D[]{};
        double[] knots = new double[]{0, 0, 0, 1, 2, 3, 4, 4, 5, 5};
        double[] w = new double[]{};
        BSpline n = new BSpline(points, knots, w, 2);
        double[] bf = n.getBasicFunctions(4, 2.5);

        /*  assertEquals(3, bf.length);
        assertEquals(1.0 / 8.0, bf[0], DELTA);
        assertEquals(6.0 / 8.0, bf[1], DELTA);
        assertEquals(1.0 / 8.0, bf[2], DELTA);
         */
    }

    public void baseFunctions2() {
        Point3D[] points = new Point3D[]{
            new Point3D(0, 0, 0), new Point3D(1, 1, 0), new Point3D(3, 2, 0),
            new Point3D(4, 1, 0), new Point3D(5, -1, 0)
        };
        double[] knots = new double[]{0, 0, 0, 1, 2, 3, 4, 4, 5, 5, 5};
        double[] w = new double[]{};
        BSpline n = new BSpline(points, knots, w, 2);
        int index = n.findSpawnIndex(2.5);

        double[] bf = n.getBasicFunctions(index, 2.5);

        /*  assertEquals(3, bf.length);
        assertEquals(1.0 / 8.0, bf[0], DELTA);
        assertEquals(6.0 / 8.0, bf[1], DELTA);
        assertEquals(1.0 / 8.0, bf[2], DELTA);
         */
    }
}
