/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.server.impl.controller;

import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartComponentServices;
import bzh.terrevirtuelle.navisu.domain.camera.model.Camera;
import bzh.terrevirtuelle.navisu.domain.navigation.NavigationDataSet;
import bzh.terrevirtuelle.navisu.instruments.camera.CameraComponentServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RouteEditorController;
import bzh.terrevirtuelle.navisu.navigation.server.impl.vertx.NavigationServerImpl;
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
import org.capcaval.c3.component.annotation.UsedService;
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

    protected static final Logger LOGGER = Logger.getLogger(NavigationServerImpl.class.getName());
    private static NavigationServerController INSTANCE;
    
    private CameraComponentServices cameraComponentServices;
    private S57ChartComponentServices s57ChartComponentServices;

    private Properties properties;
    private final String PROPERTIES_NAME = "properties/navigation.properties";
    private final String START_CMD = "/navigation";
    private final String HTML_RESPONSE = "data/html/response.html";
    private final String HTML_RESPONSE_CMD = "/";

    private Vertx vertx;
    private Marshaller marshaller;
    private int port;
    private NavigationDataSet navigationDataSet;

    private List<Camera> cameraList;
    private Camera camera;
    
    private NavigationServerController() {
        navigationDataSet = new NavigationDataSet();
        cameraList = new ArrayList<>();
        initProperties();
    }

    public static NavigationServerController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NavigationServerController();
        }
        return INSTANCE;
    }

    public void init() {
        this.port = new Integer(properties.getProperty("port").trim());
        initVertx();
    }

    public void init(int port) {
        this.port = port;
        initVertx();
    }

    private void initProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_NAME));
            marshaller = JAXBContext.newInstance(NavigationDataSet.class).createMarshaller();
        } catch (IOException | JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void initVertx() {
        vertx = VertxFactory.newVertx();
        try {
            vertx.createHttpServer().websocketHandler((final ServerWebSocket ws) -> {
                if (ws.path().equals(START_CMD)) {
                    ws.dataHandler((Buffer data) -> {
                        camera = command(data.toString());
                        if (camera != null) {
                            cameraComponentServices.updateTarget(camera);
                            ws.writeTextFrame("ACK");
                        } else {
                            ws.writeTextFrame("NACK");
                        }
                    });
                } else {
                    ws.reject();
                }
            }).requestHandler((HttpServerRequest req) -> {
                if (req.path().equals(HTML_RESPONSE_CMD)) {
                    req.response().sendFile(HTML_RESPONSE);
                }
            }).listen(port);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private Camera command(String data) {
        Camera tmp = null;
        try {
            navigationDataSet = new NavigationDataSet();
            navigationDataSet = ImportExportXML.imports(navigationDataSet, new StringReader(data));
        } catch (JAXBException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        cameraList = navigationDataSet.get(Camera.class);
        if (cameraList.size() >= 1) {
            tmp = cameraList.get(0);
        }
        return tmp;
    }

    private String response() {
        return "";
    }

    public void setCameraComponentServices(CameraComponentServices cameraComponentServices) {
        this.cameraComponentServices = cameraComponentServices;
    }

    public void setS57ChartComponentServices(S57ChartComponentServices s57ChartComponentServices) {
        this.s57ChartComponentServices = s57ChartComponentServices;
    }

}
