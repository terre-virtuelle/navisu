/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISHypothesisAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.annotations.impl.AISInconsistentLengthForTypeAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;

/**
 *
 * @author serge
 */
public class AISMessageFactory {

    /**
     * Is valid VDMMessage, such that an AIS Message can be extracted from it?
     *
     * @param message
     * @return is valid VDMMessage?
     */
    static public boolean IsValid(VDMMessage message) {
        return message.atEnd() && message.isComplete();
    }

    /**
     * Returns AISMessage that adheres to the specifications based on
     * MessageType and Length
     *
     * @param sb
     * @param provenance
     * @return
     */
    static private AISMessageImpl getAISMessageWithinSpecification(Sixbit sb, VDMMessageProvenance provenance) {
                
        int messageid = AISMessageImpl.getMessageID(sb);
        switch (messageid) {
            case 1:
                if (AIS01.validLength(sb.length())) {
                    return new AIS01(sb, provenance);
                }
                break;
            case 2:
                if (AIS02.validLength(sb.length())) {
                    return new AIS02(sb, provenance);
                }
                break;
            case 3:
                if (AIS03.validLength(sb.length())) {
                    return new AIS03(sb, provenance);
                }
                break;
            case 4:
                if (AIS04.validLength(sb.length())) {
                    return new AIS04(sb, provenance);
                }
                break;
            case 5:
                if (AIS05.validLength(sb.length())) {
                    return new AIS05(sb, provenance);
                }
                break;
            case 6:
                if (AIS06.validLength(sb.length())) {
                    return new AIS06(sb, provenance);
                }
                break;
            case 7:
                if (AIS07.validLength(sb.length())) {
                    return new AIS07(sb, provenance);
                }
                break;
            case 8:
                if (AIS08.validLength(sb.length())) {
                    return new AIS08(sb, provenance);
                }
                break;
            case 9:
                if (AIS09.validLength(sb.length())) {
                    return new AIS09(sb, provenance);
                }
                break;
            case 10:
                if (AIS10.validLength(sb.length())) {
                    return new AIS10(sb, provenance);
                }
                break;
            case 11:
                if (AIS11.validLength(sb.length())) {
                    return new AIS11(sb, provenance);
                }
                break;
            case 12:
                if (AIS12.validLength(sb.length())) {
                    return new AIS12(sb, provenance);
                }
                break;
            case 13:
                if (AIS13.validLength(sb.length())) {
                    return new AIS13(sb, provenance);
                }
                break;
            case 14:
                if (AIS14.validLength(sb.length())) {
                    return new AIS14(sb, provenance);
                }
                break;
            case 15:
                if (AIS15.validLength(sb.length())) {
                    return new AIS15(sb, provenance);
                }
                break;
            case 16:
                if (AIS16.validLength(sb.length())) {
                    return new AIS16(sb, provenance);
                }
                break;
            case 17:
                if (AIS17.validLength(sb.length())) {
                    return new AIS17(sb, provenance);
                }
                break;
            case 18:
                if (AIS18.validLength(sb.length())) {
                    return new AIS18(sb, provenance);
                }
                break;
            case 19:
                if (AIS19.validLength(sb.length())) {
                    return new AIS19(sb, provenance);
                }
                break;
            case 20:
                if (AIS20.validLength(sb.length())) {
                    return new AIS20(sb, provenance);
                }
                break;
            case 21:
                if (AIS21.validLength(sb.length())) {
                    return new AIS21(sb, provenance);
                }
                break;
            case 22:
                if (AIS22.validLength(sb.length())) {
                    return new AIS22(sb, provenance);
                }
                break;
            case 23:
                if (AIS23.validLength(sb.length())) {
                    return new AIS23(sb, provenance);
                }
                break;
            case 24: {
                // Distinguish between the different part numbers:
                if (AISMessage24PartAImpl.valid(sb)) {
                    return new AISMessage24PartAImpl(sb, provenance);
                } else if (AISMessage24PartBImpl.valid(sb)) {
                    return new AISMessage24PartBImpl(sb, provenance);
                }
                break;
            }
            case 25:
                if (AIS25.valid(sb)) {
                    return new AIS25(sb, provenance);
                }
                break;
            case 26:
                if (AIS26.valid(sb)) {
                    return new AIS26(sb, provenance);
                }
                break;
            case 27:
                if (AIS27.validLength(sb.length())) {
                    return new AIS27(sb, provenance);
                }
                break;
        }
        return null;
    }

