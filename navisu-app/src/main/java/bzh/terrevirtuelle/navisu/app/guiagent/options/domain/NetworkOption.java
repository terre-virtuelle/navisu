/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.domain;

/**
 *
 * @author serge
 * @date Jun 26, 2017
 */
public class NetworkOption
        implements Option {

    private static int index = 0;

    private String protocol;

    private String address;

    private String dataport;

    public NetworkOption() {
    }

    public NetworkOption(String protocol, String address, String dataport) {
        this.protocol = protocol;
        this.address = address;
        this.dataport = dataport;
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
     * Get the value of dataport
     *
     * @return the value of dataport
     */
    public String getDataport() {
        return dataport;
    }

    /**
     * Set the value of dataport
     *
     * @param dataport new value of dataport
     */
    public void setDataport(String dataport) {
        this.dataport = dataport;
    }

    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get the value of protocol
     *
     * @return the value of protocol
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Set the value of protocol
     *
     * @param protocol new value of protocol
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return "NetworkOption{" + "protocol=" + protocol + ", address=" + address + ", dataport=" + dataport + '}';
    }

}
