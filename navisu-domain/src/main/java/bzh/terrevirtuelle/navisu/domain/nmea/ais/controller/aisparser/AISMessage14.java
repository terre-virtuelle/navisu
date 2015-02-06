/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 14: Safety Related Broadcast.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage14 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** safetyRelatedText
	 * @return String value of safetyRelatedText [maximally 161 6-bit ASCII characters] (966 bits [41,1006])
	 */
	public String getSafetyRelatedText();

	/** spare2
	 * @return int value of spare2 (2 bits [1007,1008])
	 */
	public int getSpare2();

}