/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl.serial.impl.jssc;

import bzh.terrevirtuelle.navisu.server.impl.serial.SerialPortReader;
import jssc.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.vertx.java.core.Vertx;

/**
 * @author Serge
 */
public class SerialPortReaderImpl
        implements SerialPortReader, SerialPortEventListener {

    private SerialPort serialPort;
    private String sentence;
    private StringBuffer buffer = new StringBuffer();
    private final Vertx vertx;
    private final int index;

    public SerialPortReaderImpl(int index, Vertx vertx) {
        this.vertx = vertx;
        this.index = index;
        String[] portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            System.out.println(portName);
        }

    }

    @Override
    public void connect(String port, int baudRate, int dataBits, int stopBits, int parity) {
        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(baudRate, dataBits, stopBits, parity);
            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);
            serialPort.addEventListener(this);
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public String[] getPortNames() {
        return SerialPortList.getPortNames();

    }

    @Override
    public boolean closePort() {
        boolean result = false;
        if (serialPort.isOpened()) {
            try {
                result = serialPort.closePort();
            } catch (SerialPortException ex) {
                Logger.getLogger(SerialPortReaderImpl.class.getName()).log(Level.SEVERE, ex.toString(), ex);
            }
            
        }
        return result;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        String s;
        buffer = new StringBuffer();
        try {
            do {
                s = serialPort.readString();
                if (s != null) {
                    buffer.append(s);
                } else {
                    s = "";
                }
            } while (!s.equals("\n"));
            sentence = buffer.toString();
            // System.out.println("sentence " + sentence);
        } catch (SerialPortException ex) {
            java.util.logging.Logger.getLogger(SerialPortReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        vertx.eventBus().send("comm-address" + index, sentence);
    }

    @Override
    public void read() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
