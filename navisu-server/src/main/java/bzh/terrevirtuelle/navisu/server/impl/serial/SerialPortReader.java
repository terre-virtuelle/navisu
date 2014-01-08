/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl.serial;

import bzh.terrevirtuelle.navisu.server.impl.Reader;

/**
 *
 * @author Serge
 */
public interface SerialPortReader
        extends Reader {

    public void connect(String port, int baudRate, int dataBits, int stopBits, int parity);

    public String[] getPortNames();

    public boolean closePort();
}
