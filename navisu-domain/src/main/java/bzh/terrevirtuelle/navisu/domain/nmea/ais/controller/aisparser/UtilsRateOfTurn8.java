package nl.esi.metis.aisparser;

/** This class provides functions to analyze the rate-of-turn value that is returned by {@link AISMessagePositionReport#getRateOfTurn()}.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsRateOfTurn8 {
	/** The rate-of-turn value that is used to signal unavailability, according to the AIS standard. */
	private static final int DEFAULTVALUE = -0x80;

	/** The minimum rate-of-turn value when a turn indicator is available. */
	private static final int MINTurnIndicatorVALUE = -126;

	/** The maximum rate-of-turn value when a turn indicator is available. */
	private static final int MAXTurnIndicatorVALUE = 126;

	/** Checks if rate-of-turn information is available.
	 * This means that the value does not signal unavailability. 
	 * It is still possible that no turn indicator is available (see {@link UtilsRateOfTurn8#isTurnIndicatorAvailable}).
	 * @param value the rate-of-turn value that is returned by {@link AISMessagePositionReport#getRateOfTurn()}.
	 * @return true if the rate-of-turn is available
	 */
	public static boolean isTurnInformationAvailable( int value )
	{
		return value != UtilsRateOfTurn8.DEFAULTVALUE;
	}

	/** Checks if a turn indicator is available.
	 * This means that the rate-of-turn value provides a numeric estimate of the rate-of-turn, 
	 * which can be computed using {@link UtilsRateOfTurn8#toDegreesPerMinute}.
	 * @param value the rate-of-turn value that is returned by {@link AISMessagePositionReport#getRateOfTurn()}.
	 * @return true if the rate-of-turn is available
	 */
	public static boolean isTurnIndicatorAvailable( int value )
	{
		return (UtilsRateOfTurn8.MINTurnIndicatorVALUE <= value) && (value <= UtilsRateOfTurn8.MAXTurnIndicatorVALUE);
	}

	/** Converts the rate-of-turn value ROT<sub>AIS</sub> to a numerical estimate for the rate-of-turn ROT<sub>sensor</sub> (in degrees/minute).
	 * 
	 * Page 101 of the AIS standard ITU-R M.1371-4 states: 
	 * 			ROT<sub>AIS</sub> = 4.733 SQRT(ROT<sub>sensor</sub>)
	 * <br>
	 * Note that a ROT<sub>AIS</sub> of &plusmn;126 indicates a rate-of-turn of 708 degrees/min <b>or higher</b>.
	 * @precondition isTurnIndicatorAvailable(rot)
	 * @param rot the rate-of-turn value ROT<sub>AIS</sub> that is returned by {@link AISMessagePositionReport#getRateOfTurn()}.
	 * @return a numerical estimate for the rate-of-turn ROT<sub>sensor</sub> (in degrees/minute) (positive sign indicates turning right)
	 */
	public static double toDegreesPerMinute ( int rot )
	{
		assert(isTurnIndicatorAvailable(rot));
		double v = rot/4.733;
		double v2 = v*v; //	Math.pow(v, 2)
		if(rot < 0)
		{
			return -v2;
		}
		else
		{
			return v2;
		}
	}		
	
	/** Makes human-readable text from a rate-of-turn value.
	 * @param rot the rate-of-turn value that is returned by {@link AISMessagePositionReport#getRateOfTurn()}.
	 * @return a human-readable string represent the rate-of-turn information
	 */
	public static String toString(int rot)
	{
		String direction;			//sign of rot is encoded in direction
		if (rot < 0)
		{	direction = "left";
		}
		else
		{
			direction = "right";
		}
		
		switch (Math.abs(rot))
		{
			case 128: return "no turn information available (default)";
			case 127: return "turning " + direction + " at more than 5 degrees per 30 s (No TI available)";
			case 126: return "turning " + direction + " at 708 degrees per min or higher";
			case 0 : return "not turning";
			default : 
				return "turning " + direction + " at " + Math.abs(toDegreesPerMinute(rot)) + " degrees per min";
														//abs value since sign of rot is encoded in direction
		}
	}

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsRateOfTurn8 () {
	}
}