    /**
     *
     * @param message
     * @param sb
     * @param provenance
     * @param annotation
     * @return
     */
    public static AISMessageImpl AssumeMessageIdIsCorrect(VDMMessage message, Sixbit sb, VDMMessageProvenance provenance, AISInconsistentLengthForTypeAnnotation annotation) {

        /* We assume messageId as specified by message is OK
         * We change length of message to adhere to the type's length
         */
        AISMessageImpl retval;

        int assumedMessageId = AISMessageImpl.getMessageID(sb);
        switch (assumedMessageId) {
            case 1: {
                int diff = AIS01.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS01.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS01(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 2: {
                int diff = AIS02.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS02.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS02(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 3: {
                int diff = AIS03.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS03.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS03(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 4: {
                int diff = AIS04.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS04.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS04(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 5: {
                int diff = AIS05.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS05.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS05(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 6: {
                int diff = AIS06.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS06.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS06(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 7: {
                int diff = AIS07.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS07.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS07(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 8: {
                int diff = AIS08.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS08.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS08(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 9: {
                int diff = AIS09.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS09.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS09(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 10: {
                int diff = AIS10.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS10.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS10(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 11: {
                int diff = AIS11.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS11.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS11(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 12: {
                int diff = AIS12.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS12.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS12(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 13: {
                int diff = AIS13.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS13.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS13(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 14: {
                int diff = AIS14.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS14.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS14(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 15: {
                int diff = AIS15.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS15.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS15(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 16: {
                int diff = AIS16.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS16.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS16(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 17: {
                int diff = AIS17.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS17.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS17(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 18: {
                int diff = AIS18.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS18.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS18(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 19: {
                int diff = AIS19.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS19.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS19(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 20: {
                int diff = AIS20.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS20.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS20(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 21: {
                int diff = AIS21.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS21.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS21(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 22: {
                int diff = AIS22.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS22.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS22(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 23: {
                int diff = AIS23.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS23.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS23(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 24: {
                if (AISMessage24PartAImpl.isTypeA(sb)) {
                    int diff = AISMessage24PartAImpl.differenceInBits(sb);
                    assert (diff != message.getNrOfFillBits());		// message violates standard
                    if (diff >= 0) {
                        sb.setNrOfFillBits(diff);
                        assert (AISMessage24PartAImpl.valid(sb));	// message changed to adhere to standard
                        retval = new AISMessage24PartAImpl(sb, provenance);
                        retval.AddAnnotation(annotation);
                        retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                    } else {
                        //no hypothesis
                        retval = new AISMessageImpl(sb, provenance);
                        retval.AddAnnotation(annotation);
                    }
                } else if (AISMessage24PartBImpl.isTypeB(sb)) {
                    int diff = AISMessage24PartBImpl.differenceInBits(sb);
                    assert (diff != message.getNrOfFillBits());		// message violates standard
                    if (diff >= 0) {
                        sb.setNrOfFillBits(diff);
                        assert (AISMessage24PartBImpl.valid(sb));	// message changed to adhere to standard
                        retval = new AISMessage24PartBImpl(sb, provenance);
                        retval.AddAnnotation(annotation);
                        retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                    } else {
                        //no hypothesis
                        retval = new AISMessageImpl(sb, provenance);
                        retval.AddAnnotation(annotation);
                    }

                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 25: {
                int diff = AIS25.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS25.valid(sb));	// message changed to adhere to standard
                    retval = new AIS25(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 26: {
                int diff = AIS26.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS26.valid(sb));	// message changed to adhere to standard
                    retval = new AIS26(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            case 27: {
                int diff = AIS27.differenceInBits(sb);
                assert (diff != message.getNrOfFillBits());		// message violates standard
                if (diff >= 0) {
                    sb.setNrOfFillBits(diff);
                    assert (AIS27.validLength(sb.length()));	// message changed to adhere to standard
                    retval = new AIS27(sb, provenance);
                    retval.AddAnnotation(annotation);
                    retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
                } else {
                    //no hypothesis
                    retval = new AISMessageImpl(sb, provenance);
                    retval.AddAnnotation(annotation);
                }
                break;
            }
            default: {
                return new AISMessageUnknownMessageIdImpl(sb, provenance);
            }
        }
        assert (retval != null);
        return retval;
    }

    private static AISMessageImpl GuessMessageIdBasedOnLength(Sixbit sb, VDMMessageProvenance provenance, AISInconsistentLengthForTypeAnnotation annotation) {
        AISMessageImpl retval = null;

        switch (sb.length()) {
            case 168: {
                /* AisMessagePositionReport is most occurring message. 
                 * We assume that a message outside the specification with this length is a PositionReport
                 * We have chosen type 01 as implementation there of 
                 */
                int assumedMessageId = 1;
                AISMessageImpl.setMessageID(sb, assumedMessageId);
                assert (AISMessageImpl.getMessageID(sb) == assumedMessageId);
                retval = new AIS01(sb, provenance);
                retval.AddAnnotation(annotation);
                retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 168));
                break;
            }
            case 312: {
                /* Message of type 19 have the relatively unique length of 312
                 * We assume that a message outside the specification with this length is of Type 19
                 */
                int assumedMessageId = 19;
                AISMessageImpl.setMessageID(sb, assumedMessageId);
                assert (AISMessageImpl.getMessageID(sb) == assumedMessageId);
                retval = new AIS19(sb, provenance);
                retval.AddAnnotation(annotation);
                retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 312));
                break;
            }
            case 424: {
                /* Message of type 5 have the relatively unique length of 424
                 * We assume that a message outside the specification with this length is of Type 05
                 */
                int assumedMessageId = 5;
                AISMessageImpl.setMessageID(sb, assumedMessageId);
                assert (AISMessageImpl.getMessageID(sb) == assumedMessageId);
                retval = new AIS05(sb, provenance);
                retval.AddAnnotation(annotation);
                retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 424));
                break;
            }
        }
        return retval;
    }

    private static AISMessageImpl GuessPartIdBasedOnLength(Sixbit sb, VDMMessageProvenance provenance, AISInconsistentLengthForTypeAnnotation annotation) {
        AISMessageImpl retval = null;

        int messageid = AISMessageImpl.getMessageID(sb);
        if (messageid == 24) {
            if (sb.length() == AISMessage24PartAImpl.LENGTHPartA) {
                AIS24.setPart(sb, 0);
                assert (AISMessage24PartAImpl.valid(sb));	// message changed to adhere to standard
                retval = new AISMessage24PartAImpl(sb, provenance);
                retval.AddAnnotation(annotation);
                retval.AddAnnotation(new AISHypothesisAnnotation(messageid, sb.length()));	//TODO: include partId in hypothesis
            } else if (sb.length() == AISMessage24PartBImpl.LENGTHPartB) {
                AIS24.setPart(sb, 1);
                assert (AISMessage24PartBImpl.valid(sb));	// message changed to adhere to standard
                retval = new AISMessage24PartBImpl(sb, provenance);
                retval.AddAnnotation(annotation);
                retval.AddAnnotation(new AISHypothesisAnnotation(messageid, sb.length())); //TODO: include partId in hypothesis
            }
        }
        return retval;
    }

    /**
     *
     * @param message
     * @return
     */
    static public AISMessage getAISMessage(VDMMessage message) {

        Sixbit sb = new Sixbit(message.getMessage(), message.getNrOfFillBits());
        VDMMessageProvenance provenance = message.getProvenance();
        if (sb.length() < 38) {
            //Message too short according to standard for any message type
            return new AISMessageIllegalImpl(sb, provenance); // the errors are logged here
        } else {

            AISMessageImpl retval = null;
            retval = getAISMessageWithinSpecification(sb, provenance);
            if (retval == null) {
                /* What to do with message outside specification?
                 * Anything can be wrong: including the messageId, the NrOfFillbits, and the length.
                 * How to correct this error?
                 * 
                 * We use the strategy
                 * 1. Can PartId made consistent with length and messageId? 
                 * 2. Classify based on uniqueness of length and frequency of occurrence of particular messages. 
                 * 3. Assume messageId is correct, when more bits (incl. fill bits) are available than needed, parse message (assumption: we should ignore the trailing bits [and not the first bits])
                 * 4. TODO: Assume messageId is correct, when bits are missing, either parse conditionally or add "default" bits. (last option seems preferable: least changes to interface)
                 */
                int originalMessageId = AISMessageImpl.getMessageID(sb);
                int originalLength = sb.length();
                AISInconsistentLengthForTypeAnnotation annotation = new AISInconsistentLengthForTypeAnnotation(originalMessageId, originalLength);

                retval = GuessPartIdBasedOnLength(sb, provenance, annotation);
                if (retval == null) {
                    retval = GuessMessageIdBasedOnLength(sb, provenance, annotation);
                    if (retval == null) {
                        retval = AssumeMessageIdIsCorrect(message, sb, provenance, annotation);
                    }
                }
                assert (retval != null);
            }
            return retval;
        }
    }
}
