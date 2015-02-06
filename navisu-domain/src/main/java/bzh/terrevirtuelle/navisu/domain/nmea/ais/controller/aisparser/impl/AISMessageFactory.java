/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessage;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.VDMMessage;
import nl.esi.metis.aisparser.annotations.AISHypothesisAnnotation;
import nl.esi.metis.aisparser.annotations.AISInconsistentLengthForTypeAnnotation;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;


public class AISMessageFactory {

	/**
	 * Is valid VDMMessage, such that an AIS Message can be extracted from it?
	 * @param message
	 * @return is valid VDMMessage?
	 */
	static public boolean IsValid (VDMMessage message)
	{
		return message.atEnd() && message.isComplete();
	}


	/**
	 * Returns AISMessage that adheres to the specifications based on MessageType and Length
	 * @param sb
	 * @param provenance
	 * @return
	 */
	static private AISMessageImpl getAISMessageWithinSpecification (Sixbit sb, VDMMessageProvenance provenance)
	{
		int messageid 			= AISMessageImpl.getMessageID(sb);
		switch (messageid)
		{
		case 1:
			if (AISMessage01Impl.validLength(sb.length()))
			{
				return new AISMessage01Impl(sb, provenance);
			}
			break;
		case 2:
			if (AISMessage02Impl.validLength(sb.length()))
			{
				return new AISMessage02Impl(sb, provenance);
			}
			break;
		case 3:
			if (AISMessage03Impl.validLength(sb.length()))
			{
				return new AISMessage03Impl(sb, provenance);
			}
			break;
		case 4:
			if (AISMessage04Impl.validLength(sb.length()))
			{
				return new AISMessage04Impl(sb, provenance);
			}
			break;
		case 5:
			if (AISMessage05Impl.validLength(sb.length()))
			{
				return new AISMessage05Impl(sb, provenance);
			}
			break;
		case 6:
			if (AISMessage06Impl.validLength(sb.length()))
			{
				return new AISMessage06Impl(sb, provenance);
			}
			break;
		case 7:
			if (AISMessage07Impl.validLength(sb.length()))
			{
				return new AISMessage07Impl(sb, provenance);
			}
			break;
		case 8:
			if (AISMessage08Impl.validLength(sb.length()))
			{
				return new AISMessage08Impl(sb, provenance);
			}
			break;
		case 9:
			if (AISMessage09Impl.validLength(sb.length()))
			{
				return new AISMessage09Impl(sb, provenance);
			}
			break;
		case 10:
			if (AISMessage10Impl.validLength(sb.length()))
			{
				return new AISMessage10Impl(sb, provenance);
			}
			break;
		case 11:
			if (AISMessage11Impl.validLength(sb.length()))
			{
				return new AISMessage11Impl(sb, provenance);
			}
			break;
		case 12:
			if (AISMessage12Impl.validLength(sb.length()))
			{
				return new AISMessage12Impl(sb, provenance);
			}
			break;
		case 13:
			if (AISMessage13Impl.validLength(sb.length()))
			{
				return new AISMessage13Impl(sb, provenance);
			}
			break;
		case 14:
			if (AISMessage14Impl.validLength(sb.length()))
			{
				return new AISMessage14Impl(sb, provenance);
			}
			break;
		case 15:
			if (AISMessage15Impl.validLength(sb.length()))
			{
				return new AISMessage15Impl(sb, provenance);
			}
			break;
		case 16:
			if (AISMessage16Impl.validLength(sb.length()))
			{
				return new AISMessage16Impl(sb, provenance);
			}
			break;
		case 17:
			if (AISMessage17Impl.validLength(sb.length()))
			{
				return new AISMessage17Impl(sb, provenance);
			}
			break;
		case 18:
			if (AISMessage18Impl.validLength(sb.length()))
			{
				return new AISMessage18Impl(sb, provenance);
			}
			break;
		case 19:
			if (AISMessage19Impl.validLength(sb.length()))
			{
				return new AISMessage19Impl(sb, provenance);
			}
			break;
		case 20:
			if (AISMessage20Impl.validLength(sb.length()))
			{
				return new AISMessage20Impl(sb, provenance);
			}
			break;
		case 21:
			if (AISMessage21Impl.validLength(sb.length()))
			{
				return new AISMessage21Impl(sb, provenance);
			}
			break;
		case 22:
			if (AISMessage22Impl.validLength(sb.length()))
			{
				return new AISMessage22Impl(sb, provenance);
			}
			break;
		case 23:
			if (AISMessage23Impl.validLength(sb.length()))
			{
				return new AISMessage23Impl(sb, provenance);
			}
			break;
		case 24:
		{
			// Distinguish between the different part numbers:
			if (AISMessage24PartAImpl.valid(sb))
			{
				return new AISMessage24PartAImpl(sb, provenance);
			}
			else if (AISMessage24PartBImpl.valid(sb))
			{
				return new AISMessage24PartBImpl(sb, provenance);
			}
			break;
		}
		case 25:
			if (AISMessage25Impl.valid(sb))
			{
				return new AISMessage25Impl(sb, provenance);
			}
			break;
		case 26:
			if (AISMessage26Impl.valid(sb))
			{
				return new AISMessage26Impl(sb, provenance);
			}
			break;
		case 27:
			if (AISMessage27Impl.validLength(sb.length()))
			{
				return new AISMessage27Impl(sb, provenance);
			}
			break;	
		}
		return null;
	}

