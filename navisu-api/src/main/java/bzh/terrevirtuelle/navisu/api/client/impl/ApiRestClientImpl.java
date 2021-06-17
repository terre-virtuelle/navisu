/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.api.client.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import org.capcaval.c3.component.ComponentState;
import bzh.terrevirtuelle.navisu.api.client.ApiRestClientServices;
import bzh.terrevirtuelle.navisu.api.client.ApiRestClient;
import bzh.terrevirtuelle.navisu.api.client.impl.controler.ApiRestClientControler;

/**
 *
 * @author serge
 * @date Mar 7, 2021
 */
public class ApiRestClientImpl
        implements ApiRestClient, ApiRestClientServices, InstrumentDriver, ComponentState {

    ApiRestClientControler apiRestClientControler;

    @Override
    public void init(String requestType, String host, String port, String path, String cmd, String origin, String target) {
        apiRestClientControler = new ApiRestClientControler(requestType, host, port, path, cmd, origin, target);
    }

    @Override
    public void setParameters(String parameters) {
        apiRestClientControler.setParameters(parameters);
    }

    @Override
    public void sendRequest() {
        apiRestClientControler.sendRequest();
    }

    @Override
    public void sendRequest(String request) {
        apiRestClientControler.sendRequest(request);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

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
