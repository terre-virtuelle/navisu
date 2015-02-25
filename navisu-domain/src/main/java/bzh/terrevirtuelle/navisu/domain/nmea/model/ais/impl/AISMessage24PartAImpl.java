package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage24PartA;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsString;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/**
 * This class implements AIS messages of type 24 Part A.
 * <pre>
 * Field Nr     Field Name                              Nr of Bits (from, to )
 * ------------------------------------------------------------------------
 *  1           messageID                                        6 (   1,   6)
 *  2           repeatIndicator                                  2 (   7,   8)
 *  3           mmsi                                          30 (   9,  38)
 *  4           partNumber                                       2 (  39,  40)
 *  5           name                                           120 (  41, 160)
 *                                                            ---- +
 *                                 (maximum) number of bits    160
 * </pre>
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
class AISMessage24PartAImpl extends AIS24 implements AISMessage24PartA {

    /**
     * The length of a valid message of type 24 Part A.
     */
    public static final int LENGTHPartA = 160;

    /**
     * Checks whether the content is a valid message of type 24 Part A. (length
     * & Part number)
     *
     * @param content possible message type 24 A
     * @return true if this could be a valid message of type 24 Part A
     */
    public static boolean valid(Sixbit content) {
        return ((content.length() == LENGTHPartA) && (content.getIntFromTo(PARTNUMBER_FROM, PARTNUMBER_TO) == 0));
    }

    public static boolean isTypeA(Sixbit content) {
        return AIS24.valid(content) && (content.getIntFromTo(PARTNUMBER_FROM, PARTNUMBER_TO) == 0);
    }

    /**
     * Return the difference in available and needed bits to parse this sixbit
     * as an AisMessagePositionReport A positive difference indicates that there
     * are more bits available than needed by the standard.
     *
     * @param sb
     * @return
     */
    public static int differenceInBits(Sixbit sb) {
        return (sb.available() - LENGTHPartA);
    }

    /**
     * The number of the first bit of the name field
     */
    private static final int NAME_FROM = 41;

    /**
     * The number of the last bit of the name field
     */
    private static final int NAME_TO = 160;

    /**
     * The name field
     */
    private String name;

    /**
     * Returns the name field.
     *
     * @return the String value of the name field (120 bits [41,160])
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the part number.
     *
     * @return the part number, which is always 0 for Part A.
     */
    public int getPartNumber() {
        return 0;
    }

    public AISMessage24PartAImpl() {
    }

    /**
     * Constructs an AIS Message 24 Part A.
     *
     * @param content the content of an AIS message
     * @param prov the provenance of the message
     * @precondition valid(content)
     */
    public AISMessage24PartAImpl(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (valid(content));

        /* Parse 24A */
        /* Get the Ship Name, convert to ASCII */
        name = UtilsString.stripAtSigns(content.getStringFromTo(NAME_FROM, NAME_TO));
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {
        return super.toString() + SEPARATOR + "A" + SEPARATOR + getName();
    }
}
