package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents an AIS message of type 5: Ship static and voyage related data.
 * Such messages are used by Class A shipborne and SAR aircraft AIS stations to report static and voyage-related data.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage05 extends AISMessage {

	/** Returns an indicator for the AIS version applicable to the current message.
	 * @return an integer value indicating the AIS version: <br>
	 * 0 = station compliant with Recommendation ITU-R M.1371-1 <br>
	 * 1 = station compliant with Recommendation ITU-R M.1371-3 <br>
	 * 2-3 = station compliant with future editions
	 */
	public int getAisVersionIndicator();

	/** Returns the IMO number of the transmitting ship.
	 * See <a href=http://en.wikipedia.org/wiki/IMO_ship_identification_number>Wikipedia</a> for more information on IMO (International Maritime Organization) numbers.
	 * @return an integer value representing the IMO number (1-999999999) <br>
	 * 0 = not available = default <br>
	 * Not applicable to SAR aircraft
	 */
	public int getImoNumber();

	/** Returns the call sign of the transmitting ship.
	 * @return a String value, containing at most 7 characters, representing the call sign <br>
	 * "" = not available
	 */
	public String getCallSign();

	/** Returns the name of the transmitting ship.
	 * @return a String value, containing maximum 20 characters, representing the name. <br>
	 * "" = not available. <br>
	 * For SAR aircraft, it should be set to "SAR AIRCRAFT NNNNNNN" where NNNNNNN equals the aircraft registration number
	 */
	public String getName();

	/** Returns the type of ship and cargo type. This can be converted to a String by utility class {@link UtilsShipType8}.
	 * @return an integer value representing the type of ship and cargo type: <br>
	 * 0 = not available or no ship <br>
	 * 1-99 = as defined in the standard (see {@link UtilsShipType8}) <br>
	 * 100-199 = reserved for regional use <br>
	 * 200-255 = reserved for future use  <br>
	 * Not applicable to SAR aircraft
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

	/** Returns the estimated time of arrival (ETA). 
	 * This can be analyzed further using utility class {@link UtilsEta}.
	 * @return a <code>BitVector</code> value of the estimated time of arrival (MMDDHHMM UTC) <br>
	 * Bits 19-16: month; 1-12; 0 = not available <br>
	 * Bits 15-11: day; 1-31; 0 = not available <br>
	 * Bits 10-6: hour; 0-23; 24 = not available <br>
	 * Bits 5-0: minute; 0-59; 60 = not available <br>
	 * For SAR aircraft, the use of this field may be decided by the responsible administration.
	 */
	public BitVector getEta();

	/** Returns the maximum present static draught.
	 * @return an integer value expressing the maximum present static draught in 1/10 m <br>
	 * 255 = draught 25.5 m or greater <br>
	 * 0 = not available  <br>
	 * in accordance with IMO Resolution A.851 <br>
	 * Not applicable to SAR aircraft, should be set to 0
	 */
	public int getMaximumPresentStaticDraught();

	/** Returns the destination.
	 * @return a String value, containing maximum 20 characters, representing the destination <br>
	 * "" = not available <br>
	 * For SAR aircraft, the use of this field may be decided by the responsible administration.
	 */
	public String getDestination();

	/** Returns the data terminal equipment (DTE) status.
	 * @return a boolean value describing whether the data terminal equipment is ready: <br>
	 * false = available <br>
	 * true = not available
	 */
	public boolean getDte();

	/** Get the spare bit.
	 * @return the integer value of the spare bit. This should be zero.
	 */
	public int getSpare();

}