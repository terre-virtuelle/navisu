/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.provenance;

import java.util.ArrayList;
import java.util.List;

import nl.esi.metis.aisparser.annotations.Annotation;

/** An object of this class indicates that information is contained in an VDM message that was obtained from one or more sources together.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISMessageProvenance implements Provenance {
	/** This field contains the provenance for the underlying VDM message */
	private VDMMessageProvenance provenance;

	/** Returns a description of the provenance of the information item. 
	 * In this case, it just forward it to the provenance of the underlying VDM Message. 
	 */
	public VDMMessageProvenance getProvenance() {
		return provenance;
	}


	/** Returns a description of the provenance of the information item. 
	 * In this case, it just forward it to the provenance of the underlying VDM Message. 
	 */
	public String getProvenanceTree(String layout) {
		return provenance.getProvenanceTree(layout);
	}

	/** Returns the time stamp when the information item was created. */
	public double getTime() {
		return provenance.getTime();
	}

	/** The annotations associated with this AIS Message */
	private List<Annotation> annotations = new ArrayList<Annotation>();

	/** Returns the annotations associated with this AIS message. */
	@Override
	public List<Annotation> getAnnotations() {
		List<Annotation> retval = new ArrayList<Annotation>();
		retval.addAll(annotations);
		retval.addAll(provenance.getAnnotations());
		return retval;
	}
	
	/** Constructs a provenance object describing an VDM message that was obtained from one or more parts together.
	 * @param provenance the provenance of the VDMMessage object used to create this AIS Message
	 * @param annotations the annotations associated with this AIS Message
	 */
	public AISMessageProvenance (VDMMessageProvenance provenance, List<Annotation> annotations)
	{
		this.provenance = provenance;
		this.annotations = annotations;
	}
}