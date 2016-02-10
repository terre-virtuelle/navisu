/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.commands;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationViewSet;

/**
 *
 * @author serge
 */
public interface NavigationCmd {

    public NavigationViewSet doIt(NavigationData arg);
    
}
