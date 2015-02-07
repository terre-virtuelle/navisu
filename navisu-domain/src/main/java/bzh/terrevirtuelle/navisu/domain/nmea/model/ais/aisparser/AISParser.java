/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser;

import java.util.List;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.ChangedChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.ChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.NrOfFillBitsAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl.HandleVDMMessageImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.AISHypothesisAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.AISInconsistentLengthForTypeAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.Annotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl.HandleSensorDataImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl.HandleVDMLineImpl;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.AISMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.Provenance;

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

    /**
     * Constructs an AIS parser object. This object will use the provided AIS
     * message handler to deal with every AIS message it will find.
     *
     * @param aisMessageHandler a handler for AIS messages.
     * @param errorHandler a handler for incorrect input.
     */
    public AISParser(HandleAISMessage aisMessageHandler, HandleInvalidInput errorHandler) {
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

    /**
     * Signals that the handling of individual lines is finished. This method
     * should be called after a number of lines are passed to the
     * {@link #handleSensorData(Provenance, String) handleLine} method.
     */
    public void finished() {
        lineHandler.finished();
    }

    /**
     * This is a simple example of how the {@link AISParser} class can be used.
     * This method just counts the annotated AIS messages in a single file or in
     * a whole directory of AIS files. For this purpose it uses a simple private
     * message handler class.
     *
     * @param args a file to be parsed, or a directory of files to be parsed
     */
    public static void main(String[] args) {
        MyAISMessageHandler messageHandler = new MyAISMessageHandler();
        MyErrorHandler errorHandler = new MyErrorHandler();
        AISParser parser = new AISParser(messageHandler, errorHandler);
        //parser.readPath(args[0]);
        parser.readPath("data/ais_0.txt");

        System.out.println("Number of messages " + messageHandler.messagesCount);
        System.out.println("Number of annotated messages " + messageHandler.annotatedMessagesCount + " (" + messageHandler.annotatedMessagesCount / (0.01 * messageHandler.messagesCount) + " %)");

        System.out.println("Number of incorrect nr of fill bits " + messageHandler.nrOfFillBitsAnnotationCount);
        System.out.println("Number of incorrect channelIDs " + messageHandler.channelIDAnnotationCount);
        System.out.println("Number of changed channel ids " + messageHandler.changedChannelIdAnnotationCount);
        System.out.println("Number of illegal values " + messageHandler.illegalValueAnnotationCount);
        System.out.println("Number of inconsistent lengths for type = " + messageHandler.inconsistentLengthForTypeAnnotationCount);
        System.out.println("Number of hypothesis used = " + messageHandler.AISHypothesisAnnotationCount);
        System.out.println("Number of other annotations " + messageHandler.otherCount);
    }

    /**
     * This private class illustrates how an AIS message handler can be defined.
     *
     * @author Pierre America
     * @author Pierre van de Laar
     */
    static private class MyAISMessageHandler implements HandleAISMessage {

        /**
         * The number of AIS messages that were found.
         */
        public long messagesCount = 0;
        /**
         * The number of annotated AIS messages that were found.
         */
        public long annotatedMessagesCount = 0;

        /**
         * specific annotation counts
         */
        public long nrOfFillBitsAnnotationCount = 0;
        public long channelIDAnnotationCount = 0;
        public long illegalValueAnnotationCount = 0;
        public long changedChannelIdAnnotationCount = 0;
        public long inconsistentLengthForTypeAnnotationCount = 0;
        public long AISHypothesisAnnotationCount = 0;
        public long otherCount = 0;

        /**
         * Handles a single AIS message. In this case, if the message has type 1
         * the count is incremented.
         *
         * @param message the message to be handled
         */
        public void handleAISMessage(AISMessage message) {
            System.out.println(message);
            messagesCount++;
            final AISMessageProvenance provenance = message.getProvenance();
            final List<Annotation> annotations = provenance.getAnnotations();
            if (annotations.size() > 0) {
                annotatedMessagesCount++;
                for (Annotation annotation : annotations) {
                    if (annotation instanceof NrOfFillBitsAnnotation) {
                        nrOfFillBitsAnnotationCount++;
                    } else if (annotation instanceof AISIllegalValueAnnotation) {
                        illegalValueAnnotationCount++;
                    } else if (annotation instanceof ChannelIdAnnotation) {
                        channelIDAnnotationCount++;
                    } else if (annotation instanceof ChangedChannelIdAnnotation) {
                        changedChannelIdAnnotationCount++;
                    } else if (annotation instanceof AISInconsistentLengthForTypeAnnotation) {
                        inconsistentLengthForTypeAnnotationCount++;
                    } else if (annotation instanceof AISHypothesisAnnotation) {
                        AISHypothesisAnnotationCount++;
                    } else {
                        otherCount++;

                        System.out.print(provenance.getProvenanceTree(""));
                        System.out.println("* " + annotation.toString());
                    }
                }
            }
        }
    }

    static private class MyErrorHandler implements HandleInvalidInput {

        @Override
        public void handleInvalidVDMMessage(VDMMessage invalidVDMMessage) {
            //System.err.println("Error VDM Message : "+invalidVDMMessage.getProvenance().getProvenanceTree(""));		
        }

        @Override
        public void handleInvalidVDMLine(VDMLine invalidVDMLine) {
            //System.err.println("Error VDM Line : "+ invalidVDMLine.getProvenance().getProvenanceTree(""));		
        }

        @Override
        public void handleInvalidSensorData(Provenance source, String sensorData) {
            //System.err.println("Error sensor data : "+ sensorData);			
        }
    }
}
