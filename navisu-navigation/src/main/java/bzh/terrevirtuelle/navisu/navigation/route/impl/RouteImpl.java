/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.route.impl;

import bzh.terrevirtuelle.navisu.geometry.curves2D.bezier.Bezier2DServices;
import bzh.terrevirtuelle.navisu.navigation.route.Route;
import bzh.terrevirtuelle.navisu.navigation.route.RouteServices;
import bzh.terrevirtuelle.navisu.util.Pair;
import java.util.List;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 27 juin 2015
 * @author Serge Morvan
 */
public class RouteImpl
        implements Route, RouteServices, ComponentState {

    @UsedService
    Bezier2DServices bezier2DServices;

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
