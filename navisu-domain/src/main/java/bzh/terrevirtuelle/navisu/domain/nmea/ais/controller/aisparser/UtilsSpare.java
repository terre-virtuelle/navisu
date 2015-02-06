package nl.esi.metis.aisparser;

/** This class provides functions to analyze spares.
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class UtilsSpare {
	
	/** Spare values should be equal to zero to be semantically correct
	 * @param value The spare value
	 * @return whether spare is semantically correct
	 */
	public static boolean isSpareSemanticallyCorrect( int value )
	{
		return value == 0;
	}
	
	/** semantically correct range of course over ground
	 * 
	 */
	public static final String range = "{0}";
	

	/** This constructor is made private to indicate that clients should not construct instances of this class. */
	private UtilsSpare () {
	}
}