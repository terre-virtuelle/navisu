/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 27: Long-range AIS broadcast message.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage27 extends AISMessage, PositionInfo {

	/** raimFlag
	 * @return boolean value of raimFlag (bit 40)
	 */
	public boolean getRaimFlag();

	/** Returns the navigational status.
	 * This can be further analyzed using utility class {@link UtilsNavStatus}.
	 * @return an integer in the range of 0 to 15: <br>
	 * 0 = under way using engine <br>
	 * 1 = at anchor <br>
	 * 2 = not under command <br>
	 * 3 = restricted maneuverability <br>
	 * 4 = constrained by her draught <br>
	 * 5 = moored <br>
	 * 6 = aground <br>
	 * 7 = engaged in fishing <br>
	 * 8 = under way sailing <br>
	 * 9 = reserved for future amendment of navigational status for ships carrying DG, HS, or MP, or IMO hazard or pollutant category C, high speed craft (HSC) <br>
	 * 10 = reserved for future amendment of navigational status for ships carrying dangerous goods (DG), harmful substances (HS) or marine pollutants (MP), 
	 *      or IMO hazard or pollutant category A, wing in grand (WIG) <br>
	 * 11-13 = reserved for future use <br>
	 * 14 = AIS-SART (active) <br>
	 * 15 = not defined = default (also used by AIS-SART under test)
	 */
	public int getNavigationalStatus();

	/** speedOverGround (6 bits)
	 * @return int value of speedOverGround (6 bits [80,85])
	 */
	public int getSpeedOverGround();

	/** courseOverGround (9 bits)
	 * This value can be analyzed further with utility class {@link UtilsAngle9}.
	 * @return int value of courseOverGround (9 bits [86,94])
	 */
	public int getCourseOverGround();

	/** statusOfCurrentGNSSPosition
	 * @return boolean value of statusOfCurrentGNSSPosition (bit 95)
	 */
	public boolean getStatusOfCurrentGNSSPosition();

	/** spare
	 * @return int value of spare (1 bits [96,96])
	 */
	public int getSpare();

}