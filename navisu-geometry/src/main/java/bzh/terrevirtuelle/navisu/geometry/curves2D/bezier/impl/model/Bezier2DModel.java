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

/**
 * NaVisu
 *
 * @date 19 juin 2015
 * @author Serge Morvan
 */
public class Bezier2DModel {

    private int n = 0;
    private List<Pair<Double, Double>> si;
    private List<Long> coeffs = new ArrayList();

    public Bezier2DModel() {

    }

    public Bezier2DModel(List<Pair<Double, Double>> si) {
        this.n = si.size() - 1;
        this.si = si;
        for (int i = 0; i <= n; i++) {
            coeffs.add(Utils.cni(n, i));
        }
    }

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

    public List<Long> getCoeffs() {
        return coeffs;
    }

    public int getN() {
        return n;
    }

    public List<Pair<Double, Double>> getSi() {
        return si;
    }

    @Override
    public String toString() {
        return "Bezier2D{" + "n=" + n + ", si=" + si + ", coeffs=" + coeffs + '}';
    }

}
