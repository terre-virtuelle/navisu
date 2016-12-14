/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.dbase.impl;

import bzh.terrevirtuelle.navisu.dbase.DBaseComponent;
import bzh.terrevirtuelle.navisu.dbase.DBaseComponentServices;
import org.capcaval.c3.component.ComponentState;

/**
 *
 * @author Serge Morvan
 * @date 10 nov. 2014 NaVisu project
 */
public class DBaseComponentImpl
        implements DBaseComponent, DBaseComponentServices, ComponentState {

    @Override
    public void componentInitiated() {

    }

    @Override
    public void componentStarted() {
        /* Nothing to do here */ }

    @Override
    public void componentStopped() {
        /* Nothing to do here */ }

    @Override
    public void init(String filename) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getFieldCount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] getRowObjects() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
