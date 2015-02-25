/**
 * ESI AIS Parser
 *
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 *
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessageIllegal;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteMessageIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteRepeatIndicatorAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteUserIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.AISMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import java.util.ArrayList;
import java.util.List;

//TODO also store content of inconsistent message?
/**
 * AIS Message Inconsistent Class to store AIS Messages that don't adhere to the
 * standard
 */
class AISMessageIllegalImpl 
implements AISMessageIllegal {

    private static final int MESSAGEID_FROM = 1;
    private static final int MESSAGEID_TO = 6;

    private int messageID;

    /**
     * messageID
     *
     * @return int value of messageID (6 bits [1,6])
     */
    public int getMessageID() {
        return messageID;
    }

    private static final int REPEATINDICATOR_FROM = 7;
    private static final int REPEATINDICATOR_TO = 8;

    private int repeatIndicator;

    /**
     * repeatIndicator
     *
     * @return int value of repeatIndicator (2 bits [7,8])
     */
    public int getRepeatIndicator() {
        return repeatIndicator;
    }

    private static final int USERID_FROM = 9;
    private static final int USERID_TO = 38;

    private int mmsi;

    /**
     * mmsi
     *
     * @return int value of mmsi (30 bits [9,38])
     */
    public int getMMSI() {
        return mmsi;
    }

    /**
     * The provenance
     */
    private VDMMessageProvenance provenance;

    /**
     * The annotations
     */
    protected List<Annotation> annotations = new ArrayList<Annotation>();

    /**
     * Returns the provenance, i.e., a description of where the AIS message came
     * from, including a time stamp.
     *
     * @return the provenance
     */
    public AISMessageProvenance getProvenance() {
        return new AISMessageProvenance(provenance, annotations);
    }

    public AISMessageIllegalImpl() {
    }

    /**
     * Constructs an object representing an illegal message.
     *
     * @param content the content of the message
     * @param prov the provenance of the message
     */
    public AISMessageIllegalImpl(Sixbit content, VDMMessageProvenance prov) {
        this.provenance = prov;

        if (content.length() < MESSAGEID_TO) {
            messageID = content.getIntFromTo(MESSAGEID_FROM, content.length());		//TODO: Is this the correct interpretation, or should we provide a range of possible values (depending on the possible values the missing bits could have)
            annotations.add(new AISNoCompleteMessageIdAnnotation(content.length()));
            annotations.add(new AISNoCompleteRepeatIndicatorAnnotation(0));
            annotations.add(new AISNoCompleteUserIdAnnotation(0));
        } else {
            messageID = content.getIntFromTo(MESSAGEID_FROM, MESSAGEID_TO);

            if (content.length() < REPEATINDICATOR_TO) {
                repeatIndicator = content.getIntFromTo(REPEATINDICATOR_FROM, content.length()); //TODO: Is this the correct interpretation, or should we provide a range of possible values (depending on the possible values the missing bits could have)
                annotations.add(new AISNoCompleteRepeatIndicatorAnnotation(content.length() + 1 - REPEATINDICATOR_FROM));
                annotations.add(new AISNoCompleteUserIdAnnotation(0));
            } else {
                repeatIndicator = content.getIntFromTo(REPEATINDICATOR_FROM, REPEATINDICATOR_TO);

                assert (content.length() < USERID_TO);
                mmsi = content.getIntFromTo(USERID_FROM, content.length()); //TODO: Is this the correct interpretation, or should we provide a range of possible values (depending on the possible values the missing bits could have)
                annotations.add(new AISNoCompleteUserIdAnnotation(content.length() + 1 - USERID_FROM));
            }
        }
    }

    /**
     * Generates a String representing the parsed AISMessage Format: all fields
     * are shown in the order and units as specified by the standard separated
     * by SEPARATORs
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return Integer.toString(messageID) + AISMessageImpl.SEPARATOR
                + Integer.toString(repeatIndicator) + AISMessageImpl.SEPARATOR
                + Integer.toString(mmsi) + AISMessageImpl.SEPARATOR;
    }
}
