package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage15;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage15Unit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AIS Message 15 Interrogation
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare1
 * 2	( 39, 40) 5	destinationID1 30	( 41, 70) 6	messageID1_1 6	( 71, 76) 7
 * slotOffset1_1 12	( 77, 88) 8	spare2 2	( 89, 90) 9	messageID1_2 6	( 91, 96) 10
 * slotOffset1_2 12	( 97, 108) 11	spare3 2	( 109, 110) 12	destinationID2 30	(
 * 111, 140) 13	messageID2_1 6	( 141, 146) 14	slotOffset2_1 12	( 147, 158) 15
 * spare4 2	( 159, 160) ---- + (maximum) number of bits 160
 */
@XmlRootElement(name = "ais15")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS15
        extends AISMessageImpl
        implements AISMessage15 {

    /**
     *
     */
    public static final int LENGTH_ONEDESTINATION_ONEMESSAGE = 88;

    /**
     *
     */
    public static final int LENGTH_ONEDESTINATION_TWOMESSAGES = 112;	//TODO: check with practice: do length of 108 and 110 also exist?

    /**
     *
     */
    public static final int LENGTH_TWODESTINATIONS = 160;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (length == LENGTH_ONEDESTINATION_ONEMESSAGE)
                || (length == LENGTH_ONEDESTINATION_TWOMESSAGES)
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
        } else if (available >= LENGTH_ONEDESTINATION_TWOMESSAGES) {
            return available - LENGTH_ONEDESTINATION_TWOMESSAGES;
        } else {
            return available - LENGTH_ONEDESTINATION_ONEMESSAGE;
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

    private static final int INTERROGATION1_FROM = 41;

    private static final int SPARE2_FROM = 89;
    private static final int SPARE2_TO = 90;

    private int spare2 = 0;    //default value is zero

    /**
     * spare2
     *
     * @return int value of spare2 (2 bits [89,90])
     */
    public int getSpare2() {
        return spare2;
    }

    private static final int SPARE3_FROM = 109;
    private static final int SPARE3_TO = 110;

    private int spare3 = 0;      //default value is zero

    /**
     * spare3
     *
     * @return int value of spare3 (2 bits [109,110] {or 4?} )
     */
    public int getSpare3() {
        return spare3;
    }

    private static final int INTERROGATION2_FROM = 111;

    private static final int SPARE4_FROM = 159;
    private static final int SPARE4_TO = 160;

    private int spare4 = 0;		//default value is zero 

    /**
     * spare4
     *
     * @return int value of spare4 (2 bits [159,160])
     */
    public int getSpare4() {
        return spare4;
    }

    private AISMessage15UnitImpl[] interrogations;

    /**
     * interrogations
     *
     * @return AISMessage15Unit[] value of interrogations
     */
    public AISMessage15UnitImpl[] getInterrogations() {
        return interrogations;
    }

    /**
     * AIS15 default constructor
     */
    public AIS15() {
    }

    /**
     * AISMessage 15 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==15
     */
    public AIS15(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 15);

        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);

        if (content.length() == LENGTH_ONEDESTINATION_ONEMESSAGE) {
            interrogations = new AISMessage15UnitImpl[1];
            interrogations[0] = new AISMessage15UnitImpl(INTERROGATION1_FROM, 1, content);
        } else if (content.length() == LENGTH_ONEDESTINATION_TWOMESSAGES) {
            interrogations = new AISMessage15UnitImpl[1];
            interrogations[0] = new AISMessage15UnitImpl(INTERROGATION1_FROM, 2, content);
            spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
            spare3 = content.getIntFromTo(SPARE3_FROM, content.length());		// four bits instead of two to get to byte boundary at bit position 112 !
        } else {
            assert (content.length() == LENGTH_TWODESTINATIONS);

            interrogations = new AISMessage15UnitImpl[2];
            interrogations[0] = new AISMessage15UnitImpl(INTERROGATION1_FROM, 2, content);
            interrogations[1] = new AISMessage15UnitImpl(INTERROGATION2_FROM, 1, content);
            spare2 = content.getIntFromTo(SPARE2_FROM, SPARE2_TO);
            spare3 = content.getIntFromTo(SPARE3_FROM, SPARE3_TO);
            spare4 = content.getIntFromTo(SPARE4_FROM, SPARE4_TO);
        }

        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }

        if (!UtilsSpare.isSpareSemanticallyCorrect(spare2)) //default value is zero, so only annotation when really parsed 
        {
            annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
        }

        if (!UtilsSpare.isSpareSemanticallyCorrect(spare3)) //default value is zero, so only annotation when really parsed 
        {
            annotations.add(new AISIllegalValueAnnotation("getSpare3", spare3, UtilsSpare.range));
        }

        if (!UtilsSpare.isSpareSemanticallyCorrect(spare4)) //default value is zero, so only annotation when really parsed 
        {
            annotations.add(new AISIllegalValueAnnotation("getSpar4e", spare4, UtilsSpare.range));
        }
    }
}
