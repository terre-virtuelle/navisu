package nl.esi.metis.aisparser;

/** This interface represents an AIS message with an unknown message ID, i.e, the message ID falls outside the range [1-27] defined by the standard.
 * It does not add any new methods to its superinterface, since all known information can be found there.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public interface AISMessageUnknownMessageId extends AISMessage{

}