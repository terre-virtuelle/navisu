package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessageAcknowledge;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessageAcknowledgeUnit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;

/**
 * Implementation of AIS Message Acknowledgements.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1           messageID                                   6   (   1,   6)
 *  2           repeatIndicator                             2   (   7,   8)
 *  3           mmsi                                     30   (   9,  38)
 *  4           spare                                       2   (  39,  40)
 *  5           destinationID1                             30   (  41,  70)
 *  6           sequenceNumberForID1                        2   (  71,  72)
 *  7           destinationID2                             30   (  73, 102)
 *  8           sequenceNumberForID2                        2   ( 103, 104)
 *  9           destinationID3                             30   ( 105, 134)
 * 10           sequenceNumberForID3                        2   ( 135, 136)
 * 11           destinationID4                             30   ( 137, 166)
 * 12           sequenceNumberForID4                        2   ( 167, 168)
 *                                                       ---- +
 *                           (maximum) number of bits     168
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
abstract public class AISMessageAcknowledgeImpl
        extends AISMessageImpl
        implements AISMessageAcknowledge {

    private static final int UNIT_SIZE = 32;		// destination UNIT has mmsi (30 bits) and Sequence number (2 bits)

    /**
     *
     */
    public static final int MINLENGTH = 72;

    /**
     *
     */
    public static final int MAXLENGTH = 168;
    // AIS Acknowledge Messages have 3 optional destinations
    // hence: MAXLENGTH == MINLENGTH + 3 * (30+2)

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return ((length == MINLENGTH)
                || (length == MINLENGTH + UNIT_SIZE)
                || (length == MINLENGTH + 2 * UNIT_SIZE)
                || (length == MAXLENGTH));
    }

    /**
     * Return the difference in available and needed bits to parse this sixbit
     * as an AISMessage A positive difference indicates that there are more bits
     * available than needed by the standard.
     *
     * @param sb the sixbit
     * @return the difference in available and needed bits
     */
    public static int differenceInBits(Sixbit sb) {
        final int available = sb.available();
        if (available >= MAXLENGTH) {
            return (available - MAXLENGTH);
        } else if (available >= MINLENGTH) {
            return (available - MINLENGTH) % UNIT_SIZE;
        } else {
            return available - MINLENGTH;
        }
    }

    private static final int SPARE_FROM = 39;
    private static final int SPARE_TO = 40;

    private int spare;

    /**
     * spare
     *
     * @return int value of spare (2 bits [39,40])
     */
    public int getSpare() {
        return spare;
    }

    private static final int UNITS_FROM = 41;

    private AISMessageAcknowledgeUnitImpl[] units;

    /**
     * units
     *
     * @return AISMessageAcknowledgeUnit[] value of units
     */
    public AISMessageAcknowledgeUnitImpl[] getUnits() {
        return units;
    }

    /**
     *
     */
    public AISMessageAcknowledgeImpl() {
    }

    /**
     * AISMessage Acknowledge constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length())
     */
    public AISMessageAcknowledgeImpl(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));

        spare = content.getIntFromTo(SPARE_FROM, SPARE_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare", spare, UtilsSpare.range));
        }
        int nrofUnits = (content.length() - 40) / UNIT_SIZE;
        units = new AISMessageAcknowledgeUnitImpl[nrofUnits];
        for (int i = 0; i < nrofUnits; i++) {
            int fromIndex = UNITS_FROM + i * UNIT_SIZE;
            units[i] = new AISMessageAcknowledgeUnitImpl(fromIndex, content);
        }
    }
}
