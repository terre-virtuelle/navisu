/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.navigation.impl.vertx;

import bzh.terrevirtuelle.navisu.client.navigation.NavigationServer;
import bzh.terrevirtuelle.navisu.client.navigation.NavigationServerServices;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import org.capcaval.c3.component.ComponentState;
import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.WebSocket;

/**
 *
 * @author Serge
 */
public class NavigationServerImpl
        implements NavigationServer, NavigationServerServices, ComponentState {

    private WebSocket ws;
    private Properties properties;
    private Vertx vertx;
    private String hostName;
    private int port;
    private int period;

    @Override
    public void componentInitiated() {
        properties = new Properties();

        try {
            properties.load(new FileInputStream("properties/navigation.properties"));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(NavigationServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void componentStarted() {

    }

    @Override
    public void componentStopped() {

    }

    @Override
    public void open() {

        hostName = properties.getProperty("hostName");
        port = new Integer(properties.getProperty("port"));
        period = new Integer(properties.getProperty("period"));
        start();

    }

    @Override
    public void open(String hostName, int port, int period) {

        this.hostName = hostName;
        this.port = port;
        this.period = period;
        start();

    }

    public void start() {
        vertx = VertxFactory.newVertx();
        vertx.createHttpClient()
                .setHost("localhost")
                .setPort(8989)
                .connectWebsocket("/navigation", new Handler<WebSocket>() {
                    @Override
                    public void handle(WebSocket websocket) {
                        websocket.dataHandler(new Handler<Buffer>() {
                            @Override
                            public void handle(Buffer data) {
                                System.out.println("Received " + data);
                                websocket.writeTextFrame("request");
                            }
                        });
                        websocket.writeTextFrame("hello world");
 
                    }
                });
    }

    @Override
    public void request(int period) {
        this.period = period;

        vertx.setPeriodic(period, (Long timerID) -> {
            try {
                ws.writeTextFrame("request");
            } catch (Throwable e) {
                System.out.println("e " + e);
            }
            // System.out.println("request");
        });

    }

    private void response(String d) {
        System.out.println("server " + d);
    }
}
