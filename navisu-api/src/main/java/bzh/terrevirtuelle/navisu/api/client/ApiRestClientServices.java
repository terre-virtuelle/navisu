/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.api.client;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentService;

/**
 *
 * @author serge
 * @date Mar 7, 2021
 */
public interface ApiRestClientServices
        extends ComponentService {

    void init(String requestType, String host, String port, String path, String cmd, String origin, String target);

    void setParameters(String parameters);

    void sendRequest();

    void sendRequest(String request);

    InstrumentDriver getDriver();

}
