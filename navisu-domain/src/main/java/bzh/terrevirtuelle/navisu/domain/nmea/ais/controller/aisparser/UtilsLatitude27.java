package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (27-bit signed integer) latitude value that is encoded in
 * {@link AISMessagePositionReport}, {@link AISMessageUTCReport}, {@link AISMessage09}, 
 * {@link AISMessage18}, {@link AISMessage19}, or {@link AISMessage21}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsLatitude27 {
	/** The number of integer units in a minute (as a measure of angle). 
	 * The fact that this value is 10000 means that the integer latitude values we are dealing with express an angle in units of 1/10000 minute.	 */
	private static final int UnitsPerMinute = 10000; //10 000 units/minute

	/** The number of minutes in a degree (as a measure of angle). */
	private static final int MinutesPerDegree = 60; //60 minutes/degree
	
	/** The multiplication factor to convert latitude values to degrees. */
	private static final double MultiplicationFactorToDegrees = 1.0 / (MinutesPerDegree * UnitsPerMinute);

	/** Converts the latitude value (in 1/10000 minutes) to degrees.
	 * @param value the latitude value that is is encoded in
	 * {@link AISMessagePositionReport}, {@link AISMessageUTCReport}, {@link AISMessage09}, 
	 * {@link AISMessage18}, {@link AISMessage19}, or {@link AISMessage21}.
	 * @return the latitude value in degrees
	 */
	public static double toDegrees ( int value )
	{
		return value * UtilsLatitude27.MultiplicationFactorToDegrees; 
	}
	
	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsLatitude27 () {
	}
}