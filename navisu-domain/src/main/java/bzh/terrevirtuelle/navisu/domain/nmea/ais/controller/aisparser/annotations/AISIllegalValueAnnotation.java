/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package nl.esi.metis.aisparser.annotations;


/** This class represents annotations related to deviations in value from the AIS standard that are detected during parsing of AIS messages.
 * These annotations are stored in the provenance of the resulting message objects, so that they can analyzed by a client program.
 * @author Pierre America
 * @author Pierre van de Laar
 */
public class AISIllegalValueAnnotation implements AISAnnotation {
	private String methodName;
	private Object value;
	private String range;
	public AISIllegalValueAnnotation(String methodName, Object value, String range)
	{
		this.methodName = methodName;
		this.value = value;
		this.range = range;
	}
	
	/** Equality
	 * @param obj Object to compare to
	 */
	@Override
    public boolean equals(Object obj) {
         if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        AISIllegalValueAnnotation iva = (AISIllegalValueAnnotation) obj;
        return (this.methodName.equals(iva.methodName)) && (this.value.equals(iva.value)) && (this.range.equals(iva.range));
	}
	
	public String toString()
	{
		return "Violation of standard: Value " + value.toString() + " of method " + methodName + " is outside the allowed range of " + range;
	}
}
