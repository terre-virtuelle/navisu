/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.handlers.HandleAISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISHypothesisAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISInconsistentLengthForTypeAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChangedChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.NrOfFillBitsAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.AISMessageProvenance;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class MyAISMessageHandler implements HandleAISMessage {

    /**
     * The number of AIS messages that were found.
     */
    public long messagesCount = 0;
    /**
     * The number of annotated AIS messages that were found.
     */
    public long annotatedMessagesCount = 0;
    public List<AISMessage> messages = new ArrayList<>();
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
    public Sentences sentences;

    public MyAISMessageHandler(Handler handler) {
        sentences = handler.getSentences();
    }

    /**
     * Handles a single AIS message. In this case, if the message has type 1 the
     * count is incremented.
     *
     * @param message the message to be handled
     */
    @Override
    public void handleAISMessage(AISMessage message) {
       // sentences.add((NMEA) message);
/*
         messages.add(message);
         messagesCount++;
         final AISMessageProvenance provenance = message.getProvenance();
         System.out.println("provenance " + provenance);
         final List<Annotation> annotations = provenance.getAnnotations();
         System.out.println("annotations " + annotations);
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
         }
         }
            
         } else {
         sentences.add((NMEA) message);
         }
         */
        if (message.toString().length() > 20) {
            sentences.add((NMEA) message);
        }
    }

}
