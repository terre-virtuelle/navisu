package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.Sotdma;

/** This interface represents an AIS message of type 1: Position report.
 * It offers methods to retrieve the value of all fields, 
 * although most of these methods are inherited from {@link AISMessagePositionReport}.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage01 extends AISMessagePositionReport {

	/** Returns the communication state.
	 * @return the SOTDMA value of the communication state
	 */
        @Override
	public Sotdma getCommunicationState();

}