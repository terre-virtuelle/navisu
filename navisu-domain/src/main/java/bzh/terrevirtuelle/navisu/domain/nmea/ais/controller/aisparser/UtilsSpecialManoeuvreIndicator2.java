package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (2-bit signed integer) Special Manoeuvre Indicator value that is returned by 
 * {@link AISMessagePositionReport#getSpecialManoeuvre()}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsSpecialManoeuvreIndicator2 {
	/** The special-manoeuvre-indicator value that is used to signal unavailability, according to the AIS standard. */
	private static final int DEFAULTVALUE = 0;

	/** The minimum valid special-manoeuvre-indicator value. */
	private static final int MINVALUE = 1 ;

	/** The maximum valid special-manoeuvre-indicator value. */
	private static final int MAXVALUE =  2;
	
	/** Checks if the special-manoeuvre-indicator value is semantically correct (according to the standard). 
	 * This means that it is either a valid measurement or the standard value indicating unavailability.
	 * @param value the special-manoeuvre-indicator value that is returned by {@link AISMessagePositionReport#getSpecialManoeuvre()}.
	 * @return true if the value is semantically correct
	 */
	public static boolean isSpecialManoeuvreIndicatorSemanticallyCorrect( int value )
	{
		return ( ( UtilsSpecialManoeuvreIndicator2.MINVALUE <= value ) && ( value <= UtilsSpecialManoeuvreIndicator2.MAXVALUE ) ) || (value == UtilsSpecialManoeuvreIndicator2.DEFAULTVALUE);
	}
	
	/** Checks if the special-manoeuvre-indicator value is available.
	 * @precondition isSpecialManoeuvreIndicatorSemanticallyCorrect (value)
	 * @param value the special-manoeuvre-indicator value that is returned by {@link AISMessagePositionReport#getSpecialManoeuvre()}.
	 * @return true if the special-manoeuvre-indicator is available
	 */
	public static boolean isAvailable( int value )
	{
		return !(value == UtilsSpecialManoeuvreIndicator2.DEFAULTVALUE);
	}
	
	/** semantically correct range of special-manoeuvre-indicator
	 * 
	 */
	public static final String range = "["+MINVALUE+","+MAXVALUE+"] + {"+ DEFAULTVALUE +"}";
	
	/** Generates a text string representing the special-manoeuvre-indicator.
	 * @param specialManoeuvreIndicator an integer value representing the special-manoeuvre-indicator, as returned by {@link AISMessagePositionReport#getSpecialManoeuvre()}
	 * @return a string representing the special-manoeuvre-indicator
	 */

	public static String toString(int specialManoeuvreIndicator) {
		switch (specialManoeuvreIndicator) {
			case 0:
				return "no special manoeuvre indicator";
			case 1:
				return "not in special manoeuvre";
			case 2:
				return "in special manoeuvre";
			default:
				return "invalid special manoeuvre indicator";
		}
	}
}