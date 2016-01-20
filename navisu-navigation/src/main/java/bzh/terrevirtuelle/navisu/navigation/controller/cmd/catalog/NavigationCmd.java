/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog;

import bzh.terrevirtuelle.navisu.domain.navigation.NavigationData;

/**
 *
 * @author serge
 */
public interface NavigationCmd {

    public NavigationData doIt(NavigationData arg);
    
}
