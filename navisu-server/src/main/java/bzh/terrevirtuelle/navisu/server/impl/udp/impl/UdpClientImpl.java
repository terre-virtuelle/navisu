/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.impl.udp.impl;

import bzh.terrevirtuelle.navisu.server.impl.NetReader;

/**
 *
 * @author serge
 * @date Jun 9, 2017
 */
public class UdpClientImpl implements NetReader {

    public UdpClientImpl() {
        /*
        DatagramSocket socket = vertx.createDatagramSocket(new DatagramSocketOptions());
socket.listen(1234, "0.0.0.0", asyncResult -> {
  if (asyncResult.succeeded()) {
    socket.handler(packet -> {
      // Do something with the packet
    });
  } else {
    System.out.println("Listen failed" + asyncResult.cause());
  }
});
         */
        
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
