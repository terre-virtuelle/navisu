package nl.esi.metis.aisparser;

/** This class provides functions to analyze the (6-bit integer) time stamp value that is returned by 
 * {@link AISMessagePositionReport#getTimeStamp()}, {@link AISMessage09#getTimeStamp()}, 
 * {@link AISMessage18#getTimeStamp()}, {@link AISMessage19#getTimeStamp()}, or {@link AISMessage21#getTimeStamp()}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public class UtilsTimeStamp {

	/** The minimum time stamp value. */
	private static final int MINVALUE = 0;

	/** The maximum time stamp value. */
	private static final int MAXVALUE = 59;

	/** Checks if the time stamp value is available.
	 * This means that it is a value in between the minimum (0) and maximum (59) valid time stamp values.
	 * @param value the time stamp value that is returned by 
     * {@link AISMessagePositionReport#getTimeStamp()}, {@link AISMessage09#getTimeStamp()}, 
     * {@link AISMessage18#getTimeStamp()}, {@link AISMessage19#getTimeStamp()}, or {@link AISMessage21#getTimeStamp()}.
	 * @return true if the time stamp is available
	 */
	public static boolean isAvailable(int value)
	{
		return (value >= MINVALUE && value <= MAXVALUE); 
	}
	
	/** Extends a time stamp value consisting of seconds only to a full time stamp by using another full time stamp as a reference.
	 * The input {@code messageSeconds} is assumed to come from an AIS message, where it represents the UTC second when the position was measured in the transmitting ship.
	 * If {@code receivedTime} is, for example, the time when the AIS message arrived at a receiver, 
	 * this method attempts to find the full time stamp when position was measured.
	 * It does this by taking the time nearest to {@code receivedTime} where {@code messageSeconds} is the number of seconds.
	 * @param messageSeconds a valid time stamp as returned by 
	 * {@link AISMessagePositionReport#getTimeStamp()}, {@link AISMessage09#getTimeStamp()}, 
     * {@link AISMessage18#getTimeStamp()}, {@link AISMessage19#getTimeStamp()}, or {@link AISMessage21#getTimeStamp()}.
	 * @param receivedTime a time stamp, measured in seconds from January 1, 1970
	 * @return the time, measured in seconds from January 1, 1970, 
	 *         where the seconds value is equal to {@code messageSeconds} and that is nearest to {@code receivedTime}
	 * @precondition isAvailable(messageSeconds)
	 */
	public static long convertToFullTimeStamp (int messageSeconds, double receivedTime) {
		assert (isAvailable(messageSeconds));
		long receivedMinutes = (long) (receivedTime / 60); // this should truncate the value
		double receivedSeconds = receivedTime - receivedMinutes*60;
		double differenceSeconds = receivedSeconds - messageSeconds;
		long resultMinutes = receivedMinutes;

		if (differenceSeconds < -30)
			resultMinutes--;
		else if (differenceSeconds > 30)
			resultMinutes++;
		
		return resultMinutes * 60 + messageSeconds;
	}
	
	/** Generates a text string representing the time stamp value.
	 * @param timeStamp a valid time stamp as returned by 
	 * {@link AISMessagePositionReport#getTimeStamp()}, {@link AISMessage09#getTimeStamp()}, 
     * {@link AISMessage18#getTimeStamp()}, {@link AISMessage19#getTimeStamp()}, or {@link AISMessage21#getTimeStamp()}. 
	 * @return a string representing the time stamp
	 */
	public static String toString (int timeStamp) {
		switch (timeStamp) {
		case 60:
			return "no time stamp";
		case 61: 
			return "positioning system manual";
		case 62:
			return "dead reckoning";
		case 63:
			return "positioning system inoperative";
		default:
			return Integer.toString(timeStamp);
		}
	}
	
	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsTimeStamp () {
	}	
}
