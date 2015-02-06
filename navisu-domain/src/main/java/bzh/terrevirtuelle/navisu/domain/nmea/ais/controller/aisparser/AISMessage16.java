/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 16: Assigned mode command.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage16 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** units
	 * @return Array of AISMessage16Unit containing all units in the AIS Message
	 */
	public AISMessage16Unit[] getUnits();

	/** spare2
	 * @return int value of spare2 (0 or 4 bits {4 bits at [93,96]})
	 */
	public int getSpare2();

}