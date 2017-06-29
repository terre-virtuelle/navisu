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
public class SerialDeviceOptionBuilder {

    private String portName = "/dev/ttyUSB0";

    private String baudRate = "4800";

    private String dataBits = "8";

    private String stopBits = "1";

    private String parity = "0";

    public SerialDeviceOptionBuilder() {
    }

    public SerialDeviceOptionBuilder(String portName, String baudRate, String dataBits, String stopBits, String parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
    }

    public static SerialDeviceOptionBuilder create() {
        return new SerialDeviceOptionBuilder();
    }

    public SerialDeviceOption build() {
        return new SerialDeviceOption(portName, baudRate, dataBits, stopBits, parity);
    }

    public SerialDeviceOptionBuilder portName(String portName) {
        this.portName = portName;
        return this;
    }

    public SerialDeviceOptionBuilder baudRate(String baudRate) {
        this.baudRate = baudRate;
        return this;
    }

    public SerialDeviceOptionBuilder dataBits(String dataBits) {
        this.dataBits = dataBits;
        return this;
    }

    public SerialDeviceOptionBuilder stopBits(String stopBits) {
        this.stopBits = stopBits;
        return this;
    }

    public SerialDeviceOptionBuilder parity(String parity) {
        this.parity = parity;
        return this;
    }
}