	public static AISMessageImpl AssumeMessageIdIsCorrect(VDMMessage message, Sixbit sb, VDMMessageProvenance provenance, AISInconsistentLengthForTypeAnnotation annotation)
	{

		/* We assume messageId as specified by message is OK
		 * We change length of message to adhere to the type's length
		 */
		AISMessageImpl retval;

		int assumedMessageId = AISMessageImpl.getMessageID(sb);

		switch (assumedMessageId)
		{
		case 1:
		{
			int diff = AISMessage01Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage01Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage01Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 2:
		{
			int diff = AISMessage02Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage02Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage02Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 3:
		{
			int diff = AISMessage03Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage03Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage03Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 4:
		{
			int diff = AISMessage04Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage04Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage04Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 5:
		{
			int diff = AISMessage05Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage05Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage05Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 6:
		{
			int diff = AISMessage06Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage06Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage06Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 7:
		{
			int diff = AISMessage07Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage07Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage07Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 8:
		{
			int diff = AISMessage08Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage08Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage08Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 9:
		{
			int diff = AISMessage09Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage09Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage09Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 10:
		{
			int diff = AISMessage10Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage10Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage10Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 11:
		{
			int diff = AISMessage11Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage11Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage11Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}	case 12:
		{
			int diff = AISMessage12Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage12Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage12Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 13:
		{
			int diff = AISMessage13Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage13Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage13Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 14:
		{
			int diff = AISMessage14Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage14Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage14Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 15:
		{
			int diff = AISMessage15Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage15Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage15Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 16:
		{
			int diff = AISMessage16Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage16Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage16Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 17:
		{
			int diff = AISMessage17Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage17Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage17Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 18:
		{
			int diff = AISMessage18Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage18Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage18Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 19:
		{
			int diff = AISMessage19Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage19Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage19Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 20:
		{
			int diff = AISMessage20Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage20Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage20Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 21:
		{
			int diff = AISMessage21Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage21Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage21Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}	case 22:
		{
			int diff = AISMessage22Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage22Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage22Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 23:
		{
			int diff = AISMessage23Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage23Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage23Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 24:
		{
			if (AISMessage24PartAImpl.isTypeA(sb))
			{
				int diff = AISMessage24PartAImpl.differenceInBits (sb);
				assert (diff != message.getNrOfFillBits());		// message violates standard
				if (diff >= 0)
				{
					sb.setNrOfFillBits(diff);
					assert(AISMessage24PartAImpl.valid(sb));	// message changed to adhere to standard
					retval = new AISMessage24PartAImpl(sb, provenance);
					retval.AddAnnotation(annotation);
					retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
				}
				else
				{
					//no hypothesis
					retval = new AISMessageImpl(sb, provenance);
					retval.AddAnnotation(annotation);
				}
			} 
			else if (AISMessage24PartBImpl.isTypeB(sb))
			{
				int diff = AISMessage24PartBImpl.differenceInBits (sb);
				assert (diff != message.getNrOfFillBits());		// message violates standard
				if (diff >= 0)
				{
					sb.setNrOfFillBits(diff);
					assert(AISMessage24PartBImpl.valid(sb));	// message changed to adhere to standard
					retval = new AISMessage24PartBImpl(sb, provenance);
					retval.AddAnnotation(annotation);
					retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
				}
				else
				{
					//no hypothesis
					retval = new AISMessageImpl(sb, provenance);
					retval.AddAnnotation(annotation);
				}

			} 
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 25:
		{
			int diff = AISMessage25Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage25Impl.valid(sb));	// message changed to adhere to standard
				retval = new AISMessage25Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 26:
		{
			int diff = AISMessage26Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage26Impl.valid(sb));	// message changed to adhere to standard
				retval = new AISMessage26Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		case 27:
		{
			int diff = AISMessage27Impl.differenceInBits (sb);
			assert (diff != message.getNrOfFillBits());		// message violates standard
			if (diff >= 0)
			{
				sb.setNrOfFillBits(diff);
				assert(AISMessage27Impl.validLength(sb.length()));	// message changed to adhere to standard
				retval = new AISMessage27Impl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, sb.length()));
			}
			else
			{
				//no hypothesis
				retval = new AISMessageImpl(sb, provenance);
				retval.AddAnnotation(annotation);
			}
			break;
		}
		default:{
			return new AISMessageUnknownMessageIdImpl(sb, provenance);
		}
		}
		assert (retval != null);
		return retval;
	}

	private static AISMessageImpl GuessMessageIdBasedOnLength(Sixbit sb, VDMMessageProvenance provenance, AISInconsistentLengthForTypeAnnotation annotation)
	{
		AISMessageImpl retval = null;

		switch (sb.length())
		{
		case 168:	
		{
			/* AisMessagePositionReport is most occurring message. 
			 * We assume that a message outside the specification with this length is a PositionReport
			 * We have chosen type 01 as implementation there of 
			 */
			int assumedMessageId = 1;
			AISMessageImpl.setMessageID(sb, assumedMessageId);
			assert(AISMessageImpl.getMessageID(sb) == assumedMessageId);
			retval = new AISMessage01Impl(sb, provenance);
			retval.AddAnnotation(annotation);
			retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 168));
			break;
		}
		case 312:
		{
			/* Message of type 19 have the relatively unique length of 312
			 * We assume that a message outside the specification with this length is of Type 19
			 */
			int assumedMessageId = 19;
			AISMessageImpl.setMessageID(sb, assumedMessageId);
			assert(AISMessageImpl.getMessageID(sb) == assumedMessageId);
			retval = new AISMessage19Impl(sb, provenance);
			retval.AddAnnotation(annotation);
			retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 312));
			break;
		}
		case 424:
		{
			/* Message of type 5 have the relatively unique length of 424
			 * We assume that a message outside the specification with this length is of Type 05
			 */
			int assumedMessageId = 5;
			AISMessageImpl.setMessageID(sb, assumedMessageId);
			assert(AISMessageImpl.getMessageID(sb) == assumedMessageId);
			retval = new AISMessage05Impl(sb, provenance);
			retval.AddAnnotation(annotation);
			retval.AddAnnotation(new AISHypothesisAnnotation(assumedMessageId, 424));
			break;
		}
		}
		return retval;
	}
	


	private static AISMessageImpl GuessPartIdBasedOnLength(Sixbit sb, VDMMessageProvenance provenance, 	AISInconsistentLengthForTypeAnnotation annotation) {
		AISMessageImpl retval = null;

		int messageid = AISMessageImpl.getMessageID(sb);
		if (messageid == 24)
		{
			if (sb.length() == AISMessage24PartAImpl.LENGTHPartA)
			{
				AISMessage24Impl.setPart(sb, 0);
				assert(AISMessage24PartAImpl.valid(sb));	// message changed to adhere to standard
				retval = new AISMessage24PartAImpl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(messageid, sb.length()));	//TODO: include partId in hypothesis
			}
			else if (sb.length() == AISMessage24PartBImpl.LENGTHPartB)
			{
				AISMessage24Impl.setPart(sb, 1);
				assert(AISMessage24PartBImpl.valid(sb));	// message changed to adhere to standard
				retval = new AISMessage24PartBImpl(sb, provenance);
				retval.AddAnnotation(annotation);
				retval.AddAnnotation(new AISHypothesisAnnotation(messageid, sb.length())); //TODO: include partId in hypothesis
			}
		}
		return retval;
	}

	static public AISMessage getAISMessage(VDMMessage message) {
		Sixbit sb = new Sixbit(message.getMessage(), message.getNrOfFillBits());
		VDMMessageProvenance provenance = message.getProvenance();

		if (sb.length() < 38)	
		{
			//Message too short according to standard for any message type
			return new AISMessageIllegalImpl(sb, provenance); // the errors are logged here
		}
		else
		{
			AISMessageImpl retval = null;
			retval = getAISMessageWithinSpecification(sb, provenance);
			if (retval == null)
			{
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
				if (retval == null)
				{
					retval = GuessMessageIdBasedOnLength(sb, provenance, annotation);
					if (retval == null)
					{
						retval = AssumeMessageIdIsCorrect(message, sb, provenance, annotation);
					}
				}
				assert (retval != null);
			}
			return retval;
		}
	}
}