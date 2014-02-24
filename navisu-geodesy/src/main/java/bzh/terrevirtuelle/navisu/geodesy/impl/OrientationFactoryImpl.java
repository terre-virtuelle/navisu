/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.geodesy.impl;

import bzh.terrevirtuelle.navisu.geodesy.Orientation;
import bzh.terrevirtuelle.navisu.geodesy.OrientationFactory;

/**
 *
 * @author Serge
 */
public class OrientationFactoryImpl
        implements OrientationFactory {

    @Override
    public Orientation newOrientation() {
        return new ReadWriteOrientationImpl();
    }

    @Override
    public Orientation newOrientation(double orientation) {
        return new ReadWriteOrientationImpl(orientation);
    }

}
