/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.extensions.server.impl.controller;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.domain.navigation.model.NavigationDataSet;
import bzh.terrevirtuelle.navisu.extensions.commands.Command;
import bzh.terrevirtuelle.navisu.extensions.commands.NavigationCmdComponentServices;
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
    private static NavigationServerController INSTANCE;
    private NavigationCmdComponentServices navigationCmdComponentServices;
    protected GuiAgentServices guiAgentServices;
    private NavigationDataSet navigationDataSet;

    private Properties properties;
    private final String PROPERTIES_NAME = "properties/navigation.properties";
    private final String START_CMD = "/navigation";
    private final String HTML_RESPONSE = "data/html/response.html";
    private final String HTML_RESPONSE_CMD = "/";

    private Vertx cmdVertx;
    private int port;
    private Command command;

    private NavigationServerController(GuiAgentServices guiAgentServices,
            NavigationCmdComponentServices navigationCmdComponentServices) {
        this.guiAgentServices = guiAgentServices;
        this.navigationCmdComponentServices = navigationCmdComponentServices;
        initProperties();
    }

    public static NavigationServerController getInstance(
            GuiAgentServices guiAgentServices,
            NavigationCmdComponentServices navigationCmdComponentServices) {
        if (INSTANCE == null) {
            INSTANCE = new NavigationServerController(guiAgentServices, navigationCmdComponentServices);
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
        guiAgentServices.getJobsManager().newJob("Server is listening on port " + port, new Job() {

            @Override
            public void run(ProgressHandle progressHandle) {
                try (ServerSocket serverSocket = new ServerSocket(port)) {

                    System.out.println("Server is listening on port " + port);

                    while (true) {
                        Socket socket = serverSocket.accept();

                        System.out.println("New client connected");
                        InputStream input = socket.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        String text;
                        do {
                            text = reader.readLine();
                            System.out.println(text);
                        } while (!text.equals("bye"));
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
