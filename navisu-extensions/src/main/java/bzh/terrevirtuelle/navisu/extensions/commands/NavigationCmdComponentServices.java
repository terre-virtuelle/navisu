/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.commands;

import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationData;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface NavigationCmdComponentServices
        extends ComponentService {

    public void init();

    public NavigationDataSet doIt(String cmd, NavigationData navigationData);
    
    public NavigationDataSet doIt(String cmd, NavigationData navigationData, String arg);

    public NavigationDataSet doIt(String cmd, String arg);

}
