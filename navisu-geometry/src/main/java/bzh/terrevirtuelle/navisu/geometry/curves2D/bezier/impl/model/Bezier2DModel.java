/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.impl.model;

import bzh.terrevirtuelle.navisu.geometry.utils.Utils;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.ejml.data.DenseMatrix64F;
import org.ejml.factory.LinearSolverFactory;
import org.ejml.interfaces.linsol.LinearSolver;

/**
 * NaVisu
 *
 * @date 19 juin 2015
 * @author Serge Morvan
 */
public class Bezier2DModel {

    private int n = 0;
    private List<Pair<Double, Double>> si;
    private List<Long> coeffs;

    public Bezier2DModel() {

    }

    @SuppressWarnings("unchecked")
    public Bezier2DModel(List<Pair<Double, Double>> si) {
        coeffs = new ArrayList();
        this.n = si.size() - 1;
        this.si = si;

        for (int i = 0; i <= n; i++) {
            coeffs.add(Utils.cni(n, i));
        }
    }
@SuppressWarnings("unchecked")
    public List<Pair<Double, Double>> compute(double inc) {
        double x = 0;
        double y = 0;
        List<Pair<Double, Double>> result = new ArrayList<>();
        for (double u = 0; u < 1; u += inc) {
            for (int i = 0; i <= n; i++) {
                x += Utils.bernstein(n, i, u) * si.get(i).getX();
                y += Utils.bernstein(n, i, u) * si.get(i).getY();
            }
            result.add(new Pair(x, y));
            x = y = 0;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Pair<Double, Double>> tangent(List<Pair<Double, Double>> si) {
        List<Pair<Double, Double>> tmp = new ArrayList<>();
        for (int i = 0; i < si.size() - 1; i++) {
            tmp.add(new Pair((si.get(i + 1).getX() - si.get(i).getX()),
                    (si.get(i + 1).getY() - si.get(i).getY())));
        }
        return tmp;
    }

    @SuppressWarnings("unchecked")
    public List<Pair<Double, Double>> leastSquare(List<Pair<Double, Double>> data, int degree) {

        double[][] ta = new double[degree + 1][degree + 1];
        double[][] tb = new double[degree + 1][1];
        double[][] ts = new double[degree + 1][1];
        List<Pair<Double, Double>> filtered = filter(data);
        double m = filtered.size() - 1;

        for (int k = 0; k <= degree; k++) {
            for (int i = 0; i <= degree; i++) {
                for (int j = 0; j <= m; j++) {
                    ta[k][i] += Utils.bernstein(degree, i, j / m) * Utils.bernstein(degree, k, j / m);
                }
            }
            for (int j = 0; j <= m; j++) {
                tb[k][0] += filtered.get(j).getX() * Utils.bernstein(degree, k, j / m);
            }
        }

        DenseMatrix64F a = new DenseMatrix64F(ta);
        DenseMatrix64F b = new DenseMatrix64F(tb);
        DenseMatrix64F s = new DenseMatrix64F(ts);
        LinearSolver<DenseMatrix64F> solver = LinearSolverFactory.qr(a.numRows, a.numCols);
        solver.setA(a);
        solver.solve(b, s);

        List<Double> x = new ArrayList<>();
        for (Double tmp : s.getData()) {
            x.add(tmp);
        }
        ta = new double[degree + 1][degree + 1];
        tb = new double[degree + 1][1];
        ts = new double[degree + 1][1];

        for (int k = 0; k <= degree; k++) {
            for (int i = 0; i <= degree; i++) {
                for (int j = 0; j <= m; j++) {
                    ta[k][i] += Utils.bernstein(degree, i, j / m) * Utils.bernstein(degree, k, j / m);
                }
            }
            for (int j = 0; j <= m; j++) {
                tb[k][0] += filtered.get(j).getY() * Utils.bernstein(degree, k, j / m);
            }
        }
        a = new DenseMatrix64F(ta);
        b = new DenseMatrix64F(tb);
        s = new DenseMatrix64F(ts);
        solver = LinearSolverFactory.qr(a.numRows, a.numCols);
        solver.setA(a);
        solver.solve(b, s);

        List<Double> y = new ArrayList<>();
        for (Double tmp : s.getData()) {
            y.add(tmp);
        }

        List<Pair<Double, Double>> sii = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            sii.add(new Pair(x.get(i), y.get(i)));
        }
        sii.remove(0);
        sii.set(0, data.get(0));
        sii.remove(degree - 1);
        sii.add(data.get(data.size() - 1));
        return sii;
    }

    private List<Pair<Double, Double>> filter(List<Pair<Double, Double>> data) {
        List<Pair<Double, Double>> dataFiltered = new ArrayList<>();
        int size = data.size();
        Pair<Double, Double> tmp = data.get(0);
        dataFiltered.add(tmp);
        for (int i = 1; i < size; i++) {
            if (tmp != data.get(i)) {
                dataFiltered.add(data.get(i));
                tmp = data.get(i);
            }
        }
        return dataFiltered;
    }

    public List<Long> getCoeffs() {
        return coeffs;
    }

    public int getDegree() {
        return n;
    }

    public void setSi(List<Pair<Double, Double>> si) {
        this.si = si;
    }

    public List<Pair<Double, Double>> getSi() {
        return si;
    }

    @Override
    public String toString() {
        return "Bezier2D{" + "n=" + n + ", si=" + si + ", coeffs=" + coeffs + '}';
    }
}
