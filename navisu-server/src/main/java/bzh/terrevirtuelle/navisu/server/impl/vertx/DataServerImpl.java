package bzh.terrevirtuelle.navisu.server.impl.vertx;

import bzh.terrevirtuelle.navisu.server.impl.serial.impl.jssc.SerialPortReaderImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import bzh.terrevirtuelle.navisu.server.DataServer;
import bzh.terrevirtuelle.navisu.server.DataServerServices;
import bzh.terrevirtuelle.navisu.server.http.impl.HttpServer;
import bzh.terrevirtuelle.navisu.server.impl.Reader;
import bzh.terrevirtuelle.navisu.server.impl.file.FileReader;
import bzh.terrevirtuelle.navisu.server.impl.file.impl.FileReaderImpl;
import bzh.terrevirtuelle.navisu.server.impl.gpsd.NetReader;
import bzh.terrevirtuelle.navisu.server.impl.gpsd.impl.NetReaderImpl;
import bzh.terrevirtuelle.navisu.server.impl.serial.SerialPortReader;
import bzh.terrevirtuelle.navisu.server.nmea.parser.NmeaStringParser;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.capcaval.c3.component.ComponentState;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.ServerWebSocket;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Serge Morvan Date: 18/12/13 Time: 16:01 To
 * change this template use File | Settings | File Templates.
 */
public class DataServerImpl
        implements DataServer, DataServerServices, ComponentState {

    private CircularFifoQueue<String> sentenceQueue;
    private Properties properties;
    public Sentences sentences;
    private NmeaStringParser parser;
    private Vertx vertx;
    private Marshaller marshaller;
    private SerialPortReader serialPortReader;
    private FileReader fileReader;
    private NetReader netReader;
    private String fileName;
    private int port;
    private String hostName;
    private List<Reader> readers;
    private List<CircularFifoQueue<String>> sentenceQueues;
    private int readerIndex = 0;
    private int currentReaderIndex = 0;
    private int queueSize;
    private StringWriter response;
    private StringWriter stringWriter = null;
    protected static final Logger LOGGER = Logger.getLogger(DataServerImpl.class.getName());

    @Override
    public void componentInitiated() {
        properties = new Properties();
        readers = new ArrayList<>();
        sentenceQueues = new ArrayList<>();
        try {
            properties.load(new FileInputStream("properties/server.properties"));
            marshaller = JAXBContext.newInstance(Sentences.class).createMarshaller();
        } catch (IOException | JAXBException ex) {
            Logger.getLogger(DataServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        queueSize = new Integer(properties.getProperty("queueSize").trim());
        sentences = new Sentences();
        parser = new NmeaStringParser(sentences);

    }

    @Override
    public void init() {
        this.hostName = properties.getProperty("hostName").trim();
        this.port = new Integer(properties.getProperty("port").trim());
        initVertx();
    }

    @Override
    public void init(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        initVertx();
    }

    private void initVertx() {
        vertx = VertxFactory.newVertx();
        vertx.createHttpServer().websocketHandler((final ServerWebSocket ws) -> {
            if (ws.path().equals("/nmea")) {
                ws.dataHandler((Buffer data) -> {
                    // Analyse lexicale de la phrase et envoi en xml
                    if (!readers.isEmpty()) {
                        if (readers.get(currentReaderIndex).getClass().getSimpleName().equals("FileReaderImpl")) {
                            readers.get(currentReaderIndex).read();
                        }
                        response = response(currentReaderIndex);
                       // System.out.println("response "+ response);
                        if (response != null) {
                            ws.writeTextFrame(response.toString());
                        }
                        // rotation dans les buffers des readers
                        currentReaderIndex = (currentReaderIndex + 1) % sentenceQueues.size();
                    }
                });
            } else {
                ws.reject();
            }
        }).listen(port, hostName);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
        if (serialPortReader != null) {
            serialPortReader.closePort();
        }
        // fileHandler.close();
    }

    @Override
    public StringWriter response(int currentReader) {
        if (!sentenceQueues.isEmpty()) {
            sentences.clear();
            try {
                sentenceQueues.get(currentReader).stream().forEach((s) -> {
                   //LOGGER.info(s);
                   // System.out.println(s);
                    parser.parse(s.trim());
                });
                stringWriter = new StringWriter();
                if (!sentences.isEmpty()) {
                    marshaller.marshal(sentences, stringWriter);
                   // System.out.println("DataServerImpl stringWriter " + stringWriter);
                }
            } catch (Exception e) {
                // System.out.println("sentenceQueues "+ sentenceQueues);
                 // System.out.println("DataServerImpl Exception" + sentences);
            }
        }
       // System.out.println("DataServerImpl " + stringWriter);
        return stringWriter;
    }

    @Override
    public void openGpsd(String hostname, int port) {
        netReader = new NetReaderImpl(readerIndex, vertx, hostname, port);
        readers.add(netReader);
        initEventBus();
    }

    @Override
    public void openSerialPort() {
        initSerialReader();
        serialPortReader.connect(properties.getProperty("portName").trim(),
                new Integer(properties.getProperty("baudRate").trim()),
                new Integer(properties.getProperty("dataBits").trim()),
                new Integer(properties.getProperty("stopBits").trim()),
                new Integer(properties.getProperty("parity").trim())
        );
    }

    @Override
    public void openSerialPort(String serialPort, int baudRate, int dataBits, int stopBits, int parity) {
        initSerialReader();
        serialPortReader.connect(serialPort, baudRate, dataBits, stopBits, parity);
    }

    private void initSerialReader() {
        serialPortReader = new SerialPortReaderImpl(readerIndex, vertx);
        readers.add(serialPortReader);
        initEventBus();
    }

    @Override
    public void openFile() {
        fileName = properties.getProperty("fileName").trim();
        initFileReader();
    }

    @Override
    public void openFile(String fileName) {
        this.fileName = fileName;
        initFileReader();
    }

    private void initFileReader() {
        fileReader = new FileReaderImpl(readerIndex, vertx, fileName, queueSize);
        readers.add(fileReader);
        initEventBus();
    }

    private void initEventBus() {
        sentenceQueue = new CircularFifoQueue<>(queueSize);
        sentenceQueues.add(sentenceQueue);
        vertx.eventBus().registerHandler("comm-address" + readerIndex, (Message<String> message) -> {
            sentenceQueue.add(message.body());
        });
        readerIndex++;
    }

    @Override
    public void openHttpServer(String hostname, int port) {
        HttpServer httpserver = new HttpServer(vertx, hostname, port);
    }
}

class MyFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return record.getMessage() + "\n";
    }
}
