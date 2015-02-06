/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 22: Channel Management.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage22 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** channelA
	 * @return int value of channelA (12 bits [41,52])
	 */
	public int getChannelA();

	/** channelB
	 * @return int value of channelB (12 bits [53,64])
	 */
	public int getChannelB();

	/** txrxMode
	 * @return int value of txrxMode (4 bits [65,68])
	 */
	public int getTxrxMode();

	/** power
	 * @return boolean value of power (bit 69)
	 */
	public boolean getPower();

	/** destination
	 * @return Destination value of destination (70 bits [70,139])
	 */
	public Destination getDestination();

	/** addressedMessageIndicator
	 * @return boolean value of addressedMessageIndicator (bit 140)
	 */
	public boolean getAddressedMessageIndicator();

	/** channelABandwidth
	 * @return boolean value of channelABandwidth (bit 141)
	 */
	public boolean getChannelABandwidth();

	/** channelBBandwidth
	 * @return boolean value of channelBBandwidth (bit 142)
	 */
	public boolean getChannelBBandwidth();

	/** transitionalZoneSize
	 * @return int value of transitionalZoneSize (3 bits [143,145])
	 */
	public int getTransitionalZoneSize();

	/** spare2
	 * @return int value of spare2 (23 bits [146,168])
	 */
	public int getSpare2();

}