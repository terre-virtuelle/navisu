/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents an AIS message of type 6: Addressed Binary Message.
 *
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public interface AISMessage06 extends AISMessage{

	/** sequenceNumber
	 * @return int value of sequenceNumber (2 bits [39,40])
	 */
	public int getSequenceNumber();

	/** destinationID
	 * @return int value of destinationID (30 bits [41,70])
	 */
	public int getDestinationID();

	/** retransmitFlag
	 * @return boolean value of retransmitFlag (bit 71)
	 */
	public boolean getRetransmitFlag();

	/** spare
	 * @return int value of spare (1 bits [72,72])
	 */
	public int getSpare();

	/** applicationID
	 * @return int value of applicationID (16 bits [73,88])
	 */
	public int getApplicationID();

	/** applicationBinaryData
	 * @return BitVector value of applicationBinaryData (maximally 920 bits [89,1008])
	 */
	public BitVector getApplicationBinaryData();

}