/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This is the common interface for all types of AIS messages that provide UTC time and position reports. 
 * Such messages can be transmitted both by mobile stations (i.e., ships and other vehicles) and by base stations.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessageUTCReport extends AISMessage, PositionInfo{

	/** Returns the UTC year.
	 * @return an integer value representing the UTC year (1-9999) <br>
	 * 0 = UTC year not available
	 */
	public int getUtcYear();

	/** Returns the UTC month.
	 * @return an integer value representing the UTC month (1-12) <br>
	 * 0 = UTC month not available <br>
	 * 13-15 not used
	 */
	public int getUtcMonth();

	/** Returns the UTC day.
	 * @return an integer value representing the UTC day (1-31) <br>
	 * 0 = UTC day not available
	 */
	public int getUtcDay();

	/** Returns the UTC hour.
	 * @return an integer value representing the UTC hour (0-23) <br>
	 * 24 = UTC hour not available <br>
	 * 25-31 not used
	 */
	public int getUtcHour();

	/** Returns the UTC minute.
	 * @return an integer value representing the UTC minute (0-59) <br>
	 * 60 = UTC minute not available <br>
	 * 61-63 not used
	 */
	public int getUtcMinute();

	/** Returns the UTC second.
	 * @return an integer value representing the UTC second (0-59) <br>
	 * 60 = UTC second not available <br>
	 * 61-63 not used
	 */
	public int getUtcSecond();

	/** Returns the type of electronic position fixing device.
	 * @return an integer value representing of the type of electronic position fixing device: <br>
	 * 0 = undefined (default) <br>
	 * 1 = global positioning system (GPS) <br>
	 * 2 = GNSS (GLONASS) <br>
	 * 3 = combined GPS/GLONASS <br>
	 * 4 = Loran-C <br>
	 * 5 = Chayka <br>
	 * 6 = integrated navigation system <br>
	 * 7 = surveyed <br>
	 * 8 = Galileo <br>
	 * 9-14 = not used <br>
	 * 15 = internal GNSS
	 */
	public int getTypeOfElectronicPositionFixingDevice();

	/** Returns the transmission control for long range broadcast message.
	 * @return a boolean value: <br>
	 * false = default: Class-A AIS station stops transmission of Message 27 within an AIS base station coverage area. <br>
	 * true = Request Class-A station to transmit Message 27 within an AIS base station coverage area.
	 */
	public boolean getTransmissionControlForLongRangeBroadcastMessage();

	/** Returns the spare bits.
	 * @return the integer value of the spare bits. This should be zero.
	 */
	public int getSpare();

	/** Returns the RAIM flag, which describes the receiver autonomous integrity monitoring status of the electronic position fixing device.
	 * @return a boolean value: <br>
	 * false = RAIM not in use <br>
	 * true = RAIM in use
	 */
	public boolean getRaimFlag();

	/** Returns the communication state.
	 * @return the SOTDMA value of the communication state.
	 */
	public Sotdma getCommunicationState();

}