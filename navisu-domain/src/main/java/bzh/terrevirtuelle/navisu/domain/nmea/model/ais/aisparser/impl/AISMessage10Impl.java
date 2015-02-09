package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.AISMessage10;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.UtilsSpare;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.annotations.AISIllegalValueAnnotation;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.aisparser.provenance.VDMMessageProvenance;




/** AIS Message 10
 * UTC and date inquiry
 * 
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	userID                                  	  30	(   9,  38)
 *  4	spare1                                  	   2	(  39,  40)
 *  5	destinationID                           	  30	(  41,  70)
 *  6	spare2                                  	   2	(  71,  72)
 *                                                   ---- +
 *                       (maximum) number of bits     72
 */
class AISMessage10Impl extends AISMessageImpl implements AISMessage10 {
    private static final int LENGTH = 72;
    public static boolean validLength(int length)
    {
    	return length == LENGTH;
    }
    
	/**
	 * Return the difference in available and needed bits to parse this sixbit as an AISMessage
	 * A positive difference indicates that there are more bits available than needed by the standard.
	 * @param sb
	 * @return
	 */
	public static int differenceInBits(Sixbit sb)
	{
		return (sb.available() - LENGTH);
	}


    private static final int SPARE1_FROM = 39;
    private static final int SPARE1_TO = 40;

    private int spare1;
    /** spare1
     * @return int value of spare1 (2 bits [39,40])
     */
    public int getSpare1() { return spare1; }


    private static final int DESTINATIONID_FROM = 41;
    private static final int DESTINATIONID_TO = 70;

    private int destinationID;
    /** destinationID
     * @return int value of destinationID (30 bits [41,70])
     */
    public int getDestinationID() { return destinationID; }


    private static final int SPARE2_FROM = 71;
    private static final int SPARE2_TO = 72;

    private int spare2;
    /** spare2
     * @return int value of spare2 (2 bits [71,72])
     */
    public int getSpare2() { return spare2; }

	/** AISMessage 10 constructor
	 * @param content AIS content
	 * @param prov the provenance of the message
	 * @precondition validLength(content.length()) && AISMessageBase.getMessageId(content)==10
	 */
	public AISMessage10Impl(Sixbit content, VDMMessageProvenance prov)
	{
		super(content, prov);
		assert(validLength(content.length()));
		assert(getMessageID() == 10);

		spare1 = content.getIntFromTo(SPARE1_FROM,SPARE1_TO);
		if (!UtilsSpare.isSpareSemanticallyCorrect(spare1))
		{
		   	annotations.add(new AISIllegalValueAnnotation("getSpare1", spare1, UtilsSpare.range));
		}
		destinationID = content.getIntFromTo(DESTINATIONID_FROM,DESTINATIONID_TO);
		spare2 = content.getIntFromTo(SPARE2_FROM,SPARE2_TO);
		if (!UtilsSpare.isSpareSemanticallyCorrect(spare2))
		{
		    annotations.add(new AISIllegalValueAnnotation("getSpare2", spare2, UtilsSpare.range));
		}
	}
	
	/** Generates a String representing the AIS message. 
	 * Format: all fields are shown in the order as specified by the standard separated by the SEPARATOR string.
	 */
	@Override
	public String toString() {
		return super.toString() + SEPARATOR + Integer.toString(getDestinationID()); 
	}
}