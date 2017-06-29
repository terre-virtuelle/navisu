/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf.common;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import ucar.ma2.Array;

/**
 *
 * @author serge
 * @date Sep 8, 2016
 */
public class VectorField {

    private final double[] values;
    private final double[] directions;
    private final double minValue;
    private final double maxValue;
    private final double[] lat;
    private final double[] lon;
    private final double minLat;
    private final double maxLat;
    private final double minLon;
    private final double maxLon;
    private final double time;
    private final double height;
    private int index;

    public VectorField(double[] lat, double[] lon,
            double minLat, double maxLat, double minLon, double maxLon,
            Array u, Array v,
            int index, double height, double time) {
        this.lat = lat;
        this.lon = lon;
        this.minLat = minLat;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.maxLon = maxLon;
        this.height = height;
        this.time = time;

        values = new double[lat.length * lon.length];

        int l = 0;
        String file = "data.txt";
        double average = 0;
        if (v != null) {
/*
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file))) {
                for (int h = 0; h < lat.length; h++) {
                    writer.write(String.valueOf(lat[h]) + " ");
                }
                writer.write("\n");
                for (int w = 0; w < lon.length; w++) {
                    for (int h = 0; h < lat.length; h++) {
                        writer.write(String.valueOf(u.getDouble(index + l + w)) + " ");
                    }
                    writer.write("\n");
                    l += lat.length;
                    average = 0;
                }
            } catch (Exception e) {

            }
*/
l=0;
            directions = new double[lat.length * lon.length];
            for (int h = 0; h < lat.length; h++) {
                for (int w = 0; w < lon.length; w++) {
                    values[l + w] = module(u.getDouble(index + l + w), v.getDouble(index + l + w));
                    directions[l + w] = angle(u.getDouble(index + l + w), v.getDouble(index + l + w));
                }
                l += lon.length;
            }
        } else {
            directions = null;
            for (int h = 0; h < lat.length; h++) {
                for (int w = 0; w < lon.length; w++) {
                    values[l + w] = u.getDouble(index + l + w)/10.0;
                   // System.out.println("values[l + w] : "+ values[l + w]);
                }
                l += lon.length;
            }
        }
        Pair<Double, Double> minMax = Pair.minMax(values);
        minValue = minMax.getX();
        maxValue = minMax.getY();
    }

    public void dumpValues() {
        for (int h = 0; h < lat.length; h++) {
            for (int w = 0; w < lon.length; w++) {
                System.out.println(values[h + w]);
            }
        }
    }

    public void dumpDirections() {
        for (int h = 0; h < lat.length; h++) {
            for (int w = 0; w < lon.length; w++) {
                System.out.println(directions[h + w]);
            }
        }
    }

    public void dump() {
        for (int h = 0; h < lat.length; h++) {
            for (int w = 0; w < lon.length; w++) {
                System.out.print(lat[h] + " " + lon[w]
                        + " " + values[h + w] + " " + directions[h + w]);
            }
            System.out.println("");
        }
    }

    private double module(double uValue, double vValue) {
        return Math.sqrt((uValue * uValue) + (vValue * vValue));
    }

    private double angle(double uValue, double vValue) {
        double tmp = Math.atan2(uValue, vValue);
        return tmp;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public int getLatDimension() {
        return lat.length;
    }

    public int getLonDimension() {
        return lon.length;
    }

    public double[] getValues() {
        return values;
    }

    public double getValue(int index) {
        return values[index];
    }

    public double getDirection(int index) {
        return directions[index];
    }

    public double[] getDirections() {
        return directions;
    }

    public double getHeight() {
        return height;
    }

    public double getMinLat() {
        return minLat;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMinLon() {
        return minLon;
    }

    public double getMaxLon() {
        return maxLon;
    }

    public double getMinLatitude() {
        return minLat;
    }

    public double getMaxLatitude() {
        return maxLat;
    }

    public double getMinLongitude() {
        return minLon;
    }

    public double getMaxLongitude() {
        return maxLon;
    }

    public int getIndex() {
        return index;
    }

    public double getTime() {
        return time;
    }

}
