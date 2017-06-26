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

public class ServerOption
        implements Option {
/*
    private int port;
    private String hostName;
    */
    
    private String hostName;

    private String port;

    public ServerOption(String hostName, String port) {
        this.hostName = hostName;
        this.port = port;
    }

    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public String getPort() {
        return port;
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * Get the value of hostName
     *
     * @return the value of hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Set the value of hostName
     *
     * @param hostName new value of hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    @Override
    public String toString() {
        return "ServerOption{" + "hostName=" + hostName + ", port=" + port + '}';
    }

}
