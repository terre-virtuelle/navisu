/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleAISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleInvalidInput;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleSensorData;
import java.util.List;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl.HandleVDMMessageImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl.HandleSensorDataImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl.HandleVDMLineImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl.MyAISMessageHandler;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl.MyErrorHandler;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.Provenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.FileSource;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This class supports parsing of AIS messages. It can be used as follows:
 * <ul>
 * <li> Define a class that implements interface {@link HandleAISMessage}. This
 * should do something useful with the AIS messages that it gets.</li>
 * <li> Define a class that implements interface {@link HandleInvalidInput}.
 * This could ignore, or do something useful (like logging or estimating sensor
 * health) with the invalid input.</li>
 * <li> Create an instance of {@link AISParser}, using an instance of the above
 * AIS message handler class.</li>
 * <li> Feed the parser with sensor data, either using the
 * {@link ReadPath#readFile readFile} method (that assumes lines of sensor data
 * according to {@link ReadPath#linePattern}) or piece of sensor data at a time
 * using the {@link #handleSensorData handleSensorData} method. Now each time an
 * AIS message is complete, it will be fed to the AIS message handler.</li>
 * </ul>
 * See the {@link #main main} method for a simple example.
 *
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISParser {

    /**
     * The line handler that will be invoked for each line that is fed to the
     * parser.
     */
    private HandleSensorData lineHandler = null;
    private List<String> lines;
    private FileSource fs = new FileSource(new File("data/annotationstxt"), 1, "", 0.0);
    private Handler handler = null;

    /**
     * Constructs an AIS parser object. This object will use the provided AIS
     * message handler to deal with every AIS message it will find.
     *
     * @param filename
     * @param handler
     * @param aisMessageHandler a handler for AIS messages.
     * @param errorHandler a handler for incorrect input.
     */
    public AISParser(String filename, Handler handler, HandleAISMessage aisMessageHandler, HandleInvalidInput errorHandler) {
        this.handler = handler;
        HandleVDMMessageImpl vdmMessageHandler = new HandleVDMMessageImpl(aisMessageHandler, errorHandler);
        HandleVDMLineImpl vdmLineHandler = new HandleVDMLineImpl(vdmMessageHandler, errorHandler);
        lineHandler = new HandleSensorDataImpl(vdmLineHandler, errorHandler);
        try {
            lines = Files.lines(Paths.get(filename)).collect(Collectors.toList());
        } catch (IOException ex) {
            Logger.getLogger(AISParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("lines.size() " + lines.size());
    }

    public AISParser(Handler handler, HandleAISMessage aisMessageHandler, HandleInvalidInput errorHandler) {
        this.handler = handler;
        HandleVDMMessageImpl vdmMessageHandler = new HandleVDMMessageImpl(aisMessageHandler, errorHandler);
        HandleVDMLineImpl vdmLineHandler = new HandleVDMLineImpl(vdmMessageHandler, errorHandler);
        lineHandler = new HandleSensorDataImpl(vdmLineHandler, errorHandler);
        System.out.println("lines.size() " + lines.size());
    }

    public AISParser(Handler handler) {
        this.handler = handler;
        MyAISMessageHandler aisMessageHandler = new MyAISMessageHandler(handler);
        MyErrorHandler errorHandler = new MyErrorHandler();
        HandleVDMMessageImpl vdmMessageHandler = new HandleVDMMessageImpl(aisMessageHandler, errorHandler);
        HandleVDMLineImpl vdmLineHandler = new HandleVDMLineImpl(vdmMessageHandler, errorHandler);
        lineHandler = new HandleSensorDataImpl(vdmLineHandler, errorHandler);
    }

    /**
     * Read AIS data from Path
     *
     * @param aisPath Path to parse (file or directory)
     */
    public void readPath(String aisPath) {
        ReadPath rp = new ReadPath();
        rp.readPath(aisPath, lineHandler);
    }

    /**
     * Read AIS data from Path
     *
     * @param aisPath Path to parse (file or directory)
     * @param nrFiles (Maximum) Nr Of Files to parse
     */
    public void readPath(String aisPath, long nrFiles) {
        ReadPath rp = new ReadPath();
        rp.readPath(aisPath, lineHandler, nrFiles);
    }

    /**
     * Handles a single input line. The line must be passed together with the
     * provenance information. This is useful for situations where live AIS
     * information arrives spread out over time.
     *
     * @param p the provenance of the input line
     * @param sensorData a string containing a raw AIS message
     */
    public void handleSensorData(Provenance p, String sensorData) {
        lineHandler.handleSensorData(p, sensorData);
    }

    public void parse(String line) {
       // System.out.println("line "+line +"  fs "+fs);
        handleSensorData(fs, line);
    }

    /**
     * Signals that the handling of individual lines is finished. This method
     * should be called after a number of lines are passed to the
     * {@link #handleSensorData(Provenance, String) handleLine} method.
     */
    public void finished() {
        lineHandler.finished();
    }

    public List<String> getLines() {
        return lines;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

}
