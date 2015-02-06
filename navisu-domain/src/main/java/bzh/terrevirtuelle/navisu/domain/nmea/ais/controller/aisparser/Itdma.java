package nl.esi.metis.aisparser;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/** This class represents the ITDMA communication state as expressed in type 3 AIS messages.
 * See page 41 of the standard.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1           syncState                                  2    (   1,   2)
 *  2           slotIncrement                             13    (   3,  15)
 *  3           numberOfSlots                              3    (  16,  18)
 *  4           keepFlag                                   1    (  19,  19)
 *                                                      ---- +
 *                           (maximum) number of bits     19
 * </pre>
 * @author Pierre van de Laar
 * @author Pierre America
 * @author Brian C. Lane
 */
public class Itdma extends CommunicationState {
	/** The first bit of the sync state */
	private static final int SYNCSTATE_FROM = 0;

	/** The last bit of the sync state */
	private static final int SYNCSTATE_TO = 1;

	/** The sync state. */
	private int syncState;
	
	/** Returns the sync state.
	 * @return an integer value representing the sync state (0-3)
	 */
	public int getSyncState() { return syncState; }

	/** The first bit of the slot increment */
	private static final int SLOTINCREMENT_FROM = 2;

	/** The last bit of the slot increment */
	private static final int SLOTINCREMENT_TO = 14;

	/** The slot increment */
	private int slotIncrement;

	/** Returns the slot increment.
	 * @return an integer value representing the slot increment (13 bits)
	 */
	public int getSlotIncrement() { return slotIncrement; }

	/** The first bit of the number of slots */
	private static final int NUMBEROFSLOTS_FROM = 15;

	/** The last bit of the number of slots */
	private static final int NUMBEROFSLOTS_TO = 17;

	/** The number of slots */
	private int numberOfSlots;

	/** Returns the number of consecutive slots to allocate.
	 * @return an integer value representing the number of slots: <br>
	 * 0 = 1 slot <br>
	 * 1 = 2 slots <br>
	 * 2 = 3 slots <br>
	 * 3 = 4 slots <br>
	 * 4 = 5 slots <br>
	 * 5 = 1 slot; offset = slot increment + 8192 <br>
	 * 6 = 2 slots; offset = slot increment + 8192 <br>
	 * 7 = 3 slots; offset = slot increment + 8192
	 */
	public int getNumberOfSlots() { return numberOfSlots; }

	/** The position of the keep flag bit. */
	private static final int KEEPFLAG_BITINDEX = 18;

	/** The keep flag */
	private boolean keepFlag;

	/** Returns the keep flag.
	 * @return true if the slot remains allocated for one additional frame
	 */
	public boolean getKeepFlag() { return keepFlag; }

	/** Constructs an ITDMA object.
	 * @param offset the position in the message from where to extract information
	 * @param content the content of the message
	 */
	public Itdma( int offset, Sixbit content )
	{
		assert(offset+KEEPFLAG_BITINDEX <= content.length());

		syncState = content.getIntFromTo(offset+SYNCSTATE_FROM,offset+SYNCSTATE_TO);
		slotIncrement = content.getIntFromTo(offset+SLOTINCREMENT_FROM,offset+SLOTINCREMENT_TO);
		numberOfSlots = content.getIntFromTo(offset+NUMBEROFSLOTS_FROM,offset+NUMBEROFSLOTS_TO);
		keepFlag = content.getBoolean(offset+KEEPFLAG_BITINDEX);
	}
	
	/** Generates a String representing the Sotdma message. 
	 * Format: all fields are shown in the order as specified by the standard separated by the SEPARATOR string.
	 */
	@Override
	public String toString() {
		return Integer.toString(syncState) + SEPARATOR + Integer.toString(slotIncrement) + SEPARATOR + Integer.toString(numberOfSlots) +  SEPARATOR + keepFlag;
	}
}