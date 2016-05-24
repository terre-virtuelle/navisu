/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.impl.controller;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author serge
 * @date May 23, 2016
 *
 */
public class Connection {

    private final SimpleStringProperty type;
    private final SimpleStringProperty dataPort;
    private final SimpleStringProperty parameters;
    private final SimpleStringProperty status;

    public Connection(String type, String dataPort, String parameters, String status) {
        this.type = new SimpleStringProperty(type);
        this.dataPort = new SimpleStringProperty(dataPort);
        this.parameters = new SimpleStringProperty(parameters);
        this.status = new SimpleStringProperty(status);
    }

    
    public String getParameters() {
        return parameters.get();
    }

    public void setParameters(String parameters) {
        this.parameters.set(parameters);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    /**
     * Get the value of dataPort
     *
     * @return the value of dataPort
     */
    public String getDataPort() {
        return dataPort.get();
    }

    /**
     * Set the value of dataPort
     *
     * @param dataPort new value of dataPort
     */
    public void setDataPort(String dataPort) {
        this.dataPort.set(dataPort);
    }

}
