/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.api.client.impl.controler;

import bzh.terrevirtuelle.navisu.api.client.impl.ApiRestClientImpl;
import bzh.terrevirtuelle.navisu.core.util.Proc;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author serge
 * @date Mar 7, 2021
 */
public class ApiRestClientControler {

    private String host;
    private String port;
    private String path;
    private String cmd;
    private String origin;
    private String target;
    private double latitude;
    private double longitude;
    private double altitude;

    private double heading;
    private double pitch;
    private double roll;

    private String timestamp;

    private String request;

    private String requestType;
    private String parameters = "";
//curl -X PUT "http://localhost:3000/control?cmd=position&origin=SMAUG&target=camera&latitude=48.00&longitude=-4.50&altitude=30000&heading=90.0&pitch=0.0&roll=0.0"

    public ApiRestClientControler() {
    }

    public ApiRestClientControler(String requestType, String host, String port, String path, String cmd, String origin, String target) {
        this.host = host;
        this.port = port;
        this.path = path;
        this.cmd = cmd;
        this.origin = origin;
        this.target = target;
        this.requestType = requestType;
       this.request = "curl -X " + this.requestType + " \"http://" + this.host + ":" + port + "/" + this.path + "?cmd=" + this.cmd + "&origin=" + this.origin + "&target=" + this.target;
       // this.request = "curl -X " + this.requestType + " \"http://" + this.host + ":" + port + "?cmd=" + this.cmd + "&origin=" + this.origin + "&target=" + this.target;
        //  System.out.println("this.request " + this.request );
    }

    public ApiRestClientControler(String request) {
        this.request = request;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters + "\"";
    }

    public void sendRequest() {
        System.out.println(this.request + this.parameters);
        try {
            Proc.BUILDER.create()
                    .setCmd(this.request + this.parameters)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ApiRestClientControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendRequest(String request) {
        try {
            Proc.BUILDER.create()
                    .setCmd(request)
                    .execSh();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ApiRestClientControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Get the value of altitude
     *
     * @return the value of altitude
     */
    public double getAltitude() {
        return altitude;
    }

    /**
     * Set the value of altitude
     *
     * @param altitude new value of altitude
     */
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    /**
     * Get the value of requestType
     *
     * @return the value of requestType
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Set the value of requestType
     *
     * @param requestType new value of requestType
     */
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    /**
     * Get the value of request
     *
     * @return the value of request
     */
    public String getRequest() {
        return request;
    }

    /**
     * Set the value of request
     *
     * @param request new value of request
     */
    public void setRequest(String request) {
        this.request = request;
    }

    /**
     * Get the value of roll
     *
     * @return the value of roll
     */
    public double getRoll() {
        return roll;
    }

    /**
     * Set the value of roll
     *
     * @param roll new value of roll
     */
    public void setRoll(double roll) {
        this.roll = roll;
    }

    /**
     * Get the value of pitch
     *
     * @return the value of pitch
     */
    public double getPitch() {
        return pitch;
    }

    /**
     * Set the value of pitch
     *
     * @param pitch new value of pitch
     */
    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    /**
     * Get the value of heading
     *
     * @return the value of heading
     */
    public double getHeading() {
        return heading;
    }

    /**
     * Set the value of heading
     *
     * @param heading new value of heading
     */
    public void setHeading(double heading) {
        this.heading = heading;
    }

    /**
     * Get the value of longitude
     *
     * @return the value of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set the value of longitude
     *
     * @param longitude new value of longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Get the value of latitude
     *
     * @return the value of latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Set the value of latitude
     *
     * @param latitude new value of latitude
     * @param longitude
     * @param altitude
     */
    public void setLatitude(double latitude, double longitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.request += "&latitude=" + latitude + "&longitude=" + longitude + "&altitude=" + altitude;
    }

    public void setLatLon(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Get the value of target
     *
     * @return the value of target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Set the value of target
     *
     * @param target new value of target
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Get the value of origin
     *
     * @return the value of origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the value of origin
     *
     * @param origin new value of origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Get the value of cmd
     *
     * @return the value of cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * Set the value of cmd
     *
     * @param cmd new value of cmd
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Get the value of path
     *
     * @return the value of path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the value of path
     *
     * @param path new value of path
     */
    public void setPath(String path) {
        this.path = path;
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
     * Get the value of host
     *
     * @return the value of host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set the value of host
     *
     * @param host new value of host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get the value of timestamp
     *
     * @return the value of timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Set the value of timestamp
     *
     * @param timestamp new value of timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
