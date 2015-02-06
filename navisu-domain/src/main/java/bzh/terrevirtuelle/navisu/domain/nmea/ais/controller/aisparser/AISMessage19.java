/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents an AIS message of type 19: Extended Class B Equipment Position Report.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage19 extends AISMessageClassBPositionReport {

	/** Returns the name of the transmitting ship.
	 * @return a String value, containing maximum 20 characters, representing the name. <br>
	 * "" = not available.
	 */
	public String getName();

	/** Returns the type of ship and cargo type. 
	 * This can be converted to a String by utility class {@link UtilsShipType8}.
	 * @return an integer value representing the type of ship and cargo type: <br>
	 * 0 = not available or no ship <br>
	 * 1-99 = as defined in the standard (see {@link UtilsShipType8}) <br>
	 * 100-199 = reserved for regional use <br>
	 * 200-255 = reserved for future use  <br>
	 */
	public int getTypeOfShipAndCargoType();

	/** Returns the dimensions of the ship and the reference point for the reported position.
	 * These can be analyzed further with utility class {@link UtilsDimensions30}.
	 * @return a <code>BitVector</code> value representing the dimensions.
	 */
	public BitVector getDimension();

	/** Returns the type of electronic position fixing device.
	 * @return an integer value representing of the type of electronic position fixing device: <br>
	 * 0 = undefined (default) <br>
	 * 1 = global positioning system (GPS) <br>
	 * 2 = GNSS (GLONASS) <br>
	 * 3 = combined GPS/GLONASS <br>
	 * 4 = Loran-C <br>
	 * 5 = Chayka <br>
	 * 6 = integrated navigation system <br>
	 * 7 = surveyed <br>
	 * 8 = Galileo <br>
	 * 9-14 = not used <br>
	 * 15 = internal GNSS
	 */
	public int getTypeOfElectronicPositionFixingDevice();

	/** Returns the data terminal equipment (DTE) status.
	 * @return a boolean value describing whether the data terminal equipment is ready: <br>
	 * false = available <br>
	 * true = not available
	 */
	public boolean getDte();

	/** Returns the third set of spare bits.
	 * @return the value of the third set of spare bits. This should be zero.
	 */
	public int getSpare3();

}