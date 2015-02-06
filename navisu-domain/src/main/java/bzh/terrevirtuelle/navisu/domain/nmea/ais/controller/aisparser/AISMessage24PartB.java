/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/** This interface represents AIS messages of type 24 Part B: Static data report.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage24PartB extends AISMessage24{

	/** Returns the type of ship and cargo type. This can be converted to a String by utility class {@link UtilsShipType8}.
	 * @return an integer value representing the type of ship and cargo type: <br>
	 * 0 = not available or no ship <br>
	 * 1-99 = as defined in the standard (see {@link UtilsShipType8}) <br>
	 * 100-199 = reserved for regional use <br>
	 * 200-255 = reserved for future use  <br>
	 * Not applicable to SAR aircraft
	 */
	public int getTypeOfShipAndCargoType();

	/** Returns the vendor ID. Currently there is not any utility class available to analyze this further.
	 * @return a <code>BitVector</code> representing the vendor ID (42 bits). <br>
	 * This is a unique identification of the transmitting unit by a number as defined by the manufacturer. <br>
	 * "@@@@@@@" = not available = default <br>
	 * See Table 76A in the standard Rec. ITU-R M.1371-4
	 */
	public BitVector getVendorID();

	/** Returns the call sign of the transmitting ship.
	 * @return a String value, containing up to 7 characters, representing the call sign <br>
	 * "" = not available
	 */
	public String getCallSign();

	/** Returns the dimensions of the ship and the reference point for position reporting,
	 * or for unregistered daughter vessels, the MMSI of the mother ship.
	 * Unfortunately, there is no reliable method to distinguish among these two possibilities.
	 * In case of dimensions, the value can be further analyzed using {@link UtilsDimensions30}.
	 * @return a <code>BitVector</code> object representing the dimensions or the mother ID (30 bits)
	 */
	public BitVector getDimensionOrMotherID();

	/** Returns the spare bits.
	 * @return integer value of the spare bits. This should be zero.
	 */
	public int getSpare();

}