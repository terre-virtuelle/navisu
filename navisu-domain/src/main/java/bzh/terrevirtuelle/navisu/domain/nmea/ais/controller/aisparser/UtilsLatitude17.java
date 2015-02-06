package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (17-bit signed integer) latitude value that is encoded in 
 * {@link AISMessage17}, {@link AISMessage27}, and 
 * {@link DestinationArea} of {@link AISMessage22}, {@link AISMessage23}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsLatitude17 {
	/** The number of integer units in a minute (as a measure of angle). 
	 * The fact that this value is 10 means that the integer latitude values we are dealing with express an angle in units of 1/10 minute.	 */
	private static final long UnitsPerMinute = 10;
	
	/** The number of minutes in a degree (as a measure of angle). */
	private static final long MinutesPerDegree = 60;
	
	/** The multiplication factor to convert latitude values to degrees. */
	private static final double MultiplicationFactorToDegrees = 1.0 / (MinutesPerDegree * UnitsPerMinute);

	/** Converts the latitude value (in 1/10 minutes) to degrees.
	 * @param value the latitude value that is is encoded in 
	 * {@link AISMessage17}, {@link AISMessage27}, and 
	 * {@link DestinationArea} of {@link AISMessage22}, {@link AISMessage23}.
	 * @return the latitude value in degrees
	 */
	public static double toDegrees ( long value )
	{
		return value * UtilsLatitude17.MultiplicationFactorToDegrees; 
	}
	
	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsLatitude17 () {
	}
}