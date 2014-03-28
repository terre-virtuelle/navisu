/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.client.nmea.impl.vertx;

import bzh.terrevirtuelle.navisu.client.nmea.NmeaClient;
import bzh.terrevirtuelle.navisu.client.nmea.NmeaClientServices;
import bzh.terrevirtuelle.navisu.client.nmea.eventsProducer.impl.NmeaEventProducerImpl;
import bzh.terrevirtuelle.navisu.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.nmea.model.Sentences;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.SubComponent;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.WebSocket;

/**
 *
 * @author Serge
 */
public class NmeaClientImpl
        implements NmeaClient, NmeaClientServices, ComponentState {

    private WebSocket ws;
    private List<NMEA> list;
    private Sentences sentences;
    private Properties properties;
    private Vertx vertx;
    private Unmarshaller unmarshaller = null;
    private String hostName;
    private int port;
    private int period;
    private boolean xml = false;

    @SubComponent
    protected NmeaEventProducerImpl eventProducer;

    @Override
    public void componentInitiated() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("properties/client.properties"));
            xml = Boolean.valueOf(properties.getProperty("xml"));
            unmarshaller = JAXBContext.newInstance(Sentences.class).createUnmarshaller();
        } catch (IOException | JAXBException ex) {
            java.util.logging.Logger.getLogger(NmeaClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        eventProducer.init();
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
        initVertx();
    }

    @Override
    public void open(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        // this.period = period;
        initVertx();
    }

    private void initVertx() {
        vertx = VertxFactory.newVertx();
        vertx.createHttpClient()
                .setHost(hostName)
                .setPort(port)
                .connectWebsocket("/nmea", (WebSocket websocket) -> {
                    websocket.dataHandler((Buffer data) -> {
                        StringBuilder stringBuilder = new StringBuilder(data.getString(0, data.length()));
                        try {
                            if (xml == true) {
                                response(stringBuilder); //xml data
                            } else {
                                sentences = (Sentences) unmarshaller.unmarshal(new StreamSource(new StringReader(stringBuilder.toString())));
                            }
                            response(sentences);
                        } catch (JAXBException ex) {
                            System.out.println(ex);
                        }
                    });
                    ws = websocket;
                });
    }

    @Override
    public void request(int period) {
        this.period = period;
        vertx.setPeriodic(period, (Long timerID) -> {
            ws.writeTextFrame("request");
        });
    }

    @Override
    public void requestXML(int period) {
        this.period = period;
        xml = true;
        request(period);
    }

    private void response(Sentences sentences) {
        /* With new classe in the domain, create also new Event for diffusion */
        /* Debug mode : comment notifyNMEAEvent, uncomment display */
        notifyNMEAEvent();
        
        // display();
    }

    public void display() {
        list = sentences.getSentences();
        list.stream().forEach((n) -> {
            System.out.println(n);
        });
    }

    private void notifyNMEAEvent() {
        list = sentences.getSentences();
        list.stream().forEach((n) -> {
            eventProducer.notifyNMEAEvent(n);
        });
    }

    private void response(StringBuilder stringBuilder) {
        System.out.println(stringBuilder);
    }
}
