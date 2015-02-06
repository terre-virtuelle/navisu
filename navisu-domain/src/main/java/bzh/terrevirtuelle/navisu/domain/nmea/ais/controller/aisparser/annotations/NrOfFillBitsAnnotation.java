package nl.esi.metis.aisparser.annotations;

public class NrOfFillBitsAnnotation implements Annotation{
	private int nrOfFillBits;
	
	public NrOfFillBitsAnnotation (int nrOfFillBits)
	{
		this.nrOfFillBits = nrOfFillBits;
	}
	
	public String toString()
	{
	  return "Deviation from standards, more than 5 fill bits used: " + nrOfFillBits;
	}
}
