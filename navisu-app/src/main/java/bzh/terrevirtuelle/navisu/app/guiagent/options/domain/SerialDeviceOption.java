/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

/**
 *
 * @author serge
 * @date Jun 28, 2017
 */
public class SerialDeviceOption
        implements Option {

    private static int index = -1;

    private String portName;

    private String baudRate;

    private String dataBits;

    private String stopBits;

    private String parity;

    public SerialDeviceOption() {
    }

    public SerialDeviceOption(String portName, String baudRate, String dataBits, String stopBits, String parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        index++;
    }

    /**
     * Get the value of index
     *
     * @return the value of index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Set the value of index
     *
     * @param index new value of index
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Get the value of parity
     *
     * @return the value of parity
     */
    public String getParity() {
        return parity;
    }

    /**
     * Set the value of parity
     *
     * @param parity new value of parity
     */
    public void setParity(String parity) {
        this.parity = parity;
    }

    /**
     * Get the value of stopBits
     *
     * @return the value of stopBits
     */
    public String getStopBits() {
        return stopBits;
    }

    /**
     * Set the value of stopBits
     *
     * @param stopBits new value of stopBits
     */
    public void setStopBits(String stopBits) {
        this.stopBits = stopBits;
    }

    /**
     * Get the value of dataBits
     *
     * @return the value of dataBits
     */
    public String getDataBits() {
        return dataBits;
    }

    /**
     * Set the value of dataBits
     *
     * @param dataBits new value of dataBits
     */
    public void setDataBits(String dataBits) {
        this.dataBits = dataBits;
    }

    /**
     * Get the value of baudRate
     *
     * @return the value of baudRate
     */
    public String getBaudRate() {
        return baudRate;
    }

    /**
     * Set the value of baudRate
     *
     * @param baudRate new value of baudRate
     */
    public void setBaudRate(String baudRate) {
        this.baudRate = baudRate;
    }

    /**
     * Get the value of portName
     *
     * @return the value of portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * Set the value of portName
     *
     * @param portName new value of portName
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    @Override
    public String toString() {
        return "DevicesOption{" + "portName=" + portName + ", baudRate=" + baudRate + ", dataBits=" + dataBits + ", stopBits=" + stopBits + ", parity=" + parity + '}';
    }

}
