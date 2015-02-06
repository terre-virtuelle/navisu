/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 20: Data Link Management Message.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage20 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** units
	 * @return Array of AISMessage20Unit containing all units in the AIS Message
	 */
	public AISMessage20Unit[] getUnits();

	/** spare2
	 * @return int value of spare2 (0-6 bits)
	 */
	public int getSpare2();

}