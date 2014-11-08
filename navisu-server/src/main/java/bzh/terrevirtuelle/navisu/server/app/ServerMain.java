/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.app;

import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.impl.vertx.DataServerImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.capcaval.c3.componentmanager.ComponentManager;

/**
 *
 * @author Serge Morvan
 * @date 10 juil. 2014 NaVisu project
 */
public class ServerMain {

    private static final Logger LOGGER = Logger.getLogger(ServerMain.class.getName());

    public static void main(String[] args) {
        final ComponentManager componentManager = ComponentManager.componentManager;
        // deploy components
        LOGGER.log(Level.INFO, "\n Start", componentManager.startApplication(
                DataServerImpl.class
        ));
        DataServerServices nmeaServerServices = componentManager.getComponentService(DataServerServices.class);
        // Test avec choix des parametres de comm
       // nmeaServerServices.init("localhost", 8080);
        // nmeaServerServices.openSerialPort("COM4", 4800, 8, 1, 0);
      //  nmeaServerServices.openSerialPort("COM5", 4800, 8, 1, 0);
        // Test avec les parametres de comm dans properties/nmea.properties
         nmeaServerServices.init();
         nmeaServerServices.openFile();
    }
}
