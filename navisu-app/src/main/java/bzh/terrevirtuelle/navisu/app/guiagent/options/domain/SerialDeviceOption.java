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
        implements Option {

   
    private StringProperty portName;

    private StringProperty baudRate;

    private StringProperty dataBits;

    private StringProperty stopBits;

    private StringProperty parity;

    private SimpleBooleanProperty status;

    private String type;
public final SimpleBooleanProperty statusProperty() {
        return status;
    }
    public SerialDeviceOption() {
    }
    public SerialDeviceOption(String type, String portName, String baudRate, String dataBits, Boolean status) {
       this.type = type;
        this.portName = new SimpleStringProperty(portName);
        this.baudRate = new SimpleStringProperty(baudRate);
        this.dataBits = new SimpleStringProperty(dataBits);
        this.status = new SimpleBooleanProperty(status);
    }
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
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
     * @param value
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
     * @return 
     */
    public final void setDataBits(String value) {
        dataBits.set(value);
    }

    /**
     * Set the value of parity
     *
     * @param value
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
     * @return 
     */
    public final void setStopBits(String value) {
        stopBits.set(value);
    }

    /**
     * Get the value of dataBits
     *
     * @param value
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
     * @return 
     */
    public final void setParity(String value) {
        parity.set(value);
    }

    /**
     * Set the value of baudRate
     *
     * @param value
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
     * @return 
     */
    public final void setStatus(Boolean value) {
        status.set(value);
    }
/*
    public final void setParity(String value) {
        parity.set(value);
    }

    public final String getParity() {
        return parity.get();
    }

    public final StringProperty parityProperty() {
        return parity;
    }

    public final void setStatus(String value) {
        status.set(value);
    }

    public final String getStatus() {
        return status.get();
    }

    public final StringProperty statusProperty() {
        return status;
    }
*/

    public final Boolean getStatus() {
        return status.get();
    }

    
}
