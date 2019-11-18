/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves3D.bsplines.impl;

import bzh.terrevirtuelle.navisu.geometry.objects3D.Point3D;

/**
 *
 * @author serge
 * @date Nov 18, 2019
 */
public class BSpline {

    protected Point3D[] controlPoints;
    protected double[] knots;
    protected double[] weights;
    protected int degree;
    protected boolean closed = false;

    public BSpline(Point3D[] controlPoints, double[] knots, double[] weights, int degree) {
        this.controlPoints = controlPoints;

        this.knots = knots;
        this.weights = weights;
        this.degree = degree;

        //some init stuff
        if (this.weights.length == 0) {
            this.weights = new double[this.controlPoints.length];
        }

        for (int i = 0; i < weights.length; i++) {
            if (weights[i] == 0.0) {
                weights[i] = 1.0;
            }
        }
    }

    public double[] getBasicFunctions(int i, double u) {
        double[] n = new double[degree + 1];
        n[0] = 1.0;

        double[] left = new double[degree + 1];
        double[] right = new double[degree + 1];

        for (int j = 1; j <= degree; j++) {
            left[j] = u - this.knots[(i + 1) - j];
            right[j] = this.knots[i + j] - u;

            double saved = 0.0;

            for (int r = 0; r < j; r++) {
                double t = n[r] / (right[r + 1] + left[j - r]);
                n[r] = saved + (right[r + 1] * t);
                saved = left[j - r] * t;
            }

            n[j] = saved;
        }

        return n;
    }

    public Point3D getPointAt(int i, double u) {
        Point3D p = new Point3D();
        double[] n = this.getBasicFunctions(i, u);

        double t = 0.0;

        for (int j = 0; j <= this.degree; j++) {
            int d = i - this.degree + j;
            double w = this.weights[d];

            p.setX(p.getX() + (n[j] * this.controlPoints[d].getX() * w));
            p.setY(p.getY() + (n[j] * this.controlPoints[d].getY() * w));
            p.setZ(p.getZ() + (n[j] * this.controlPoints[d].getZ() * w));
            t += (n[j] * w);
        }

        p.setX((p.getX() / t));
        p.setY(p.getY() / t);
        p.setZ(p.getZ() / t);

        return p;
    }

    public Point3D getPointAt(double u) {
        int interval = this.findSpawnIndex(u);

        return this.getPointAt(interval, u);
    }

    public int findSpawnIndex(double u) {
        if (u == this.knots[this.controlPoints.length + 1]) {
            return this.controlPoints.length;
        }

        int low = this.degree;
        int high = this.controlPoints.length + 1;
        int mid = (low + high) / 2;

        while ((u < this.knots[mid]) || (u >= this.knots[mid + 1])) {
            if (u < this.knots[mid]) {
                high = mid;
            } else {
                low = mid;
            }

            mid = (low + high) / 2;
        }

        return mid;
    }

    public Point3D[] getControlPoints() {
        return controlPoints;
    }

    public void setControlPoints(Point3D[] controlPoints) {
        this.controlPoints = controlPoints;
    }

    public double[] getKnots() {
        return knots;
    }

    public void setKnots(double[] knots) {
        this.knots = knots;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    
}
