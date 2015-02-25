package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage18;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.CommunicationState;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Implementation of AIS Message 18: Standard Class B equipment position report.
 * Several fields are covered by the superclass
 * {@link AISMessageClassBPositionReportImpl}. Here are the remaining fields:
 * <pre>
 * Field Nr     Field Name                          NrOf Bits       (from,  to)
 * ----------------------------------------------------------------------------
 * 12           spare2                                         2    ( 140, 141)
 * 13           classBUnitFlag                                 1    ( 142, 142)
 * 14           classBDisplayFlag                              1    ( 143, 143)
 * 15           classBDSCFlag                                  1    ( 144, 144)
 * 16           classBBandFlag                                 1    ( 145, 145)
 * 17           classBMessage22Flag                            1    ( 146, 146)
 * 18           assignedModeFlag                               1    ( 147, 147)
 * 19           raimFlag                                       1    ( 148, 148)
 * 20           communicationStateSelectorFlag                 1    ( 149, 149)
 * 21           communicationState                            19    ( 150, 168)
 *                                                           ---- +
 *                       (maximum) number of bits            168
 * </pre>
 *
 * @author Pierre America
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */
@XmlRootElement(name = "ais18")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS18 extends AISMessageClassBPositionReportImpl implements AISMessage18 {

    /**
     * The first position of the second set of spare bits.
     */
    protected static final int SPARE2_FROM = 140;

    /**
     * The last position of the second set of spare bits. Note that this is
     * different from type 19 messages, and therefore not covered by the
     * superclass {@link AISMessageClassBPositionReportImpl}.
     */
    protected static final int SPARE2_TO = 141;

    /**
     * The position of the class B unit flag.
     */
    private static final int CLASSBUNITFLAG_BITINDEX = 142;

    /**
     * The value of the class B unit flag.
     */
    private boolean classBUnitFlag;

    /**
     * Returns the class B unit flag, which indicates the kind of AIS unit.
     *
     * @return a boolean value representing the class B unit flag: <br>
     * false = Class B SOTDMA unit <br>
     * true = Class B "CS" unit
     */
    public boolean getClassBUnitFlag() {
        return classBUnitFlag;
    }

    /**
     * The position of the class B display flag.
     */
    private static final int CLASSBDISPLAYFLAG_BITINDEX = 143;

    /**
     * The value of the class B display flag.
     */
    private boolean classBDisplayFlag;

    /**
     * Returns the class B display flag, which indicates whether the unit is
     * equipped with an integrated display displaying Message 12 and 14.
     *
     * @return a boolean value representing the class B display flag: <br>
     * false = No display available; not capable of displaying Message 12 and 14
     * <br>
     * true = Equipped with integrated display displaying Message 12 and 14
     */
    public boolean getClassBDisplayFlag() {
        return classBDisplayFlag;
    }

    /**
     * The position of the class B DSC flag.
     */
    private static final int CLASSBDSCFLAG_BITINDEX = 144;

    /**
     * The value of the class B DSC flag.
     */
    private boolean classBDSCFlag;

    /**
     * Returns the class B DSC flag, which indicates whether the unit has the
     * digital selective calling (DSC) function.
     *
     * @return a boolean value representing the class B DSC flag: <br>
     * false = Not equipped with DSC function <br>
     * true = Equipped with DSC function (dedicated or time-shared)
     */
    public boolean getClassBDSCFlag() {
        return classBDSCFlag;
    }

    /**
     * The position of the class B band flag.
     */
    private static final int CLASSBBANDFLAG_BITINDEX = 145;

    /**
     * The value of the class B band flag.
     */
    private boolean classBBandFlag;

    /**
     * Returns the class B band flag, which indicates over which radio bands the
     * unit can communicate.
     *
     * @return a boolean value representing the class B band flag: <br>
     * false = Capable of operating over the upper 525 kHz band of the marine
     * band <br>
     * true = Capable of operating over the whole marine band <br>
     * (irrelevant if "Class B Message 22 flag" is 0)
     */
    public boolean getClassBBandFlag() {
        return classBBandFlag;
    }

    /**
     * The position of the class B Message 22 flag.
     */
    private static final int CLASSBMESSAGE22FLAG_BITINDEX = 146;

    /**
     * The value of the class B Message 22 flag.
     */
    private boolean classBMessage22Flag;

    /**
     * Returns the class B Message 22 flag, which indicates whether the unit
     * supports frequency management via Message 22.
     *
     * @return a boolean value representing the class B Message 22 flag: <br>
     * false = No frequency management via Message 22, operating on AIS1, AIS2
     * only <br>
     * true = Frequency management via Message 22
     */
    public boolean getClassBMessage22Flag() {
        return classBMessage22Flag;
    }

    /**
     * The position of the assigned mode flag. Note that the position of this
     * flag is different for type 19 messages.
     */
    private static final int ASSIGNEDMODEFLAG_BITINDEX = 147;

    /**
     * The position of the RAIM flag. Note that the position of this flag is
     * different for type 19 messages.
     */
    private static final int RAIMFLAG_BITINDEX = 148;

    /**
     * The position of the communication state selector flag.
     */
    private static final int COMMUNICATIONSTATESELECTORFLAG_BITINDEX = 149;

    /**
     * The value of the communication state selector flag.
     */
    private boolean communicationStateSelectorFlag;

    /**
     * Returns the communication state selector flag.
     *
     * @return a boolean value representing the communication state selector
     * flag: <br>
     * false = SOTDMA communication state: {@link #getCommunicationState()} will
     * return an {@link Sotdma} object. <br>
     * true = ITDMA communication state: {@link #getCommunicationState()} will
     * return an {@link Itdma} object. <br>
     * (always true for Class-B "CS" units)
     */
    public boolean getCommunicationStateSelectorFlag() {
        return communicationStateSelectorFlag;
    }

    /**
     * The position of the first bit of the communication state
     */
    private static final int COMMUNICATIONSTATE_FROM = 150;
//	private static final int COMMUNICATIONSTATE_TO = 168;

    /**
     * The communication state
     */
    private CommunicationState communicationState;

    /**
     * Returns the communication state.
     *
     * @return the value of the communication state. Depending on the
     * {@linkplain #getCommunicationStateSelectorFlag communication state selector flag},
     * this may be {@link Itdma} or {@link Sotdma}.
     */
    public CommunicationState getCommunicationState() {
        return communicationState;
    }

    /**
     * The length in bytes that a valid AIS message 18 should have
     */
    public static final int LENGTH = 168;

    /**
     * Checks whether the given number can be the length in bytes of the
     * contents of a valid AIS message 18.
     *
     * @param length an integer value representing the length of a message in
     * bytes
     * @return true if this can be the length of a valid AIS message 18
     */
    public static boolean validLength(int length) {
        return (length == LENGTH);
    }

    /**
     * Return the difference in available and needed bits to parse this sixbit
     * as an AISMessage A positive difference indicates that there are more bits
     * available than needed by the standard.
     *
     * @param sb
     * @return
     */
    public static int differenceInBits(Sixbit sb) {
        return (sb.available() - LENGTH);
    }

    /**
     * AIS18 default constructor
     */
    public AIS18() {
    }

    /**
     * AISMessage 18 constructor
     *
     * @param content AIS content
     * @param prov TODO
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==18
     */
    public AIS18(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 18);

        spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
        classBUnitFlag = content.getBoolean(CLASSBUNITFLAG_BITINDEX);
        classBDisplayFlag = content.getBoolean(CLASSBDISPLAYFLAG_BITINDEX);
        classBDSCFlag = content.getBoolean(CLASSBDSCFLAG_BITINDEX);
        classBBandFlag = content.getBoolean(CLASSBBANDFLAG_BITINDEX);
        classBMessage22Flag = content.getBoolean(CLASSBMESSAGE22FLAG_BITINDEX);
        assignedModeFlag = content.getBoolean(ASSIGNEDMODEFLAG_BITINDEX);
        raimFlag = content.getBoolean(RAIMFLAG_BITINDEX);
        communicationStateSelectorFlag = content.getBoolean(COMMUNICATIONSTATESELECTORFLAG_BITINDEX);
        if (communicationStateSelectorFlag) {
            communicationState = new Itdma(COMMUNICATIONSTATE_FROM, content);
        } else {
            communicationState = new Sotdma(COMMUNICATIONSTATE_FROM, content);
        }
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order and units as specified by the standard separated by
     * the SEPARATOR string.
     */
    @Override
    public String toString() {
        String result = super.toString();
        result += SEPARATOR + (classBUnitFlag ? "CS" : "SOTDMA");
        result += SEPARATOR + (classBDisplayFlag ? "with" : "no") + " display";
        result += SEPARATOR + (classBDSCFlag ? "with" : "no") + " DSC";
        result += SEPARATOR + (classBBandFlag ? "whole" : "upper") + " band";
        result += SEPARATOR + (classBMessage22Flag ? "with" : "no") + " message 22";
        result += SEPARATOR + (assignedModeFlag ? "assigned" : "autonomous") + " mode";
        result += SEPARATOR + "RAIM " + (raimFlag ? "in use" : "not in use");
        result += SEPARATOR + (communicationStateSelectorFlag ? "ITDMA" : "SOTDMA");
        result += SEPARATOR + communicationState.toString();
        return result;
    }
}
