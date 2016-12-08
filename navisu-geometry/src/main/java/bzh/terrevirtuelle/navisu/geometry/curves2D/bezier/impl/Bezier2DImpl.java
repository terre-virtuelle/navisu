/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.impl;

import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.Bezier2D;
import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.Bezier2DServices;
import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.impl.model.Bezier2DModel;
import bzh.terrevirtuelle.navisu.geometry.utils.Utils;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.util.ArrayList;
import java.util.List;
import org.capcaval.c3.component.ComponentState;

/**
 * NaVisu
 *
 * @date 20 juin 2015
 * @author Serge Morvan
 */
public class Bezier2DImpl
        implements Bezier2D, Bezier2DServices, ComponentState {

    Bezier2DModel bezier;

    /**
     *
     */
    @Override
    public void componentInitiated() {
        bezier = new Bezier2DModel();
    }

    /**
     *
     * @param si
     * @return
     */
    @Override
    public List<Pair<Double, Double>> compute(List<Pair<Double, Double>> si) {
        return compute(si, 0.1);
    }

    /**
     *
     * @param si
     * @param inc
     * @return
     */
    @Override
    public List<Pair<Double, Double>> compute(List<Pair<Double, Double>> si, double inc) {
        Bezier2DModel tmp = new Bezier2DModel(si);
        return tmp.compute(inc);
    }

    @Override
    public List<Pair<Double, Double>> tangent(List<Pair<Double, Double>> si) {
        return bezier.tangent(si);
    }

    @Override
    public List<Pair<Double, Double>> tangentCompute(List<Pair<Double, Double>> si, double inc) {
        List<Pair<Double, Double>> tgSi = bezier.tangent(si);
        Bezier2DModel tmp = new Bezier2DModel(tgSi);
        return tmp.compute(inc);
    }

    /**
     *
     * @param si : a characteristic Bezier polygon
     * @param inc
     * @param scale :for tg vector
     * @param headings
     * @return a list of tangent vectors
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Pair<Pair<Double, Double>, Pair<Double, Double>>>
            vectorTangentCompute(List<Pair<Double, Double>> si, double inc, double scale,
                    List<Pair<Pair<Double, Double>, Double>> headings) {
        List<Pair<Pair<Double, Double>, Pair<Double, Double>>> result = new ArrayList<>();
        Bezier2DModel bez = new Bezier2DModel(si);
        List<Pair<Double, Double>> curvePts = bez.compute(inc);
        List<Pair<Double, Double>> tgSi = bez.tangent(si);
        bez = new Bezier2DModel(tgSi);
        List<Pair<Double, Double>> tgPts = bez.compute(inc);
        for (int i = 0; i < curvePts.size(); i++) {
            double u = curvePts.get(i).getX() + tgPts.get(i).getX() * scale;
            double v = curvePts.get(i).getY() + tgPts.get(i).getY() * scale;
            Pair<Double, Double> pu = new Pair(u, v);
            result.add(new Pair(curvePts.get(i), pu));
            if (headings != null) {
                double uu = pu.getX() - curvePts.get(i).getX();
                double vv = pu.getY() - curvePts.get(i).getY();
                double heading = 90 - Math.toDegrees(Math.atan2(uu, vv));
                if (heading < 0) {
                    heading += 360;
                }
                headings.add(new Pair(curvePts.get(i), heading));
            }
        }
        return result;
    }

    /**
     *
     * @param data
     * @return
     */
    @Override
    public List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data) {
        return leastSquareCompute(data, 0.1);
    }

    /**
     *
     * @param data
     * @param inc
     * @return
     */
    @Override
    public List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, double inc) {
        return leastSquareCompute(data, 0.1, 5);
    }

    /**
     *
     * @param data
     * @param degree
     * @return
     */
    @Override
    public List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, int degree) {
        return leastSquareCompute(data, 0.1, degree);
    }

    /**
     *
     * @param data
     * @param inc
     * @param degree
     * @return
     */
    @Override
    public List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, double inc, int degree) {
        List<Pair<Double, Double>> filtered = filter(data);
        List<Pair<Double, Double>> smoothSi = bezier.leastSquare(filtered, degree);
        Bezier2DModel tmp = new Bezier2DModel(smoothSi);
        return tmp.compute(inc);
    }

    @Override
    public List<Pair<Double, Double>> leastSquare(List<Pair<Double, Double>> data, int degree) {
        List<Pair<Double, Double>> filtered = filter(data);
        List<Pair<Double, Double>> smoothSi = bezier.leastSquare(filtered, degree);
        return smoothSi;
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

    @Override
    public void toKML(String filename, List<Pair<Double, Double>> data) {
        toKML("data/kml", filename, data, "501400FF", "2");
    }

    @Override
    public void toKML(String path, String filename, List<Pair<Double, Double>> data, String color, String width) {
        Utils.toKML(path, filename, data, color, width);
    }

    @Override
    public void toKML2(String path, String filename, List<Pair<Pair<Double, Double>, Pair<Double, Double>>> data,
            String color, String width) {
        Utils.toKML2(path, filename, data, color, width);
    }

    @Override
    public List<Pair<Double, Double>> readCsv(String path, String filename) {
        return Utils.readCsv(path, filename);
    }

    /**
     *
     */
    @Override
    public void componentStarted() {
    }

    /**
     *
     */
    @Override
    public void componentStopped() {
    }

}
