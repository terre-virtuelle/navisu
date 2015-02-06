package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 2: Position report.
 * It offers methods to retrieve the value of all fields, 
 * although most of these methods are inherited from {@link AISMessagePositionReport}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage02 extends AISMessagePositionReport {

	/** Returns the communication state.
	 * @return the SOTDMA value of the communication state
	 */
	public Sotdma getCommunicationState();

}