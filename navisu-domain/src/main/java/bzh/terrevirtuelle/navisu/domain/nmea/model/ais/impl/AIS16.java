package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage16;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage16Unit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AIS Message 16 Assigned mode command
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare1
 * 2	( 39, 40) 5	destinationIDA 30	( 41, 70) 6	offsetA 12	( 71, 82) 7	incrementA
 * 10	( 83, 92) 8	destinationIDB 30	( 93, 122) 9	offsetB 12	( 123, 134) 10
 * incrementB 10	( 135, 144) 11	spare2 0 ---- + (maximum) number of bits 144
 */
@XmlRootElement(name = "ais16")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS16
        extends AISMessageImpl
        implements AISMessage16 {

    /**
     *
     */
    public static final int LENGTH_ONEDESTINATION = 96;

    /**
     *
     */
    public static final int LENGTH_TWODESTINATIONS = 144;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (length == LENGTH_ONEDESTINATION)
                || (length == LENGTH_TWODESTINATIONS);
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
        final int available = sb.available();
        if (available >= LENGTH_TWODESTINATIONS) {
            return available - LENGTH_TWODESTINATIONS;
        } else {
            return available - LENGTH_ONEDESTINATION;
        }
    }

    private static final int SPARE1_FROM = 39;
    private static final int SPARE1_TO = 40;

    private int spare1;

    /**
     * spare1
     *
     * @return int value of spare1 (2 bits [39,40])
     */
    public int getSpare1() {
        return spare1;
    }

    private static final int UNITA_FROM = 41;
    private static final int UNITB_FROM = 93;

    private AISMessage16UnitImpl[] units;

    /**
     * units
     *
     * @return Array of AISMessage16Unit containing all units in the AIS Message
     */
    public AISMessage16UnitImpl[] getUnits() {
        return units;
    }

    private static final int SPARE2_FROM = 93;
    private static final int SPARE2_TO = 96;

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (0 or 4 bits {4 bits at [93,96]})
     */
    public int getSpare2() {
        return spare2;
    }

    /**
     * AIS16 default constructor
     */
    public AIS16() {
    }

    /**
     * AISMessage 16 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==16
     */
    public AIS16(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 16);

        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
        if (content.length() == LENGTH_ONEDESTINATION) {
            units = new AISMessage16UnitImpl[1];
            units[0] = new AISMessage16UnitImpl(UNITA_FROM, content);
            spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
        } else {
            assert (content.length() == LENGTH_TWODESTINATIONS);
            units = new AISMessage16UnitImpl[2];
            units[0] = new AISMessage16UnitImpl(UNITA_FROM, content);
            units[1] = new AISMessage16UnitImpl(UNITB_FROM, content);
            spare2 = 0;
        }
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }
    }

    /**
     * Generates a String representing the AIS message. Format: all fields are
     * shown in the order as specified by the standard separated by the
     * SEPARATOR string.
     */
    @Override
    public String toString() {
        String result = super.toString();
        for (AISMessage16Unit unit : units) {
            result += SEPARATOR + unit.toString();
        }
        return result;
    }
}
