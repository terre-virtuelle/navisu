/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines;

import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl.BSpline;
import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * @date 13 sept 2017
 * @author Serge Morvan
 */
public interface BSplineComponentServices
        extends ComponentService {

    BSpline create(Point3D[] controlPoints, double[] knots, double[] weights, int degree);

    List<Point3D> compute(BSpline bSpline, double inc);
}
