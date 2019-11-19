/** *****************************************************************************
 * Copyright 2010 Simon Mieth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************** */
package bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl;

import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.BSplineComponent;
import bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.BSplineComponentServices;

public class BSplineComponentImpl
        implements BSplineComponent, BSplineComponentServices, ComponentState {

    @Override
    public BSpline create(Point3D[] controlPoints, double[] knots, double[] weights, int degree) {
        BSpline bSpline = new BSpline(controlPoints, knots, weights, degree);
        return bSpline;
    }

    @Override
    public BSpline create(List<Point3D> controlPoints, double[] knots, double[] weights, int degree) {
        BSpline bSpline = new BSpline(controlPoints, knots, weights, degree);
        return bSpline;
    }

    @Override
    public List<Point3D> compute(BSpline bSpline, double inc) {
        Point3D[] controlPoints = bSpline.getControlPoints();
        Point3D p = controlPoints[0];
        Point3D stop = controlPoints[controlPoints.length - 1];
        double x = 0.0;
        List<Point3D> pts = new ArrayList<>();
        if (inc != 0.0) {
            while (!p.equals(stop)) {
                p = bSpline.getPointAt(x);
                // System.out.println(p);
                pts.add(p);
                x += inc;
            }
        }
        return pts;
    }

    @Override
    public Point3D getPointAt(BSpline bSpline, int i, double u) {
        Point3D result = null;
        if (bSpline != null) {
            result = bSpline.getPointAt(i, u);
        }
        return result;
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }
}
