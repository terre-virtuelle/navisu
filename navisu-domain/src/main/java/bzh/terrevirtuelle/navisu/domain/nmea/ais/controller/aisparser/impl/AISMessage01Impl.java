package nl.esi.metis.aisparser.impl;

import nl.esi.metis.aisparser.AISMessage01;
import nl.esi.metis.aisparser.Sixbit;
import nl.esi.metis.aisparser.Sotdma;
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


/**  AIS Message 1 implementation: Position report.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	userID                                  	  30	(   9,  38)
 *  4	navigationalStatus                      	   4	(  39,  42)
 *  5	rateOfTurn                              	   8	(  43,  50)
 *  6	speedOverGround                         	  10	(  51,  60)
 *  7	positionAccuracy                        	   1	(  61,  61)
 *  8	longitude                               	  28	(  62,  89)
 *  9	latitude                                	  27	(  90, 116)
 * 10	courseOverGround                        	  12	( 117, 128)
 * 11	trueHeading                             	   9	( 129, 137)
 * 12	timeStamp                               	   6	( 138, 143)
 * 13	specialManoeuvre                        	   2	( 144, 145)
 * 14	spare                                   	   3	( 146, 148)
 * 15	raimFlag                                	   1	( 149, 149)
 * 16	communicationState                      	  19	( 150, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 * </pre>
 */
class AISMessage01Impl extends AISMessagePositionReportImpl implements AISMessage01 {
	private Sotdma communicationState;
	/** communicationState
	 * @return Sotdma value of communicationState (19 bits [150,168])
	 */
	public Sotdma getCommunicationState() { return communicationState; }
    
	/** AISMessage 1 constructor
	 * @param content AIS content
	 * @param prov the provenance of the message
	 * @precondition validLength(content.length()) && AISMessageBase.getMessageId(content)== 1
	 */
	public AISMessage01Impl(Sixbit content, VDMMessageProvenance prov)
	{
		super(content, prov);
		assert(getMessageID() == 1);
    	communicationState = new Sotdma(COMMUNICATIONSTATE_FROM, content);
	}
}
