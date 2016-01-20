/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.controller.cmd;

import bzh.terrevirtuelle.navisu.navigation.controller.cmd.catalog.ArCommand;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface NavigationCmdComponentServices
        extends ComponentService {

    public void init();

    public void doIt(ArCommand navigationCmd);
}
