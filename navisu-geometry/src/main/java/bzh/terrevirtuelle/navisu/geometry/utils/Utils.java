/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.utils;

import bzh.terrevirtuelle.navisu.util.Pair;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.LongStream;

/**
 * NaVisu
 *
 * @date 19 juin 2015
 * @author Serge Morvan
 */
public class Utils {

    public static double bernstein(int n, int i, double u) {
        double ui;
        double un = 1 - u;
        ui = Math.pow(u, i);
        un = Math.pow(un, n - i);
        return cni(n, i) * ui * un;
    }

    public static List<Pair<Double, Double>> computeBernstein(int n, int i, double inc) {
        List<Pair<Double, Double>> data = new ArrayList<>();
        for (double u = 0; u <= 1; u += inc) {
            data.add(new Pair(u, bernstein(n, i, u)));
        }
        return data;
    }

    public static long cni(int n, int i) {
        return factorial(n) / (factorial(i) * factorial(n - i));
    }

    public static long factorial(int n) {
        if (n > 20) {
            throw new IllegalArgumentException(n + " is out of range");
        }
        return LongStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
    }

}
