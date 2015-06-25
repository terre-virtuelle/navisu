/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.utils;

import bzh.terrevirtuelle.navisu.util.Pair;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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

    public static void toKML(String filename, List<Pair<Double, Double>> data) {
        toKML("data/kml/", filename, data, "501400FF", "2");
    }

    public static void toKML(String path, String filename, List<Pair<Double, Double>> data, String color, String width) {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        lines.add("<kml xmlns=\"http://earth.google.com/kml/2.0\">");
        lines.add("<Folder>");
        lines.add("<Placemark>");
        lines.add("<name>" + LocalDate.now() + "</name>");
        lines.add("<Style>");
        lines.add("<LineStyle>");
        lines.add("<color>" + color + "</color>");
        lines.add("<width>" + width + "</width>");
        lines.add("</LineStyle>");
        lines.add("</Style>");
        lines.add("<LineString>");
        lines.add("<coordinates>");
        data.stream().forEach((d) -> {
            lines.add(d.getY().toString() + "," + d.getX().toString() + "," + "1000");
        });
        lines.add("</coordinates>");
        lines.add("</LineString>");
        lines.add("</Placemark>");
        lines.add("</Folder>");
        lines.add("</kml>");
        Path myText_path = Paths.get(path, filename);
        Charset charset = Charset.forName("UTF-8");
        try {
            Files.write(myText_path, lines, charset);
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Pair<Double, Double>> readCsv(String path, String filename) {
        List<String> lines = null;
        List<Pair<Double, Double>> data = new ArrayList<>();

        try {
            lines = Files.readAllLines(Paths.get(path, filename));
        } catch (IOException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String[] elem = lines.get(0).split(";");
        for (int i = 0; i < elem.length - 1; i += 2) {
            data.add(new Pair(Double.parseDouble(elem[i]), Double.parseDouble(elem[i + 1])));
        }
        return data;
    }
}
