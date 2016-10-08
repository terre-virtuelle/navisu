/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf.common;

import ucar.ma2.Array;

/**
 *
 * @author serge
 * @date Sep 8, 2016
 */
public class VField {

    private final int latDimension;
    private final int lonDimension;
    private final double[] values;
    private final double[] directions;
    private final Array lat;
    private final Array lon;

    public VField(Array lat, Array lon, Array u, Array v, int index) {
        this.lat = lat;
        this.lon = lon;
        latDimension = (int) lat.getSize();
        lonDimension = (int) lon.getSize();
        values = new double[latDimension * lonDimension];
        directions = new double[latDimension * lonDimension];
        int l = 0;
        for (int h = 0; h < latDimension; h++) {
            for (int w = 0; w < lonDimension; w++) {
                values[l + w] = vectorModule(u.getDouble(index + l + w), v.getDouble(index + l + w));
                directions[l + w] = angleCalculation(u.getDouble(index + l + w), v.getDouble(index + l + w));
            }
            l += lonDimension;
        }
    }

    public void dumpValues() {
        for (int h = 0; h < latDimension; h++) {
            for (int w = 0; w < lonDimension; w++) {
                System.out.println(values[h + w]);
            }
        }
    }

    public void dumpDirections() {
        for (int h = 0; h < latDimension; h++) {
            for (int w = 0; w < lonDimension; w++) {
                System.out.println(directions[h + w]);
            }
        }
    }

    public void dump() {
        for (int h = 0; h < latDimension; h++) {
            for (int w = 0; w < lonDimension; w++) {
                System.out.print(lat.getDouble(h) + " " + lon.getDouble(w)
                        + " " + values[h + w] + " " + directions[h + w]);
            }
            System.out.println("");
        }
    }

    private double vectorModule(double uValue, double vValue) {
        return Math.sqrt((uValue * uValue) + (vValue * vValue));
    }

    private double angleCalculation(double uValue, double vValue) {
        double tmp = Math.atan2(uValue, vValue);
        /*
        if ((uValue >= 0 && vValue >= 0) || (uValue >= 0 && vValue < 0) || (uValue < 0 && vValue < 0)) {
            tmp = Math.PI / 2 - tmp;
        }
        if ((uValue < 0 && vValue > 0)) {
            tmp = Math.PI / 2 - tmp + 2 * Math.PI;
        }
        if ((uValue < 0 && vValue == 0)) {
           tmp = Math.PI/2 + tmp;
           
        }
         */
        /*
        if (uValue < 0) {
            tmp = Math.PI + tmp;
        }
*/
        return tmp;
    }

    public double getMinValue() {
        double minValue = Double.MAX_VALUE;
        for (double d : values) {
            if (minValue > d) {
                minValue = d;
            }
        }
        return minValue;
    }

    public double getMaxValue() {
        double maxValue = Double.MIN_VALUE;
        for (double d : values) {
            if (maxValue < d) {
                maxValue = d;
            }
        }
        return maxValue;
    }

    public int getLatDimension() {
        return latDimension;
    }

    public int getLonDimension() {
        return lonDimension;
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

}
