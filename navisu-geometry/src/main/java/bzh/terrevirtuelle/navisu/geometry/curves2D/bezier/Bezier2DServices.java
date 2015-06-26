/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.curves2D.bezier;

import bzh.terrevirtuelle.navisu.util.Pair;
import java.util.List;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 20 juin 2015
 * @author Serge Morvan
 */
public interface Bezier2DServices
        extends ComponentService {

    List<Pair<Double, Double>> compute(List<Pair<Double, Double>> si);

    List<Pair<Double, Double>> compute(List<Pair<Double, Double>> si, double inc);

    List<Pair<Double, Double>> tangent(List<Pair<Double, Double>> si);

    List<Pair<Double, Double>> tangentCompute(List<Pair<Double, Double>> si, double inc);

    List<Pair<Double, Double>> leastSquare(List<Pair<Double, Double>> data, int degree);

    List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data);

    List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, double inc);

    List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, int degree);

    List<Pair<Double, Double>> leastSquareCompute(List<Pair<Double, Double>> data, double inc, int degree);

    void toKML(String filename, List<Pair<Double, Double>> data);

    void toKML(String path, String filename, List<Pair<Double, Double>> data, String color, String width);

    List<Pair<Double, Double>> readCsv(String path, String filename);
}
