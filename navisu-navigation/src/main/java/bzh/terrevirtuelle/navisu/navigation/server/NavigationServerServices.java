/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.server;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface NavigationServerServices
        extends ComponentService {

    public boolean canOpen(String category);

    public InstrumentDriver getDriver();

    public void init();

    public void init(int port);

}
