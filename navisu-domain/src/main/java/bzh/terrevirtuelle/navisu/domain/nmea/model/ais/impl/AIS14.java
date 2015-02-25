package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage14;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * AIS Message 14 Safety Related Broadcast
 *
 * Field Nr Field Name NrOf Bits (from, to )
 * ------------------------------------------------------------------------ 1
 * messageID 6	( 1, 6) 2	repeatIndicator 2	( 7, 8) 3	mmsi 30	( 9, 38) 4	spare1
 * 2	( 39, 40) 5	safetyRelatedText 966	( 41,1006) 6	spare2 2	(1007,1008) ---- +
 * (maximum) number of bits 1008
 */
@XmlRootElement(name = "ais14")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS14 extends AISMessageImpl implements AISMessage14 {

    /**
     *
     */
    public static final int MINLENGTH = 40;

    /**
     *
     */
    public static final int MAXLENGTH = 1008;

    /**
     *
     * @param length
     * @return
     */
    public static boolean validLength(int length) {
        return (MINLENGTH <= length && length <= MAXLENGTH);		//TODO enforce modulo 6: six bit ASCII text
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
        return (sb.available() - MAXLENGTH);
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

    private static final int SAFETYRELATEDTEXT_FROM = 41;

    private String safetyRelatedText;

    /**
     * safetyRelatedText
     *
     * @return String value of safetyRelatedText [maximally 161 6-bit ASCII
     * characters] (966 bits [41,1006])
     */
    public String getSafetyRelatedText() {
        return safetyRelatedText;
    }

    private int spare2;

    /**
     * spare2
     *
     * @return int value of spare2 (2 bits [1007,1008])
     */
    public int getSpare2() {
        return spare2;
    }

    /**
     *  AIS14 default constructor
     */
    public AIS14() {
    }

    /**
     * AISMessage 14 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)==14
     */
    public AIS14(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (validLength(content.length()));
        assert (getMessageID() == 14);

        spare1 = content.getIntFromTo(SPARE1_FROM, SPARE1_TO);
        if (!UtilsSpare.isSpareSemanticallyCorrect(spare1)) {
            annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
        }
        int nrofSpare = (content.length() - SAFETYRELATEDTEXT_FROM + 1) % Sixbit.BITSNEEDEDFORCHAR;
        safetyRelatedText = content.getStringFromTo(SAFETYRELATEDTEXT_FROM, content.length() - nrofSpare);
        spare2 = content.getIntFromTo(content.length() - nrofSpare, content.length());
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
        return super.toString() + SEPARATOR + getSafetyRelatedText();
    }
}
