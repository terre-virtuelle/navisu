/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.commands;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.view.NavigationViewSet;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface NavigationCmdComponentServices
        extends ComponentService {

    public void init();

    public NavigationViewSet doIt(String cmd, NavigationData navigationData);

    public NMEA doIt(String cmd);

}
