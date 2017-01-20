/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl;

/**
 *
 * @author Serge
 */
public interface Reader {

    public void read();

    public default String[] readBuffer() {
        return null;
    }

    public String getData();

    public default int getLength() {
        return 0;
    }

}
