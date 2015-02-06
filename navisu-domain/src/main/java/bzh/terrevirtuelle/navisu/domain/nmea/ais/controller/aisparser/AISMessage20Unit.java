/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

public interface AISMessage20Unit {

	/** offsetNumber
	 * @return int value of offsetNumber (12 bits)
	 */
	public int getOffsetNumber();

	/** numberOfSlots
	 * @return int value of numberOfSlots (4 bits)
	 */
	public int getNumberOfSlots();

	/** timeOut
	 * @return int value of timeOut (3 bits)
	 */
	public int getTimeOut();

	/** increment
	 * @return int value of increment (11 bits)
	 */
	public int getIncrement();

}