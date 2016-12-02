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

    private List<VectorField> vFields;
    private final List<List<VectorField>> hVFields;
    private final int timeDimension;
    private int heightDimension;
    private final int latDimension;
    private final int lonDimension;
    private final double minLat;
    private final double maxLat;
    private double minLon;
    private double maxLon;
    private final double[] latitudes;
    private final double[] longitudes;
    private final double[] times;
    private int index;

    public TimeSeriesVectorField(Array time, Array height,
            Array lat, Array lon,
            Array u, Array v) {
        heightDimension = (int) height.getSize();
        hVFields = new ArrayList<>(heightDimension);
        if (time != null) {
            timeDimension = (int) time.getSize();
            times = new double[timeDimension];
            for (int i = 0; i < timeDimension; i++) {
                times[i] = time.getDouble(i);
            }
        }else{
            timeDimension=1;
            times = new double[timeDimension];
            times[0]=1;
        }

        latDimension = (int) lat.getSize();
        lonDimension = (int) lon.getSize();
        latitudes = new double[latDimension];
        longitudes = new double[lonDimension];

        for (int h = 0; h < latDimension; h++) {
            latitudes[h] = lat.getDouble(h);
        }
        for (int w = 0; w < lonDimension; w++) {
            longitudes[w] = lon.getDouble(w);
        }
        Pair<Double, Double> minMax = Pair.minMax(latitudes);
        minLat = minMax.getX();
        maxLat = minMax.getY();
        minMax = Pair.minMax(longitudes);
        minLon = minMax.getX();
        maxLon = minMax.getY();
        if (minLon > 180 || maxLon > 180) {
            minLon -= 360;
            maxLon -= 360;
        }
        int block = latDimension * lonDimension;
        index = 0;
        VectorField vf;
        heightDimension = 1;//Pb memoire si rop de hauteurs et de temps
        for (int h = 0; h < heightDimension; h++) {
            vFields = new ArrayList<>();
            hVFields.add(vFields);
            for (int t = 0; t < timeDimension; t++) {
                vf = new VectorField(latitudes, longitudes, minLat, maxLat, minLon, maxLon,
                        u, v, index, height.getDouble(h), times[t]);
                vFields.add(vf);
                index += block * (height.getSize());
            }
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

    public double getMinLatitude() {
        return vFields.get(0).getMinLatitude();
    }

    public double getMaxLatitude() {
        return vFields.get(0).getMaxLatitude();//Tous les VFields ont le meme sector
    }

    public double getMinLongitude() {
        return vFields.get(0).getMinLongitude();
    }

    public double getMaxLongitude() {
        return vFields.get(0).getMaxLongitude();
    }

    public double[] getLatitudes() {
        return latitudes;
    }

    public double[] getLongitudes() {
        return longitudes;
    }

    public List<VectorField> getVFields() {
        return vFields;
    }

    public VectorField getVField(int index) {
        return vFields.get(index);
    }

    public double[] getValues(int index) {
        return vFields.get(index).getValues();
    }

    public double getMinValue(int index) {
        return vFields.get(index).getMinValue();
    }

    public double getMaxValue(int index) {
        return vFields.get(index).getMaxValue();
    }

    public double[] getDirections(int index) {
        return vFields.get(index).getDirections();
    }

    public void dump() {
        vFields.stream().forEach((v) -> {
            v.dump();
        });
    }

    public List<List<VectorField>> gethVFields() {
        return hVFields;
    }

    public double[] getTimes() {
        return times;
    }
}
