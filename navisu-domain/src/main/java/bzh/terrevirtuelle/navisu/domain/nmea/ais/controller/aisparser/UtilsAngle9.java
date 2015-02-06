package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (9-bit signed integer) angular value that is returned by 
 * {@link AISMessagePositionReport#getTrueHeading()}, {@link AISMessage18#getTrueHeading()}, {@link AISMessage19#getTrueHeading()},
 * and {@link AISMessage27#getCourseOverGround()}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsAngle9 {
	/** The angular value that is used to signal unavailability, according to the AIS standard. */
	private static final int DEFAULTVALUE = 511;

	/** The minimum valid angular value. */
	private static final int MINVALUE = 0 ;

	/** The maximum valid angular value. */
	private static final int MAXVALUE =  359;
	
	/** Checks if the angular value is semantically correct (according to the standard). 
	 * This means that it is either a valid measurement or the standard value indicating unavailability.
	 * @param value the angular value that is returned by for example {@link AISMessagePositionReport#getTrueHeading()} and {@link AISMessage27#getCourseOverGround()}.
	 * @return true if the value is semantically correct
	 */
	public static boolean isAngleSemanticallyCorrect( int value )
	{
		return ( ( UtilsAngle9.MINVALUE <= value ) && ( value <= UtilsAngle9.MAXVALUE ) ) || (value == UtilsAngle9.DEFAULTVALUE);
	}
	
	/** Checks if the angular value is available.
	 * @precondition isTrueHeadingSemanticallyCorrect (value)
	 * @param value the angular value that is returned by {@link AISMessagePositionReport#getTrueHeading()}.
	 * @return true if the angular is available
	 */
	public static boolean isAvailable( int value )
	{
		return !(value == UtilsAngle9.DEFAULTVALUE);
	}
	
	/** semantically correct range of angular value
	 * 
	 */
	public static final String range = "["+MINVALUE+","+MAXVALUE+"] + {"+ DEFAULTVALUE +"}";
	
	/** Generates a text string representing the angular.
	 * @param angle an integer value representing the angular, as returned by {@link AISMessagePositionReport#getTrueHeading()}
	 * @return a string representing the angular
	 */
	public static String toString(int angle) {
		String angleString;
		if (isAngleSemanticallyCorrect(angle))
		{
			if (isAvailable(angle))
				angleString = Integer.toString(angle);
			else 
				angleString = "not available";
		}
		else
			angleString = "illegal value";
		return angleString;
	}
}