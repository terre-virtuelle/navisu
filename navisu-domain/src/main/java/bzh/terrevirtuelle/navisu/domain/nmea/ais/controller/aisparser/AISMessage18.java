/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 18: Standard Class B equipment position report.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage18 extends AISMessageClassBPositionReport {

	/** Returns the class B unit flag, which indicates the kind of AIS unit. 
	 * @return a boolean value representing the class B unit flag: <br>
	 * false = Class B SOTDMA unit <br>
	 * true = Class B "CS" unit
	 */
	public boolean getClassBUnitFlag();

	/** Returns the class B display flag, which indicates whether the unit is equipped with an integrated display displaying Message 12 and 14.
	 * @return a boolean value representing the class B display flag: <br>
	 * false = No display available; not capable of displaying Message 12 and 14 <br>
	 * true = Equipped with integrated display displaying Message 12 and 14
	 */
	public boolean getClassBDisplayFlag();

	/** Returns the class B DSC flag, which indicates whether the unit has the digital selective calling (DSC) function.
	 * @return a boolean value representing the class B DSC flag: <br>
	 * false = Not equipped with DSC function <br>
	 * true = Equipped with DSC function (dedicated or time-shared)
	 */
	public boolean getClassBDSCFlag();

	/** Returns the class B band flag, which indicates over which radio bands the unit can communicate.
	 * @return a boolean value representing the class B band flag: <br>
	 * false = Capable of operating over the upper 525 kHz band of the marine band <br>
	 * true = Capable of operating over the whole marine band <br>
	 * (irrelevant if "Class B Message 22 flag" is 0)
	 */
	public boolean getClassBBandFlag();

	/** Returns the class B Message 22 flag, which indicates whether the unit supports frequency management via Message 22.
	 * @return a boolean value representing the class B Message 22 flag: <br>
	 * false = No frequency management via Message 22, operating on AIS1, AIS2 only <br>
	 * true = Frequency management via Message 22
	 */
	public boolean getClassBMessage22Flag();

	/** Returns the communication state selector flag.
	 * @return a boolean value representing the communication state selector flag: <br>
	 * false = SOTDMA communication state: {@link #getCommunicationState()} will return an {@link Sotdma} object. <br>
	 * true = ITDMA communication state: {@link #getCommunicationState()} will return an {@link Itdma} object. <br>
	 * (always true for Class-B "CS" units)
	 */
	public boolean getCommunicationStateSelectorFlag();

	/** Returns the communication state.
	 * @return the value of the communication state. 
	 * Depending on the {@linkplain #getCommunicationStateSelectorFlag communication state selector flag}, 
	 * this may be {@link Itdma} or {@link Sotdma}.
	 */
	public CommunicationState getCommunicationState();

}