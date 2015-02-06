/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 23: Group Assignment Command.
*
* @author Pierre van de Laar
* @author Pierre America
* @author Brian C. Lane
*/
public interface AISMessage23 extends AISMessage {

	/** spare1
	 * @return int value of spare1 (2 bits [39,40])
	 */
	public int getSpare1();

	/** destinationArea
	 * @return DestinationArea value of destinationArea (70 bits [41,110])
	 */
	public DestinationArea getDestinationArea();

	/** stationType
	 * @return int value of stationType (4 bits [111,114])
	 */
	public int getStationType();

	/** typeOfShipAndCargoType
	 * @return int value of typeOfShipAndCargoType (8 bits [115,122])
	 */
	public int getTypeOfShipAndCargoType();

	/** spare2
	 * @return int value of spare2 (22 bits [123,144])
	 */
	public int getSpare2();

	/** txrxMode
	 * @return int value of txrxMode (2 bits [145,146])
	 */
	public int getTxrxMode();

	/** reportingInterval
	 * @return int value of reportingInterval (4 bits [147,150])
	 */
	public int getReportingInterval();

	/** quietTime
	 * @return int value of quietTime (4 bits [151,154])
	 */
	public int getQuietTime();

	/** spare3
	 * @return int value of spare3 (6 bits [155,160])
	 */
	public int getSpare3();

}