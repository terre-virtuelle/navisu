package nl.esi.metis.aisparser;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 */

/** This class provides a method to analyze the navigational status as returned by 
 * {@link AISMessagePositionReport#getNavigationalStatus()} or {@link AISMessage27#getNavigationalStatus()}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public class UtilsNavStatus {

	/** This array keeps the string values corresponding to the navigational status integer values. */
	final static private String[] navStatString = {
		"under way using engine",				// 0
		"at anchor",							// 1
		"not under command",					// 2
		"restricted manoeuvrability",			// 3
		"constrained by her draught",			// 4
		"moored",								// 5
		"aground",								// 6
		"engaged in fishing",					// 7
		"under way sailing",					// 8
		"reserved for future amendment",		// 9
		"reserved for future amendment",		//10
		"reserved for future use",				//11
		"reserved for future use",				//12
		"reserved for future use",				//13
		"AIS-SART (active)",					//14
		"not defined"							//15
};
	
	/** Returns a text string describing the navigational status. 
	 * @param navigationalStatus an integer value describing the navigational status as returned by 
     * {@link AISMessagePositionReport#getNavigationalStatus()} or {@link AISMessage27#getNavigationalStatus()}.
	 * @return a text string describing the navigational status
	 */
	public static String toString(int navigationalStatus) {
		assert (navigationalStatus >= 0 && navigationalStatus <=15);
		return navStatString [navigationalStatus];
	}
	
	/** Not all values are allowed by the standard (yet).
	 * Determines whether the provided value is currently allowed.
	 * @param navigationalStatus
	 * @return Is the provided navigationalStatus semantically correct?
	 */
	public static boolean isNavStatusSemanticallyCorrect( int navigationalStatus )
	{
		return (0 <= navigationalStatus ) && ( navigationalStatus <= 15) &&
			   !( ( 9 <= navigationalStatus ) && ( navigationalStatus <= 13 ) );	//Reserved values should not be used, and are thus considered invalid
	}
	
	/** semantically correct range of navigationalStatus
	 * 
	 */
	public static final String range = "[0,8] + [14,15]";
}
