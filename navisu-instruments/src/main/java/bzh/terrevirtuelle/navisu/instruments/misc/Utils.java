/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.misc;

/**
 *
 * @author Serge Morvan
 */
public class Utils {

    public static float[] degToHms(float val) {
        float[] hms = new float[3];
        hms[0] = (int) val;
        float tmp = (val - hms[0]) * 60;
        hms[1] = (int) tmp;
        tmp = (tmp - hms[1]) * 60;
        hms[2] = tmp;
        return hms;
    }

    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6369; // en km pour des miles : 3958.75;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        return (float)dist;
    }
}
