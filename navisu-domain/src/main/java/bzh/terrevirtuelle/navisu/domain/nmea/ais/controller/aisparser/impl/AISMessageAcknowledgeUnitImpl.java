/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessageAcknowledgeUnit;
import nl.esi.metis.aisparser.Sixbit;

/** Implementation of units of acknowledgements.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	destinationID                          	  30	(  0,   29)
 *  2	Sequence Number                            2	(  30,  31)
 *  </pre>
 *  @author Pierre America
 *  @author Pierre van de Laar
 */
class AISMessageAcknowledgeUnitImpl implements AISMessageAcknowledgeUnit {
	private static final int DESTINATIONID_FROM = 0;
	private static final int DESTINATIONID_TO = 29;

	private int destinationID;
	/** destinationID
	 * @return int value of destinationID1 (30 bits offset+[0,29])
	 */
	public int getDestinationID() { return destinationID; }

	private static final int SEQUENCENUMBERFORID_FROM = 30;
	private static final int SEQUENCENUMBERFORID_TO = 31;

	private int sequenceNumberForID;
	/** sequenceNumberForID
	 * @return int value of sequenceNumberForID (2 bits offset + [30,31])
	 */
	public int getSequenceNumberForID() { return sequenceNumberForID; }


	public AISMessageAcknowledgeUnitImpl (int offset, Sixbit content)
	{
		assert(content.length() >= offset + SEQUENCENUMBERFORID_TO);
		
		destinationID = content.getIntFromTo(offset+DESTINATIONID_FROM,offset+DESTINATIONID_TO);
		sequenceNumberForID = content.getIntFromTo(offset+SEQUENCENUMBERFORID_FROM, offset+SEQUENCENUMBERFORID_TO);
	}
}