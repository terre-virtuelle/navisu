/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.view.gps;

import gnu.io.SerialPort;
import bzh.terrevirtuelle.navisu.instruments.instrument.misc.SerialComm;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Serge
 */
public abstract class GPSHandler {

    protected SerialComm serialComm;
    private boolean connected = false;
    private int portNumber = 0;

    public GPSHandler() {
        serialComm = new SerialComm(this);
        // defaults values
        serialComm.setBaudRate(4800);
        serialComm.setDataBit(SerialPort.DATABITS_8);
        serialComm.setParity(SerialPort.PARITY_NONE);
        serialComm.setStopBit(SerialPort.STOPBITS_1);
    }

    public abstract void doIt(String data);

    public void setPortNumber(int param) {
        this.portNumber = param;
        serialComm.setPortNumber(param);
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setBaudRate(int param) {
        serialComm.setBaudRate(param);
    }

    public void setDataBit(int param) {
        serialComm.setDataBit(param);
    }

    public void setStopBit(int param) {
        serialComm.setStopBit(param);
    }

    public void setParity(int param) {
        serialComm.setParity(param);
    }

    public void connect() {
        try {
            if (connected == false) {
                serialComm.connect();
                connected = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(GPSHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        if (connected == true) {
            serialComm.close();
            connected = false;
        }
    }

    public SerialComm getSerialComm() {
        return serialComm;
    }

    public boolean isConnected() {
        return connected;
    }
}
