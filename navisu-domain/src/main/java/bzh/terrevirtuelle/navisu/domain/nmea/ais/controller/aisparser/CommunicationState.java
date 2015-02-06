package nl.esi.metis.aisparser;

/** This abstract class represents the communication state as returned by {@link AISMessagePositionReport#getCommunicationState()}.
 * The real information is handled by the subclasses. 
 * 
 * @author Pierre America
 * @author Pierre van de Laar
 *
 */
public abstract class CommunicationState {
	/** This string is used by this class (and its subclasses) to separate the different fields in generated strings. */
	public static final char SEPARATOR = '|';
}
