package nl.esi.metis.aisparser;

import nl.esi.metis.aisparser.provenance.AISMessageProvenance;

/**
 * This is the common interface for all AIS messages.
 * These objects represent messages as they exchanged between ships, other vehicles in particular aircraft involved in search-and-rescue (SAR), and (fixed) base stations.
 * 
 * @author Pierre van de Laar
 * @author Pierre America
 *
 */
public interface AISMessage {

	/** Returns the message ID.
	 * The message ID identifies the message type, which determines the meaning of the message and the fields that it contains.
	 * For valid messages, it also corresponds to a particular subinterface of this interface.
	 * For example, if the message ID is 5 then the message implements interface <code>AISMessage05</code>.
	 * @return 0-63. Currently valid message IDs range from 1 to 27.
	 */
	public int getMessageID();
	
	/** Returns the repeat indicator.
	 * This indicates how many times a message has been repeated. 
	 * @return an integer value representing the repeat indicator: <br>
	 * 0 = default <br>
	 * 3 = do not repeat any more
	 */
	public int getRepeatIndicator();
	
	/** Returns the user ID.
	 * This is a unique identifier such as the MMSI number of the transmitting ship.
	 * It can be analyzed further using utility class {@link UtilsUserId30}.
	 * See <a href=http://en.wikipedia.org/wiki/Maritime_Mobile_Service_Identity>Wikipedia</a> for more information on MMSI (Maritime Mobile Service Identity).
	 * @return an integer in the range from 0 to 2<sup>30</sup>-1.
	 */
	public int getUserID();

	/** Returns the provenance, i.e., a description of where the AIS message came from, including a time stamp.
	 * @return the provenance
	 */
	public AISMessageProvenance getProvenance();
}