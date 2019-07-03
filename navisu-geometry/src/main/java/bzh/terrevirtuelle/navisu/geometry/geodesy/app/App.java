/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geometry.geodesy.app;

import bzh.terrevirtuelle.navisu.geometry.geodesy.impl.GeodesyImpl;

/**
 *
 * @author serge
 */
public class App {

    /* Test calcul de distance */
    public App() {
        GeodesyImpl geo = new GeodesyImpl();
        System.out.println(geo.getDistanceM(48.243795854365146, -4.675140275521613,
                48.243795854365146, -4.4481));
        System.out.println(geo.getDistanceM(48.39567, -4.4481,
                48.39567, -4.675140275521613));
    }

    public static void main(String[] args) {
        double latMin = 48.243;
        double lonMin = -4.675;
        double latMax = 48.244;
        double lonMax = -4.670;

        double sideX = 200;
        double sideY = 100;

        GeodesyImpl geo = new GeodesyImpl();

        double distMX = geo.getDistanceM(latMin, lonMin, latMin, lonMax);
        double distMY = geo.getDistanceM(latMin, lonMin, latMax, lonMin);

        double scaleX = sideX / distMX;
        double scaleY = sideY / distMY;
        
        scaleX = 1/scaleX;

        System.out.println("scale : " + scaleX + " " + scaleY);

    }
}
