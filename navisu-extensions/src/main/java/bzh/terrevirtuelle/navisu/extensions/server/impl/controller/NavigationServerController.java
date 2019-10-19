/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.server.impl.controller;

import bzh.terrevirtuelle.navisu.agents.ship.ShipAgentServices;
import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layers.LayersManagerServices;
import bzh.terrevirtuelle.navisu.bathymetry.db.BathymetryDBServices;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.extensions.commands.Command;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmdComponentServices;
import bzh.terrevirtuelle.navisu.kml.KmlComponentServices;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.ServerWebSocket;

/**
 *
 * @author serge
 * @date Jan 14, 2016
 *
 */
public class NavigationServerController {

    protected static final Logger LOGGER = Logger.getLogger(NavigationServerController.class.getName());
    protected static NavigationServerController INSTANCE;

    protected NavigationTcpServerController navigationTcpServerController;

    protected NavigationCmdComponentServices navigationCmdComponentServices;
    protected LayersManagerServices layersManagerServices;
    protected GuiAgentServices guiAgentServices;
    protected NavigationDataSet navigationDataSet;
    protected ShipAgentServices shipAgentServices;
    protected KmlComponentServices kmlComponentServices;
    protected BathymetryDBServices bathymetryDBServices;

    private Properties properties;
    private final String PROPERTIES_NAME = "properties/navigation.properties";
    private final String START_CMD = "/navigation";
    private final String HTML_RESPONSE = "data/html/response.html";
    private final String HTML_RESPONSE_CMD = "/";

    private Vertx cmdVertx;
    private int port;
    private Command command;

    private NavigationServerController(ShipAgentServices shipAgentServices,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            NavigationCmdComponentServices navigationCmdComponentServices,
            KmlComponentServices kmlComponentServices,
            BathymetryDBServices bathymetryDBServices) {
        this.shipAgentServices = shipAgentServices;
        this.layersManagerServices = layersManagerServices;
        this.guiAgentServices = guiAgentServices;
        this.navigationCmdComponentServices = navigationCmdComponentServices;
        this.bathymetryDBServices = bathymetryDBServices;
        initProperties();
    }

    public static NavigationServerController getInstance(
            ShipAgentServices shipAgentServices,
            LayersManagerServices layersManagerServices,
            GuiAgentServices guiAgentServices,
            NavigationCmdComponentServices navigationCmdComponentServices,
            KmlComponentServices kmlComponentServices,
            BathymetryDBServices bathymetryDBServices) {
        if (INSTANCE == null) {
            INSTANCE = new NavigationServerController(shipAgentServices,
                    layersManagerServices, guiAgentServices,
                    navigationCmdComponentServices, kmlComponentServices,
                    bathymetryDBServices);
        }
        return INSTANCE;
    }

    public void init() {
        this.port = new Integer(properties.getProperty("port").trim());
        initServer();
    }

    public void init(int port) {
        this.port = port;
        initServer();
    }

    public void initTcpServer(int port) {
        this.port = port;
        navigationTcpServerController = new NavigationTcpServerController(shipAgentServices,
                layersManagerServices,
                bathymetryDBServices,
                kmlComponentServices);
        guiAgentServices.getJobsManager().newJob("", new Job() {

            @Override
            public void run(ProgressHandle progressHandle) {
                try (ServerSocket serverSocket = new ServerSocket(port)) {

                    System.out.println("Server is listening on port " + port);
                    char[] buffer = new char[50];
                    char c;
                    while (true) {
                        Socket socket = serverSocket.accept();
                        System.out.println("New client connected");
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        String text = null;
                        do {
                              reader.read(buffer);
                            for (char b : buffer) {
                                // convert byte to character
                                c = (char) b;
                                // prints character
                                System.out.print(c);
                            }

                            //  System.out.println("text : "+ text);
                            if (text != null) {
                                String[] dataTab = text.trim().split(",");
                                double lat = Double.valueOf(dataTab[0].trim());
                                double lon = Double.valueOf(dataTab[1].trim());
                                //  System.out.println(lat + " "+lon);
                                navigationTcpServerController.doIt(lat, lon);
                            }
                        } while (true);
                    }
                } catch (IOException ex) {
                    System.out.println("Server exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }

    private void initProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_NAME));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private void initServer() {
        cmdVertx = VertxFactory.newVertx();
        try {
            cmdVertx.createHttpServer().websocketHandler((final ServerWebSocket ws) -> {
                if (ws.path().equals(START_CMD)) {
                    ws.dataHandler((Buffer data) -> {
                        //System.out.println("data : " + data);
                        command = command(data.toString());
                        if (command != null) {
                            if (command.getNavigationData() != null && command.getArg() == null) {
                                navigationDataSet = navigationCmdComponentServices.doIt(command.getCmd(), command.getNavigationData());
                            }
                            if (command.getNavigationData() != null && command.getArg() != null) {
                                navigationDataSet = navigationCmdComponentServices.doIt(command.getCmd(), command.getNavigationData(), command.getArg());
                            }
                            if (command.getArg() != null) {
                                navigationDataSet = navigationCmdComponentServices.doIt(command.getCmd(), command.getArg());
                            }
                            if (navigationDataSet != null) {
                                if (navigationDataSet.size() > 0) {
                                    String r = response(navigationDataSet);
                                    ws.writeTextFrame(r);
                                } else {
                                    ws.writeTextFrame("ACK");
                                }
                            }
                        } else {
                            ws.writeTextFrame("NACK");
                        }
                    });
                } else {
                    // System.out.println("reject");
                    ws.reject();
                }
            }).requestHandler((HttpServerRequest req) -> {
                if (req.path().equals(HTML_RESPONSE_CMD)) {
                    req.response().sendFile(HTML_RESPONSE);
                }
            }).listen(port);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
    }

    private Command command(String data) {
        Command navCmd = null;
        try {
            navCmd = new Command();
            navCmd = ImportExportXML.imports(navCmd, new StringReader(data));
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }
        return navCmd;
    }

    private String response(NavigationDataSet response) {
        StringWriter xmlString = new StringWriter();
        try {
            ImportExportXML.exports(response, xmlString);
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, ex.toString(), ex);
        }

        //TODO
        return xmlString.toString();
    }

    public void setNavigationCmdComponentServices(NavigationCmdComponentServices navigationCmdComponentServices) {
        this.navigationCmdComponentServices = navigationCmdComponentServices;
    }

}
