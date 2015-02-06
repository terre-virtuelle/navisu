/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 17: GNSS Broadcast binary message.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage17 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** Returns the longitude in degrees.
	 * @return double value of longitude in degrees (18 bits [41,58])
	 */
	public double getLongitudeInDegrees();

	/** Returns the latitude in degrees. 
	 * @return double value representing the latitude in degrees (&plusmn;90&deg;, North = positive, South = negative). <br>
	 *  91&deg; = not available
	 */
	public double getLatitudeInDegrees();

	/** spare2
	 * @return int value of spare2 (5 bits [76,80])
	 */
	public int getSpare2();

	/** messageType
	 * @return int value of messageType (6 bits [81,86])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getMessageType();

	/** stationID
	 * @return int value of stationID (10 bits [87,96])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getStationID();

	/** zCount
	 * @return int value of zCount (13 bits [97,109])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getZCount();

	/** sequenceNumber
	 * @return int value of sequenceNumber (3 bits [110,112])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getSequenceNumber();

	/** n
	 * @return int value of n (5 bits [113,117])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getN();

	/** health
	 * @return int value of health (3 bits [118,120])
	 * @precondition getDgnssDataWords().length > 0
	 */
	public int getHealth();

	/** dgnssDataWords
	 * @return int[] value of dgnssDataWords (696 bits [121,816])
	 */
	public int[] getDgnssDataWords();

}