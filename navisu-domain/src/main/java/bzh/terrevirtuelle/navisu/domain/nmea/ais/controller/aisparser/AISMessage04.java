package nl.esi.metis.aisparser;

/** This interface represents an AIS message of type 4: Base station report.
 * It offers methods to retrieve the value of all fields, 
 * although all these methods are inherited from {@link AISMessageUTCReport}.
 * AIS type 4 messages are periodically transmitted by base stations to report UTC time and date, and at the same time their position.
 * @author Pierre van de Laar
 * @author Pierre America
 */
public interface AISMessage04 extends AISMessageUTCReport {

}