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
        System.out.println(geo.getDistanceM(48.39567,-4.4481,
                48.39567,-4.675140275521613));
    }
    public static void main(String[] args) {
        new App();
    }
}
