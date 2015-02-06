/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;

/** AIS Message 24: Static Data Report
 * <pre>
 * Field Nr     Field Name                              Nr of Bits (from, to )
 * ------------------------------------------------------------------------
 *  1           messageID                                        6 (   1,   6)
 *  2           repeatIndicator                                  2 (   7,   8)
 *  3           userID                                          30 (   9,  38)
 *  4           partNumber                                       2 (  39,  40)
 *	5 ..		part specific fields                           ...  
 *                                                            ---- +
 *                                           number of bits     40
 * </pre>
 * @author Pierre van de Laar
 * @author Pierre America
 */
class AISMessage24Impl extends AISMessageImpl {

	/** Checks whether the content could be a valid message of type 24. 
	 * @param content possible message type 24
	 * @return true if this could be a valid message of type 24
	 */
	public static boolean valid(Sixbit content)
	{
		return ( content.length() >= PARTNUMBER_TO );
	}
	
	protected static final int PARTNUMBER_FROM = 39;
	protected static final int PARTNUMBER_TO = 40;

	public AISMessage24Impl(Sixbit content, VDMMessageProvenance prov)
	{
		super(content, prov);
		assert(getMessageID() == 24);
	}

	/**
	 * Change Part Id to value
	 * @param sb	vector to change
	 * @param value new value with PartA == 0 and PartB == 1
	 * @precondition valid(sb)
	 */
	public static void setPart(Sixbit sb, int value) {
		sb.setIntFromTo(value, PARTNUMBER_FROM,PARTNUMBER_TO);
	}
}
