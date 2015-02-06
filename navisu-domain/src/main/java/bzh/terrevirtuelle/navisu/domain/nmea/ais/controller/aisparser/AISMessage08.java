/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents an AIS message of type 8: Binary Broadcast Message.
  *
  * @author Pierre van de Laar
  * @author Pierre America
  * @author Brian C. Lane
  */

public interface AISMessage08 extends AISMessage {

	/** spare
	 * @return int value of spare (2 bits [39,40])
	 */
	public int getSpare();

	/** applicationID
	 * @return int value of applicationID (16 bits [41,56])
	 */
	public int getApplicationID();

	/** applicationBinaryData
	 * @return BitVector value of applicationBinaryData (maximally 952 bits [57,1008])
	 */
	public BitVector getApplicationBinaryData();

}