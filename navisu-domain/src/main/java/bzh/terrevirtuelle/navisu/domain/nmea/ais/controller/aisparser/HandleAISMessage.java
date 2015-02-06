package nl.esi.metis.aisparser;

/** This interface must be implemented by a client that wishes to process AIS messages.
 * @see AISParser
 * @author Pierre America
 * @author Pierre van de Laar
 */
public interface HandleAISMessage {
	/** This message will be called for each AIS message that the parser encounters.
	 * It can then do something useful with the message.
	 * @param message an object representing a parsed AIS message. 
	 *        In most cases it will be useful to check which of the subinterfaces of {@link AISMessage} is implemented.
	 */
	public void handleAISMessage (AISMessage message);
}
