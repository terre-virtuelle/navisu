package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessage07;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.provenance.VDMMessageProvenance;
/** ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */


/** AIS Message 7
 * Binary Acknowledge
 * 
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	userID                                  	  30	(   9,  38)
 *  4	spare                                   	   2	(  39,  40)
 *  5	destinationID1                          	  30	(  41,  70)
 *  6	sequenceNumberForID1                    	   2	(  71,  72)
 *  7	destinationID2                          	  30	(  73, 102)
 *  8	sequenceNumberForID2                    	   2	( 103, 104)
 *  9	destinationID3                          	  30	( 105, 134)
 * 10	sequenceNumberForID3                    	   2	( 135, 136)
 * 11	destinationID4                          	  30	( 137, 166)
 * 12	sequenceNumberForID4                    	   2	( 167, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 */
class AISMessage07Impl extends AISMessageAcknowledgeImpl implements AISMessage07 {
	/** AISMessage07 constructor
	 * @param content AIS content
	 * @param prov the provenance of the message
	 * @precondition validLength(content.length()) && AISMessageBase.getMessageId(content)== 7
	 */
	public AISMessage07Impl(Sixbit content, VDMMessageProvenance prov) {
		super(content, prov);
		assert(getMessageID() == 7);
	}
}
