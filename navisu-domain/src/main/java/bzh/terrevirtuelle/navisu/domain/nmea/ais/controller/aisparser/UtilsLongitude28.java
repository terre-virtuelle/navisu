package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (28-bit signed integer) longitude value that is encoded in
 * {@link AISMessagePositionReport}, {@link AISMessageUTCReport}, {@link AISMessage09}, 
 * {@link AISMessage18}, {@link AISMessage19}, or {@link AISMessage21}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsLongitude28 {
	/** The number of integer units in a minute (as a measure of angle). 
	 * The fact that this value is 10000 means that the integer longitude values we are dealing with express an angle in units of 1/10000 minute.	 */
	private static final int UnitsPerMinute = 10000; //10 000 units/minute

	/** The number of minutes in a degree (as a measure of angle). */
	private static final int MinutesPerDegree = 60; //60 minutes/degree
	
	/** The multiplication factor to convert longitude values to degrees. */
	private static final double MultiplicationFactorToDegrees = 1.0 / (MinutesPerDegree * UnitsPerMinute);

	/** Converts the longitude value (in 1/10000 minutes) to degrees.
	 * @param value the longitude value that is encoded in
	 * {@link AISMessagePositionReport}, {@link AISMessageUTCReport}, {@link AISMessage09}, 
 	 * {@link AISMessage18}, {@link AISMessage19}, or {@link AISMessage21}.
	 * @return the longitude value in degrees
	 */
	public static double toDegrees ( int value )
	{
		return value * UtilsLongitude28.MultiplicationFactorToDegrees; 
	}
	
	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsLongitude28 () {
	}
}