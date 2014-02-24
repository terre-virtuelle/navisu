/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geodesy;

import bzh.terrevirtuelle.navisu.geodesy.impl.OrientationFactoryImpl;

/**
 *
 * @author Serge
 */
public interface Orientation {

    public static final OrientationFactory factory = new OrientationFactoryImpl();
    
    public static final double MAX_ORIENTATION = 360d;
    public static final double MIN_ORIENTATION = 0d;

    public double getOrientationDegree();
}
