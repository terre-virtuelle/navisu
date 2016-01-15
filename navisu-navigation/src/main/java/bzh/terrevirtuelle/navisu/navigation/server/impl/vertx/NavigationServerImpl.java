/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.server.impl.vertx;

import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteEditorController;
import bzh.terrevirtuelle.navisu.navigation.server.NavigationServer;
import bzh.terrevirtuelle.navisu.navigation.server.NavigationServerServices;
import bzh.terrevirtuelle.navisu.util.xml.ImportExportXML;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.capcaval.c3.component.ComponentState;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.ServerWebSocket;

/**
 *
 * @author Serge
 */
public class NavigationServerImpl
        implements NavigationServer, NavigationServerServices, ComponentState {

    private Properties properties;
    private Vertx vertx;
    int i = 0;
    private Marshaller marshaller;
    private int port;
    private String hostName;
    private NavigationDataSet navigationDataSet;
    private File file;
    private FileWriter fileWriter;
    protected static final Logger LOGGER = Logger.getLogger(NavigationServerImpl.class.getName());
    private List<Camera> cameraList;
    private Camera camera;

    @Override
    public void componentInitiated() {

    }

    @Override
    public void init() {
        navigationDataSet = new NavigationDataSet();
        cameraList = new ArrayList<>();
        initProperties();
        this.hostName = properties.getProperty("hostName").trim();
        this.port = new Integer(properties.getProperty("port").trim());
        initVertx();
    }

    @Override
    public void init(String hostName, int port) {
        navigationDataSet = new NavigationDataSet();
        cameraList = new ArrayList<>();
        initProperties();
        this.hostName = hostName;
        this.port = port;
        file = new File("data/tests/server.txt");
        initVertx();
    }

    private void initProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/navigation.properties"));
            marshaller = JAXBContext.newInstance(NavigationDataSet.class).createMarshaller();
        } catch (IOException | JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void initVertx() {
        vertx = VertxFactory.newVertx();
        try {
            vertx.createHttpServer().websocketHandler((final ServerWebSocket ws) -> {
                if (ws.path().equals("/navigation")) {
                    ws.dataHandler((Buffer data) -> {
                        try {
                            fileWriter = new FileWriter(file, true);
                            fileWriter.write(response(data).toString());
                            fileWriter.close();
                        } catch (IOException ex) {
                            System.out.println("ex " + ex);
                        }
                        ws.writeTextFrame("ACK");
                    });
                } else {
                    ws.reject();
                }
            }).requestHandler((HttpServerRequest req) -> {
                if (req.path().equals("/")) {
                    req.response().sendFile("data/html/response.html");
                }
            }).listen(8787);
        } catch (Exception e) {
            System.out.println("e " + e);
        }
    }

    private Camera response(Buffer data) {
        try {
            navigationDataSet = new NavigationDataSet();
            navigationDataSet = ImportExportXML.imports(navigationDataSet, new StringReader(data.toString()));
        } catch (JAXBException ex) {
            Logger.getLogger(RouteEditorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cameraList = navigationDataSet.get(Camera.class);
        return cameraList.get(0);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {

    }
}
