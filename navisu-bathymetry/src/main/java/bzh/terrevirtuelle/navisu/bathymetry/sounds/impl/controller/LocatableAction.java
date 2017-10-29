/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.sounds.impl.controller;

import bzh.terrevirtuelle.navisu.domain.geometry.Point3D;

/**
 *
 * @author serge
 * @date Oct 16, 2017
 */
public interface LocatableAction {

    void doIt(Point3D point);
}
