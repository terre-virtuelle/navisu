/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 15: Interrogation.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage15 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** spare2
	 * @return int value of spare2 (2 bits [89,90])
	 */
	public int getSpare2();

	/** spare3
	 * @return int value of spare3 (2 bits [109,110] {or 4?} )
	 */
	public int getSpare3();

	/** spare4
	 * @return int value of spare4 (2 bits [159,160])
	 */
	public int getSpare4();

	/** interrogations
	 * @return AISMessage15Unit[] value of interrogations
	 */
	public AISMessage15Unit[] getInterrogations();

}