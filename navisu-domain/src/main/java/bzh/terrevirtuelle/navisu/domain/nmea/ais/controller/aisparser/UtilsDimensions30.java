package nl.esi.metis.aisparser;

import cern.colt.bitvector.BitVector;

/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 */

/** This class provides functions to get the individual components of the dimensions <code>BitVector</code> that is returned by 
 * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
 * Together these components specify the size of the ship and the place of the reference point of which the position is reported in AIS messages.
 * 
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */

/* <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	bow                                     	   9	(  21,  29)
 *  2	stern                                   	   9	(  12,  20)
 *  3	port                                    	   6	(   6,  11)
 *  4	starboard                               	   6	(   0,   5)
 *                                                    ---- +
 *                        (maximum) number of bits    30
 * </pre>
 */
public class UtilsDimensions30 {
	
	/** The position of the first bit of the bow component. */
	private static final int BOW_FROM = 21;

	/** The position of the last bit of the bow component. */
	private static final int BOW_TO = 29;

	/** Returns the distance from the reference point to the bow of the ship.
	 * @param dimension a {@code BitVector} containing the dimensions of the ship, as returned by
     * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
	 * @return > 0: the distance from the reference point to the bow in meters <br>
	 *         0: reference point not available, in which case the stern component could indicate the length of the ship or be zero as well.
	 */
	public static int getBow(BitVector dimension) { 
		return (int)dimension.getLongFromTo(BOW_FROM,BOW_TO); 
	}

	/** The position of the first bit of the stern component. */
	private static final int STERN_FROM = 12;

	/** The position of the last bit of the stern component. */
	private static final int STERN_TO = 20;

	/** Returns the distance from the reference point to the stern of the ship.
	 * @param dimension a {@code BitVector} containing the dimensions of the ship, as returned by
     * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
	 * @return > 0: the distance from the reference point to the stern in meters or, if the bow component is zero, the length of the ship <br>
	 *         0: ship size and reference point not available, in which case the bow component should be zero as well.
	 */
	public static int getStern(BitVector dimension) { 
		return (int) dimension.getLongFromTo(STERN_FROM,STERN_TO);
	}

	/** The position of the first bit of the port component. */
	private static final int PORT_FROM = 6;

	/** The position of the last bit of the port component. */
	private static final int PORT_TO = 11;

	/** Returns the distance from the reference point to the port side of the ship.
	 * @param dimension a {@code BitVector} containing the dimensions of the ship, as returned by
     * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
	 * @return > 0: the distance from the reference point to the port side in meters <br>
	 *         0: reference point not available, in which case the starboard component could indicate the width of the ship or be zero as well.
	 */
	public static int getPort(BitVector dimension) { 
		return (int)dimension.getLongFromTo(PORT_FROM,PORT_TO);
	}
	
	/** The position of the first bit of the starboard component. */
	private static final int STARBOARD_FROM = 0;

	/** The position of the first bit of the starboard component. */
	private static final int STARBOARD_TO = 5;

	/** Returns the distance from the reference point to the starboard side of the ship.
	 * @param dimension a {@code BitVector} containing the dimensions of the ship, as returned by
     * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
	 * @return  > 0: the distance from the reference point to the starboard in meters or, if the port component is zero, the width of the ship <br>
	 *          0: ship size and reference point not available, in which case the port component should be zero as well.
	 */
	public static int getStarboard(BitVector dimension) {
		return (int) dimension.getLongFromTo(STARBOARD_FROM,STARBOARD_TO);
	}
	
	/** Returns a string representing the components of the given dimensions.
	 * The components are separated by commas, in the order: bow, stern, port, starboard.
	 * @param dimension a {@code BitVector} containing the dimensions of the ship, as returned by
     * {@link AISMessage05#getDimension} or {@link AISMessage19#getDimension()}.
	 * @return a string representing the dimensions
	 */
	public static String toString(BitVector dimension) {
		int bow = getBow(dimension);
		int stern = getStern(dimension);
		int port = getPort(dimension);
		int starboard = getStarboard(dimension);
		String result = bow + "," + stern + "," + port + "," + starboard;
		return result;
	}

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsDimensions30 () {
	}
}