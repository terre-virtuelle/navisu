/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.dbase.impl;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.core.view.geoview.worldwind.impl.GeoWorldWindViewImpl;
import bzh.terrevirtuelle.navisu.dbase.DBaseComponent;
import bzh.terrevirtuelle.navisu.dbase.DBaseComponentServices;
import static bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl.NMEALexer.NAME;
import gov.nasa.worldwind.event.PositionEvent;
import org.capcaval.c3.component.ComponentState;
import static org.eclipse.persistence.internal.jpa.parsing.jpql.antlr.JPQLParser.GROUP;

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

}
