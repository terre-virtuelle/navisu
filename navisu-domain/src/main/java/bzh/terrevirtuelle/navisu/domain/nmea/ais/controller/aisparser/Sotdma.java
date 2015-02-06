package nl.esi.metis.aisparser;
/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar, Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */

/** This class represents the SOTDMA communication state as expressed in type 1 or 2 AIS messages.
 * See page 40 of the standard.
 * <pre>
 * Field Nr     Field Name                         NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1           syncState                                  2   (   1,   2)
 *  2           slotTimeOut                                3   (   3,   5)
 *  3           subMessage                                14   (   6,  19)
 *                                                      ---- +
 *                       (maximum) number of bits         19
 * </pre>
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */
public class Sotdma extends CommunicationState {
	/** The first bit of the sync state */
	private static final int SYNCSTATE_FROM = 0;

	/** The last bit of the sync state */
	private static final int SYNCSTATE_TO = 1;

	/** The sync state */
	private int syncState;

	/** Returns the sync state.
	 * @return an integer value representing the sync state (0-3)
	 */
	public int getSyncState() { return syncState; }

	/** The first bit of the slot time-out */
	private static final int SLOTTIMEOUT_FROM = 2;

	/** The last bit of the slot time-out */
	private static final int SLOTTIMEOUT_TO = 4;

	/** The  slot time-out */
	private int slotTimeOut;

	/** Returns the slot time-out. This specifies the frames remaining until a new slot is selected.
	 * @return an integer value representing the slot time-out: <br>
	 * 0 means that this was the last transmission in this slot <br>
	 * 1-7 means that 1 to 7 frames respectively are left until slot change
	 */
	public int getSlotTimeOut() { return slotTimeOut; }

	/** The first bit of the submessage */
	private static final int SUBMESSAGE_FROM = 5;

	/** The last bit of the submessage */
	private static final int SUBMESSAGE_TO = 18;

	/** The  submessage */
	private int subMessage;

	/** Returns the submessage.
	 * The meaning of submessage depends on the current value in slot time-out
	 * as described in Table 19 on page 40 of the standard.
	 * @return an integer value representing the submessage (14 bits)
	 */
	public int getSubMessage() { return subMessage; }

	/** Constructs an SOTDMA object.
	 * @param offset the position in the message from where to extract information
	 * @param content the content of the message
	 */
	public Sotdma( int offset, Sixbit content )
	{
		assert(offset+SUBMESSAGE_TO <= content.length());

		syncState = content.getIntFromTo(offset+SYNCSTATE_FROM,offset+SYNCSTATE_TO);
		slotTimeOut = content.getIntFromTo(offset+SLOTTIMEOUT_FROM,offset+SLOTTIMEOUT_TO);
		subMessage = content.getIntFromTo(offset+SUBMESSAGE_FROM,offset+SUBMESSAGE_TO);
	}
	

	/** Generates a String representing the Sotdma message. 
	 * Format: all fields are shown in the order as specified by the standard separated by the SEPARATOR string.
	 */
	@Override
	public String toString() {
		return Integer.toString(syncState) + SEPARATOR + Integer.toString(slotTimeOut) + SEPARATOR + Integer.toString(subMessage);
	}
}
