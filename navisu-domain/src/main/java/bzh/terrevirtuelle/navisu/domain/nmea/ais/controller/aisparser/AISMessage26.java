/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents an AIS message of type 26: Multiple Slot Binary Message with Communications State.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage26 extends AISMessage {

	/** destinationIndicator
	 * @return boolean value of destinationIndicator (bit 39)
	 */
	public boolean getDestinationIndicator();

	/** destinationID
	 * @return int value of destinationID (30 bits [41,70])
	 * @precondition getDestinationIndicator() == true
	 */
	public int getDestinationID();

	/** binaryDataFlag
	 * @return boolean value of binaryDataFlag (bit 40)
	 */
	public boolean getBinaryDataFlag();

	/** applicationID
	 * @return int value of applicationID (16 bits)
	 * @precondition getBinaryDataFlag() == true
	 */
	public int getApplicationID();

	/** applicationBinaryData
	 * @return BitVector value of applicationBinaryData (maximally 1004 bits [41,1044])
	 */
	public BitVector getApplicationBinaryData();

	/** communicationStateSelectorFlag
	 * @return boolean value of communicationStateSelectorFlag (bit 1045)
	 */
	public boolean getCommunicationStateSelectorFlag();

	/** communicationState
	 * @return CommunicationState value of communicationState (19 bits [1046,1064])
	 */
	public CommunicationState getCommunicationState();

}