/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl.gpsd.impl;

import bzh.terrevirtuelle.navisu.server.impl.gpsd.NetReader;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.net.NetSocket;

/**
 *
 * @author Serge
 */
public class NetReaderImpl
        implements NetReader {

    public NetReaderImpl(int index, Vertx vertx, String hostname, int port) {
        vertx.createNetClient().connect(port, hostname, (AsyncResultHandler<NetSocket>) new AsyncResultHandler<NetSocket>() {

            @Override
            public void handle(AsyncResult<NetSocket> asyncResult) {
                if (asyncResult.succeeded()) {
                    NetSocket socket = asyncResult.result();
                    socket.dataHandler((Buffer buffer) -> {
                        String source = buffer.toString().trim();
                        System.out.println(source);
                        if ((source.startsWith("{") && source.endsWith("}")) // Gpsd well formatted
                                || source.startsWith("!") // AIS
                                || source.startsWith("$") // NMEA0183
                                || source.startsWith("PGN")) { // N2K
                            if (source.contains("class")) {//Revoir le parser GPSD, supprimer la negation
                                vertx.eventBus().send("comm-address" + index, source);
                                //System.out.println("source "+ source);
                            }
                        }
                    });
                    socket.write(new Buffer("?WATCH={\"enable\":true,\"json\":true};"));
                }
            }
        });
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
