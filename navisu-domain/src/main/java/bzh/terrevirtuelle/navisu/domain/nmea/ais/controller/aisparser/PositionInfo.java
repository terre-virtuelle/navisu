package nl.esi.metis.aisparser;

/** This is the common interface for all types of AIS messages that provide position information. 
 * @author Pierre van de Laar
 */
public interface PositionInfo {
	/** Returns the position accuracy.
	 * @return a boolean value representing position accuracy: <br>
	 * true = high (&le; 10 m) <br>
	 * false = low (&gt; 10 m)
	 */
	public boolean getPositionAccuracy();

	/** Returns the longitude in degrees.
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the longitude in degrees (&plusmn;180&deg;, East = positive, West = negative; 181 not available).
	 */
	public double getLongitudeInDegrees();
	
	/** Returns the latitude in degrees.
	 * This value can be analyzed further with utility class {@link UtilsPositionInfo}.
	 * @return a double value representing the latitude in degrees (&plusmn;90&deg;, North = positive, South = negative; 91 not available).
	 */
	public double getLatitudeInDegrees();
}
