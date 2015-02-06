package nl.esi.metis.aisparser;

import java.text.DecimalFormat;

/** This class provides functions to analyze the (12-bit signed integer) angular value that is returned by 
 * {@link AISMessagePositionReport#getCourseOverGround()}, {@link AISMessage18#getCourseOverGround()} and {@link AISMessage19#getCourseOverGround()}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsAngle12 {
	/** The angular value that is used to signal unavailability, according to the AIS standard. */
	private static final int DEFAULTVALUE = 0xE10;

	/** The minimum valid angular value. */
	private static final int MINVALUE = 0 ;

	/** The maximum valid angular value. */
	private static final int MAXVALUE =  3599;
	
	/** The multiplication factor to convert angular values to degrees. */
	private static final double UNITCONVERSIONFACTOR = 1.0/10;
  
	/** Checks if the course over ground value is semantically correct (according to the standard). 
	 * This means that it is either a valid measurement or the standard value indicating unavailability.
	 * @param value the angular value that is returned by {@link AISMessagePositionReport#getCourseOverGround()}.
	 * @return true if the value is semantically correct
	 */
	public static boolean isAngleSemanticallyCorrect( int value )
	{
		return ( ( UtilsAngle12.MINVALUE <= value ) && ( value <= UtilsAngle12.MAXVALUE ) ) || (value == UtilsAngle12.DEFAULTVALUE);
	}
	
	/** Checks if the angular value is available.
	 * @precondition isCourseOverGroundSemanticallyCorrect (value)
	 * @param value the angular value that is returned by {@link AISMessagePositionReport#getCourseOverGround()}.
	 * @return true if the angular is available
	 */
	public static boolean isAvailable( int value )
	{
		return !(value == UtilsAngle12.DEFAULTVALUE);
	}
	
	/** Converts the angular value (in 1/10 degrees) to degrees.
	 * @param value the angular value that is returned by {@link AISMessagePositionReport#getCourseOverGround()}.
	 * @return the angular  in degrees
	 */
	public static double toDegrees ( int value )
	{
		return value * UtilsAngle12.UNITCONVERSIONFACTOR; 
	}

	/** semantically correct range of course over ground
	 * 
	 */
	public static final String range = "["+MINVALUE+","+MAXVALUE+"] + {"+ DEFAULTVALUE +"}";
	
	/** The format we use to format the course over ground */
	private static final DecimalFormat ANGLE_FORMAT = new DecimalFormat("##0.0;-##0.0");

	/** Generates a text string representing the angular value.
	 * @param value an integer value representing the course over ground, as returned by {@link AISMessagePositionReport#getCourseOverGround()}
	 * @return a string representing the angular value
	 */
	public static String toString(int value) {
		String angleString;
		if (isAngleSemanticallyCorrect(value))
		{
			if (isAvailable(value))
				angleString = UtilsAngle12.ANGLE_FORMAT.format(toDegrees(value));
			else 
				angleString = "not available";
		}
		else
			angleString = "illegal value";
		return angleString;
	}
	
	
}