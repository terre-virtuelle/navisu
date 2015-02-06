package nl.esi.metis.aisparser;

/** This class provides methods to analyze the user ID value returned by {@link AISMessage#getUserID()}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public class UtilsUserId30 {
	/** The minimum valid value for the user ID. */
	private static final long MINVALUE = 0; 		//unsigned value
	
	/** The maximum valid value for the user ID. */
	private static final long MAXVALUE = 999999999; //nine digits
	
	/** Checks whether a user ID value is semantically correct (according to the standard).
	 * 
	 * See Annex 1 paragraph 3         page 3  and
	 *     Annex 2 paragraph 3.3.7.2.1 page 40 of ITU-R M.1371-4: Only the first 9 digits should be used.
	 * @param value the user ID value as returned by {@link AISMessage#getUserID()}.
	 * @return true if the value is semantically correct.
	 */
	public static boolean isSemanticallyCorrectValue( long value )
	{
		return ( (MINVALUE <= value) && (value <= MAXVALUE ) );
	}
	
	/** Returns the region of origin (or other category) associated with the user ID (MMSI number), inferred from the first digit.
	 * See <a href=http://en.wikipedia.org/wiki/Maritime_Mobile_Service_Identity#The_first_digit_of_an_MMSI>Wikipedia</a> for more information.
	 * 
	 * @param mmsi a valid MMSI number as returned by {@link AISMessage#getUserID()}.
	 * @return a string describing the region of origin (or other category) of the transmitter of the AIS message
	 */
	public static String getOrigin (long mmsi)
	{
		int firstDigit = (int) (mmsi/100000000L); 
		switch (firstDigit)
		{
			case 0:
				return "Ship group, coast station, or group of coast stations";
			case 1:
				return "SAR aircraft";
			case 2:
				return "Europe";
			case 3:
				return "North and Central America and Caribbean";
			case 4:
				return "Asia";
			case 5:
				return "Oceana";
			case 6:
				return "Africa";
			case 7:
				return "South America";
			case 8:
				return "Assigned for regional Use";
			case 9:
				return "Nav aids or craft associated with a parent ship";
			default:	
				return "Invalid MMSI number";
		}
	}

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsUserId30 () {
	}
}