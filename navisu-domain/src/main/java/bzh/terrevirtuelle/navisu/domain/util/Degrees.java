/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.util;

/**
 *
 * @author serge
 * @date Feb 20, 2016
 *
 */
public class Degrees {

    @SuppressWarnings("unchecked")
    public static Pair<Double, Double> degTodecimal(String str) throws Exception {
        //In format 43° 08' N — 5° 46' E or 43° 02,2' N — 6° 07,7' E
        //Out Pair(lat, lon)
        int sign;
        double min;
        double lat = 0.0;
        double lon = 0.0;
        str = str.replace(",", ".");
        String[] tab0 = str.split("—");
        tab0[0] = tab0[0].replace("°", "");
        tab0[0] = tab0[0].replace("'", "");
        String[] tab1 = tab0[0].split("\\h+| ");
        if (tab1[2].contains("N")) {
            sign = 1;
        } else {
            sign = -1;
        }

        lat = Double.valueOf(tab1[0]);
        min = Double.valueOf(tab1[1]);
        min /= 60.0;
        lat = sign * (lat + min);
        tab0[1] = tab0[1].replace("°", "");
        tab0[1] = tab0[1].replace("'", "");
        String[] tab2 = tab0[1].split(" ");
        if (tab2[2].contains("E")) {
            sign = 1;
        } else {
            sign = -1;
        }
        lon = Double.valueOf(tab2[0]);
        min = Double.valueOf(tab2[1]);
        min /= 60.0;
        lon = sign * (lon + min);

        return new Pair(lat, lon);
    }
}
