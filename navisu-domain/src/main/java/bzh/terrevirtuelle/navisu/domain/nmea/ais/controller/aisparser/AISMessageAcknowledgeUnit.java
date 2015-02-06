package nl.esi.metis.aisparser;

/** This interface represents a message of which the receipt is acknowledged as part of an {@link AISMessageAcknowledge} message.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public interface AISMessageAcknowledgeUnit {

	/** Returns the destination of this acknowledgment.
	 * @return the MMSI number of the destination
	 */
	public int getDestinationID();

	/** Returns the sequence number of the message to be acknowledged.
	 * @return the sequence number (0-3)
	 */
	public int getSequenceNumberForID();

}