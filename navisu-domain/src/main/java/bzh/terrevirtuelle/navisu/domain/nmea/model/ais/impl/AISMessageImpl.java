package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.NMEA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.Annotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISHypothesisAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISInconsistentLengthForTypeAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteMessageIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteRepeatIndicatorAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISNoCompleteUserIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChangedChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChangedMessageIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.ChannelIdAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.NrOfFillBitsAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.AISMessageProvenance;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the base class implementation for all AIS messages. It implements the
 * fields that they all have in common.
 *
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from,  to)
 * ------------------------------------------------------------------------
 *    1         messageID                                   6   (   1,   6)
 *    2         repeatIndicator                             2   (   7,   8)
 *    3         mmsi                                     10   (   9,  38)
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 */
@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class AISMessageImpl
        extends NMEA
        implements AISMessage {

    /**
     * This string is used by this class and its subclasses to separate the
     * different fields in generated strings.
     */
    public static final String SEPARATOR = "|";			//bug prevention: using a string detects a nasty bug (two +'s) which is accepted by a char (but yielding unexpected results)

    /**
     * Extracts the message ID from the content of an AIS message.
     *
     * @param content the content of an AIS message
     * @return integer value of the message ID (6 bits [1,6])
     */
    public static int getMessageID(Sixbit content) {
        return content.getIntFromTo(MESSAGEID_FROM, MESSAGEID_TO);
    }

    /**
     * Changes the message ID from the content of an AIS message.
     *
     * @param content the content of an AIS message
     */
    public static void setMessageID(Sixbit content, int id) {
        content.setIntFromTo(id, MESSAGEID_FROM, MESSAGEID_TO);
    }

    /**
     * The position of the first bit of the message ID.
     */
    private static final int MESSAGEID_FROM = 1;

    /**
     * The position of the last bit of the message ID.
     */
    private static final int MESSAGEID_TO = 6;

    /**
     * The message ID.
     */
    @XmlElement(name = "id")
    private int messageID;

    /**
     * Returns the message ID.
     *
     * @return integer value of message ID (6 bits [1,6])
     */
    public int getMessageID() {
        return messageID;
    }

    /**
     * The position of the first bit of the repeat indicator.
     */
    private static final int REPEATINDICATOR_FROM = 7;

    /**
     * The position of the last bit of the repeat indicator.
     */
    private static final int REPEATINDICATOR_TO = 8;

    /**
     * The repeat indicator
     */
    @XmlElement(name = "ri")
    private int repeatIndicator;

    /**
     * Returns the repeat indicator.
     *
     * @return integer value of repeat indicator (2 bits [7,8])
     */
   
    public int getRepeatIndicator() {
        return repeatIndicator;
    }

    /**
     * The position of the first bit of the user ID.
     */
    private static final int USERID_FROM = 9;

    /**
     * The position of the last bit of the user ID.
     */
    private static final int USERID_TO = 38;

    /**
     * The user ID.
     */
    private int mmsi;

    /**
     * Returns the user ID.
     *
     * @return integer value of user ID (30 bits [9,38])
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
    @XmlElements({
        @XmlElement(name = "aisHA", type = AISHypothesisAnnotation.class),
        @XmlElement(name = "aisILFTA", type = AISInconsistentLengthForTypeAnnotation.class),
        @XmlElement(name = "aisIVA", type = AISIllegalValueAnnotation.class),
        @XmlElement(name = "aisNCMA", type = AISNoCompleteMessageIdAnnotation.class),
        @XmlElement(name = "aisNCRIA ", type = AISNoCompleteRepeatIndicatorAnnotation.class),
        @XmlElement(name = "aisNCUIA", type = AISNoCompleteUserIdAnnotation.class),
        @XmlElement(name = "aisCCIA", type = ChangedChannelIdAnnotation.class),
        @XmlElement(name = "aisCMIA", type = ChangedMessageIdAnnotation.class),
        @XmlElement(name = "aisCIA", type = ChannelIdAnnotation.class),
        @XmlElement(name = "aisNOFBA", type = NrOfFillBitsAnnotation.class)
    })
    protected List<Annotation> annotations = new ArrayList<>();

    /**
     * Add annotation a to annotations of this AISMessage
     *
     * @param a
     */
    public void AddAnnotation(Annotation a) {
        annotations.add(a);
    }

    /**
     * Returns the provenance, i.e., a description of where the AIS message came
     * from, including a time stamp.
     *
     * @return the provenance
     */
    public AISMessageProvenance getProvenance() {
        return new AISMessageProvenance(provenance, annotations);
    }

    /**
     * Constructor, used by subclasses to initialize the fields of this class.
     *
     * @param content the content of an AIS message
     * @param provenance the provenance of the AIS message
     */
    protected AISMessageImpl(Sixbit content, VDMMessageProvenance provenance) {
        messageID = content.getIntFromTo(MESSAGEID_FROM, MESSAGEID_TO);
        repeatIndicator = content.getIntFromTo(REPEATINDICATOR_FROM, REPEATINDICATOR_TO);
        mmsi = content.getIntFromTo(USERID_FROM, USERID_TO);
        this.provenance = provenance;
    }

    public AISMessageImpl() {
    }

    /**
     * Generates a <code>String</code> representing the parsed
     * <code>AISMessage</code>. Format: all fields are shown in the order and
     * units as specified by the standard separated by the
     * <code>SEPARATOR</code> string.
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return Integer.toString(messageID) + SEPARATOR
                + Integer.toString(repeatIndicator) + SEPARATOR
                + Integer.toString(mmsi);
    }

    public void setMmsi(int mmsi) {
        this.mmsi = mmsi;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setRepeatIndicator(int repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }
    
    
}
