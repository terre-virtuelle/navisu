package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 3: Position report.
 * It offers methods to retrieve the value of all fields, 
 * although most of these methods are inherited from {@link AISMessagePositionReport}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage03 extends AISMessagePositionReport {

	/** Returns the communication state.
	 * @return the ITDMA value of the communication state
	 */
	public Itdma getCommunicationState();

}