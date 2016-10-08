/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.netcdf.common;

import bzh.terrevirtuelle.navisu.domain.util.Pair;
import java.util.ArrayList;
import java.util.List;
import ucar.ma2.Array;

/**
 *
 * @author serge
 * @date Sep 16, 2016
 */
public class TimeSeriesVectorField {

    private final List<Pair<Double, VField>> vFields;
    private double minValue;
    private double maxValue;
    private final int timeDimension;
    private final int latDimension;
    private final int lonDimension;
    private final double[] latitudes;
    private final double[] longitudes;
    private final double height;
    private final double minLat;
    private final double maxLat;
    private final double minLon;
    private final double maxLon;
    private final int block;
    private int index;

    public TimeSeriesVectorField(Array time, Array lat, Array lon, float height, Array u, Array v) {
        timeDimension = (int) time.getSize();

        latDimension = (int) lat.getSize();
        lonDimension = (int) lon.getSize();
        this.height = height;
        Pair<Double, Double> minMax = Pair.minMax(lat);
        minLat = minMax.getX();
        maxLat = minMax.getY();
        minMax = Pair.minMax(lon);
        minLon = minMax.getX();
        maxLon = minMax.getY();

        latitudes = new double[latDimension];
        longitudes = new double[lonDimension];
        for (int h = 0; h < latDimension; h++) {
            latitudes[h] = lat.getDouble(h);
        }
        for (int w = 0; w < lonDimension; w++) {
            longitudes[w] = lon.getDouble(w);
        }

        block = latDimension * lonDimension;
        vFields = new ArrayList<>(timeDimension);
        index = 0;
        VField vf = new VField(lat, lon, u, v, index);
        for (int t = 0; t < timeDimension; t++) {
            vFields.add(new Pair<>(time.getDouble(t), vf));
            // System.out.println("index : " + index);
            // vf.dumpValues();
            index += block;
        }
    }

    public int getTimeDimension() {
        return timeDimension;
    }

    public int getLatitudeDimension() {
        return latDimension;
    }

    public int getLongitudeDimension() {
        return lonDimension;
    }

    public double getHeight() {
        return height;
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

    public double[] getLatitudes() {
        return latitudes;
    }

    public double[] getLongitudes() {
        return longitudes;
    }

    public List<Pair<Double, VField>> getVFields() {
        return vFields;
    }

    public VField getVField(int index) {
        return vFields.get(index).getY();
    }

    public double[] getValues(int index) {
        return vFields.get(index).getY().getValues();
    }

    public double getMinValue(int index) {
        return vFields.get(index).getY().getMinValue();
    }

    public double getMaxValue(int index) {
        return vFields.get(index).getY().getMaxValue();
    }

    public double[] getDirections(int index) {
        return vFields.get(index).getY().getDirections();
    }

    public void dump() {
        vFields.stream().forEach((v) -> {
            v.getY().dump();
        });
    }
}
