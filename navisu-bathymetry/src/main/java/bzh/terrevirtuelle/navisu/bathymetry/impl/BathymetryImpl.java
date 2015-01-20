/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.bathymetry.impl;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.drivers.Driver;
import bzh.terrevirtuelle.navisu.bathymetry.Bathymetry;
import bzh.terrevirtuelle.navisu.bathymetry.BathymetryServices;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author Serge
 */
public class BathymetryImpl
implements Bathymetry, BathymetryServices, Driver, ComponentState{

    @Override
    public Driver getDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void openFile(String file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void open(ProgressHandle progressHandle, String... files) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getExtensions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentInitiated() {
           }

    @Override
    public void componentStarted() {
      }

    @Override
    public void componentStopped() {
      }
    
}
