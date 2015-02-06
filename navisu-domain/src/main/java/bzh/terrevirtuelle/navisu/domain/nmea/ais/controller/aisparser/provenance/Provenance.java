package nl.esi.metis.aisparser.provenance;

import java.util.List;

import nl.esi.metis.aisparser.annotations.Annotation;

/** This interface provides access to information on where a particular information item (e.g., an AIS message) originally came from.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public interface Provenance {
	/** Returns the time stamp when the information item was created.
	 * @return the time stamp (in seconds from January 1, 1970)
	 */
	public double getTime();
	
	/** Returns a description of the provenance of the information item.
	 * @param layout a string that should be added to the beginning of every line
	 * @return a string describing the provenance, possibly consisting of multiple lines
	 */
	public String getProvenanceTree(String layout);
	
	/** Returns all quality of information annotations associated with the information item in its provenance tree.
	 * When getAnnotations().length == 0, the quality of information associated with the information item is perfect.
	 * @return a list of annotations
	 */
	public List<Annotation> getAnnotations();
}
