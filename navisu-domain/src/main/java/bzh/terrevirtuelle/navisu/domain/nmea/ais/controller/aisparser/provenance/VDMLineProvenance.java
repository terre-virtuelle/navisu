package nl.esi.metis.aisparser.provenance;

import java.util.List;
import java.util.ArrayList;

import nl.esi.metis.aisparser.annotations.Annotation;

/** An object of this class indicates that information is interpreted as a valid VDM Line.
 * 
 * It implements the {@link Provenance} interface.
 * @author Pierre van de Laar
 */
public class VDMLineProvenance implements Provenance {
	/** The source of the line */
	private Provenance source;
	
	/** Returns the source of the line. */
	public Provenance getSource () {
		return source;
	}

	/** Returns the time stamp when the information item was created. */
	public double getTime() {
		return source.getTime();
	}
	
	/** The annotations associated with this VDM Line */
	private List<Annotation> annotations = new ArrayList<Annotation>();

	/** Returns annotations associated with the AIVDMLine.
	 * @return a list of annotations
	 */
	@Override
	public List<Annotation> getAnnotations() {
		List<Annotation> retval = new ArrayList<Annotation>();
		retval.addAll(annotations);
		retval.addAll(source.getAnnotations());
		return retval;
	}

	/** Returns a description of the provenance of the information item.
	 * @param layout a string that should be added to the beginning of every line
	 * @return a string describing the provenance, possibly consisting of multiple lines
	 */
	public String getProvenanceTree(String layout) {
			return source.getProvenanceTree(layout);
	}


	/** Constructs a new object describing information that was interpreted as AIVDMLine.
	 * @param source source that yielded AIVDMLine input
	 * @param annotations Annotations specifically associated with this element
	 */
	public VDMLineProvenance (Provenance source, List<Annotation> annotations)
	{
		this.source = source;
		this.annotations = annotations;
	}
}