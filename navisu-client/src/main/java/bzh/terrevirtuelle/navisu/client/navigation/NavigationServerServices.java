/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.navigation;

import bzh.terrevirtuelle.navisu.client.nmea.*;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author Serge
 */
public interface NavigationServerServices
        extends ComponentService {

    public void open();

    public void open(String hostName, int port, int period);

    void request(int period);

}
