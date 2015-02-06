package nl.esi.metis.aisparser;

import java.text.DecimalFormat;

public class UtilsPositionInfo {
	/** The format we use for longitude & latitude */
	private static final DecimalFormat COORD_FORMAT = new DecimalFormat("##0.000000;-##0.000000");

	
	private static final int LONGITUDE_NOTAVAILABLE = 181;

	/** Checks if the longitude value is semantically correct (according to the standard). 
	 * This means that longitude value is either within the available range as specified by the standard, 
	 * or equal to the standard value indicating unavailability.
	 * I.e. &plusmn;180&deg; or 181&deg; (not available).
	 * @param longitude the longitude value in degrees 
	 * @return true if the longitude is semantically correct
	 */
	//TODO: messages of type 1,2,3,4,9,11,17,18,19,21 have an explicitly default /not available value, but messages of type 27 do not. Should they NOT accept the default value as semantically correct? 
	public static boolean isLongitudeSemanticallyCorrect( double longitude )
	{
		return isLongitudeAvailable(longitude) || (longitude == LONGITUDE_NOTAVAILABLE);
	}
	
	/** Checks if the longitude value is available.
	 * For robustness against semantical violations, this is not checked by NOT(longitude == 181), but by
	 * checking whether the longitude value is within the available range as specified by the standard. 
	 * I.e. &plusmn;180&deg;.
	 * @param longitude the longitude value in degrees
	 * @return true if the longitude is available
	 */
	public static boolean isLongitudeAvailable(double longitude)
	{
		return ( -180 <= longitude ) && ( longitude <= 180 ); 
	}
	
	/** semantically correct range of Longitude
	 * 
	 */
	public static final String rangeLongitude = "[-180.0, 180.0] + {"+ LONGITUDE_NOTAVAILABLE +"}";
	
	/** Returns the longitude as a string.
	 * @param longitude the longitude value in degrees
	 * @return a string representing the longitude
	 */
	public static String longitudeToString(double longitude) {
		String lonString;
		if (isLongitudeSemanticallyCorrect(longitude))
		{
			if (isLongitudeAvailable(longitude))
			{
				lonString = COORD_FORMAT.format(longitude);
			}
			else
			{
				lonString = "longitude not available";	
			}
		}
		else
		{
			lonString = "invalid longitude";
		}
		return lonString;
	}

	
	
	private static final int LATITUDE_NOTAVAILABLE = 91;

	/** Checks if the latitude value is semantically correct (according to the standard). 
	 * This means that latitude value is either within the available range as specified by the standard, 
	 * or equal to the standard value indicating unavailability.
	 * I.e. &plusmn;90&deg; or 91&deg; (not available).
	 * @param latitude the latitude value in degrees 
	 * @return true if the latitude is semantically correct
	 */
	public static boolean isLatitudeSemanticallyCorrect( double latitude )
	{
		return isLatitudeAvailable(latitude) || (latitude == LATITUDE_NOTAVAILABLE);
	}
	
	/** Checks if the latitude value is available.
	 * For robustness against semantical violations, this is not checked by NOT(latitude == 91), but by
	 * checking whether the latitude value is within the available range as specified by the standard. 
	 * I.e. &plusmn;90&deg;.
	 * @param latitude the latitude value in degrees
	 * @return true if the latitude is available
	 */
	public static boolean isLatitudeAvailable(double latitude)
	{
		return ( -90 <= latitude ) && ( latitude <= 90 ); 
	}
	
	/** semantically correct range of Latitude
	 * 
	 */
	public static final String rangeLatitude = "[-90.0, 90.0] + {"+ LATITUDE_NOTAVAILABLE +"}";
	
	/** Returns the latitude as a string.
	 * @param latitude the latitude value in degrees. 
     * @return a string representing the latitude
	 */
	public static String latitudeToString(double latitude) {
		String latString;
		if (isLatitudeSemanticallyCorrect(latitude))
		{
			if (isLatitudeAvailable(latitude))
			{
				latString = COORD_FORMAT.format(latitude);
			}
			else
			{
				latString = "latitude not available";	
			}
		}
		else
		{
			latString = "invalid latitude";
		}
		return latString;

	}
	
	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsPositionInfo () {
	}
}