package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessage15Message;
import nl.esi.metis.aisparser.Sixbit;
/** ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */

/**
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                            	  6	(  0,  5)
 *  2	slot offset                       	     12	(  6,  17)
 *                                               ---- +
 *                     (maximum) number of bits    18
 */
class AISMessage15MessageImpl implements AISMessage15Message {
	private static final int MESSAGEID_FROM = 0;
	private static final int MESSAGEID_TO = 5;

	private int messageID;
	/** messageID
	 * @return int value of messageID (6 bits offset+[0,5])
	 */
	public int getMessageID() { return messageID; }


	private static final int SLOTOFFSET_FROM = 6;
	private static final int SLOTOFFSET_TO = 17;

	private int slotOffset;
	/** slotOffset
	 * @return int value of slotOffset (12 bitsoffset+[6,17])
	 */
	public int getSlotOffset() { return slotOffset; }

	public AISMessage15MessageImpl (int offset, Sixbit content)
	{
		assert(content.length() >= offset + SLOTOFFSET_TO);
		messageID = content.getIntFromTo(offset+MESSAGEID_FROM,offset+MESSAGEID_TO);
		slotOffset = content.getIntFromTo(offset+SLOTOFFSET_FROM,offset+SLOTOFFSET_TO);

	}
}