/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.instrument.misc;

import bzh.terrevirtuelle.navisu.instruments.instrument.view.gps.GPSHandler;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Serge Morvan
 */
public class SerialComm {

    private Map<String, Integer> serialPortParams;
    private GPSHandler handler;
    private CommPort commPort;

    public SerialComm(GPSHandler handler) {
        this.handler = handler;
        serialPortParams = new HashMap<>();
        serialPortParams.put("portNumber", null);
        serialPortParams.put("baudRate", 4800);
        serialPortParams.put("dataBit", SerialPort.DATABITS_8);
        serialPortParams.put("stopBit", SerialPort.STOPBITS_1);
        serialPortParams.put("parity", SerialPort.PARITY_NONE);
    }

    public void connect() throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM" + serialPortParams.get("portNumber"));
        if (portIdentifier == null) {
            System.out.println("Error: Port is null");
        } else {
            if (portIdentifier.isCurrentlyOwned()) {
                System.out.println("Error: Port is currently in use");
            } else {
                commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    SerialPort serialPort = (SerialPort) commPort;
                    //  serialPort.setSerialPortParams(4800, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    serialPort.setSerialPortParams(serialPortParams.get("baudRate"),
                            serialPortParams.get("dataBit"),
                            serialPortParams.get("stopBit"),
                            serialPortParams.get("parity"));
                    InputStream in = serialPort.getInputStream();
                    // OutputStream out = serialPort.getOutputStream();
                    //  (new Thread(new SerialWriter(out))).start();
                    serialPort.addEventListener(new SerialReader(in));
                    serialPort.notifyOnDataAvailable(true);
                } else {
                    System.out.println("Error: Only serial ports are handled by this example.");
                }
            }
        }
    }

    public void close() {
        commPort.close();
    }

    /**
     * Handles the input coming from the serial port. A new line character is
     * treated as the end of a block in this example.
     */
    public Enumeration<CommPortIdentifier> listPorts() {
        Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        return portEnum;
    }

    String getPortTypeName(int portType) {
        switch (portType) {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }

    public void setPortNumber(int param) {
        serialPortParams.put("portNumber", param);
    }

    public void setBaudRate(int param) {
        serialPortParams.put("baudRate", param);
    }

    public void setDataBit(int param) {
        serialPortParams.put("dataBit", param);
    }

    public void setStopBit(int param) {
        serialPortParams.put("stopBit", param);
    }

    public void setParity(int param) {
        serialPortParams.put("parity", param);
    }

    public class SerialReader implements SerialPortEventListener {

        private InputStream in;
        private byte[] buffer = new byte[1024];

        public SerialReader(InputStream in) {
            this.in = in;
        }

        @Override
        public void serialEvent(SerialPortEvent arg0) {
            int data;

            try {
                int len = 0;
                while ((data = in.read()) > -1) {
                    if (data == '\n') {
                        break;
                    }
                    buffer[len++] = (byte) data;
                }
                handler.doIt(new String(buffer, 0, len));
                //  nmeaParser.parse(new String(buffer, 0, len));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }

        private String read(int len) {
            return new String(buffer, 0, len);
        }
    }

    public class SerialWriter implements Runnable {

        OutputStream out;

        public SerialWriter(OutputStream out) {
            this.out = out;
        }

        public void run() {
            try {
                int c = 0;
                while ((c = System.in.read()) > -1) {
                    this.out.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
    }
}
