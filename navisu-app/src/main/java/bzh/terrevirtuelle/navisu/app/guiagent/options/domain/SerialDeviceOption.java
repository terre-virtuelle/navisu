/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author serge
 * @date Jun 28, 2017
 */
public class SerialDeviceOption
        extends Option {

    private StringProperty portName;

    private StringProperty baudRate;

    private StringProperty dataBits;

    private StringProperty stopBits;

    private StringProperty parity;

    private SimpleBooleanProperty status;

    public SerialDeviceOption() {
    }

    public SerialDeviceOption(String type,
            String portName, String baudRate, String dataBits, String stopBits, String parity,
            Boolean status) {
        super(type);
        this.portName = new SimpleStringProperty(portName);
        this.baudRate = new SimpleStringProperty(baudRate);
        this.dataBits = new SimpleStringProperty(dataBits);
        this.stopBits = new SimpleStringProperty(stopBits);
        this.parity = new SimpleStringProperty(parity);
        this.status = new SimpleBooleanProperty(status);
    }

    

    public final void setPortName(String value) {
        portName.set(value);
    }

    public final String getPortName() {
        return portName.get();
    }

    public final StringProperty portNameProperty() {
        return portName;
    }

    public final void setBaudRate(String value) {
        baudRate.set(value);
    }

    /**
     * Get the value of status
     *
     * @return
     */
    public final String getBaudRate() {
        return baudRate.get();
    }

    /**
     * Set the value of status
     *
     * @return
     */
    public final StringProperty baudRateProperty() {
        return baudRate;
    }

    /**
     * Get the value of parity
     *
     * @param value
     */
    public final void setDataBits(String value) {
        dataBits.set(value);
    }

    /**
     * Set the value of parity
     *
     * @return
     */
    public final String getDataBits() {
        return dataBits.get();
    }

    /**
     * Get the value of stopBits
     *
     * @return
     */
    public final StringProperty dataBitsProperty() {
        return dataBits;
    }

    /**
     * Set the value of stopBits
     *
     * @param value
     */
    public final void setStopBits(String value) {
        stopBits.set(value);
    }

    /**
     * Get the value of dataBits
     *
     * @return
     */
    public final String getStopBits() {
        return stopBits.get();
    }

    /**
     * Set the value of dataBits
     *
     * @return
     */
    public final StringProperty stopBitsProperty() {
        return stopBits;
    }

    /**
     * Get the value of baudRate
     *
     * @param value
     */
    public final void setParity(String value) {
        parity.set(value);
    }

    /**
     * Set the value of baudRate
     *
     * @return
     */
    public final String getParity() {
        return parity.get();
    }

    /**
     * Get the value of portName
     *
     * the value of portName
     *
     * @return
     */
    public final StringProperty parityProperty() {
        return parity;
    }

    /**
     * Set the value of portName
     *
     * @param value
     */
    public final void setStatus(Boolean value) {
        status.set(value);
    }

    public final Boolean getStatus() {
        return status.get();
    }

    public final SimpleBooleanProperty statusProperty() {
        return status;
    }
   
}
