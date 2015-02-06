package nl.esi.metis.aisparser;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/** This class represent destinations are returned by {@link AISMessage22#getDestination()}, when the message is addressed to individual stations.
 * @author Pierre America
 * @author Pierre van de Laar
 * @author Brian C. Lane
 *
 */
public class DestinationIDs extends Destination {
	
	/** The first station ID (30 bits) */
	private int ID1;	//

	/** Returns the first station ID (30 bits). */
	public int ID1() { return ID1; }
	
	/** The value of the first spare bits */
	private int spare1;	//5 bits

	/** Returns the value of the first spare bits. This should be zero. */
	public int spare1() { return spare1; }
	
	/** The second station ID (30 bits) */
	private int ID2;	//30 bits

	/** Returns the second station ID (30 bits). */
	public int ID2() { return ID2; }

	/** The value of the second set of spare bits */
	private int spare2;	//5 bits

	/** Returns the value of the second set of spare bits. This should be zero. */
	public int spare2() { return spare2; }
	
	/** Constructs a destination ID object.
	 * @param offset the position where to start extracting information from the message
	 * @param content the content of the message
	 */
	public DestinationIDs (int offset, Sixbit content)
	{
		ID1 = content.getIntFromTo(offset, offset+29);
		spare1 = content.getIntFromTo(offset+30, offset+34);
//TODO: check spares and add annotation if not zero		
//		if(spare1 != 0)
//		{
//		   	annotations.add(new AISSpareNonZeroAnnotation(1));
//		}
		
		ID2 = content.getIntFromTo(offset+35, offset+64);
		spare2 = content.getIntFromTo(offset+65, offset+69);
//TODO: check spares and add annotation if not zero
//		if(spare2 != 0)
//		{
//		  	annotations.add(new AISSpareNonZeroAnnotation(2));
//		}
	}

}
