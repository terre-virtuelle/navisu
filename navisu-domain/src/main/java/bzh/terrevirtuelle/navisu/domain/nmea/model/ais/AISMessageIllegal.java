/* ESI AIS Parser
 * 
 * Copyright 2011/2012 by Pierre van de Laar & Pierre America (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 * 
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

public interface AISMessageIllegal
        extends AISMessage {

    /**
     * messageID
     *
     * @return int value of messageID (6 bits [1,6])
     */
    @Override
    public int getMessageID();

    /**
     * repeatIndicator
     *
     * @return int value of repeatIndicator (2 bits [7,8])
     */
    @Override
    public int getRepeatIndicator();

    /**
     * mmsi
     *
     * @return int value of mmsi (30 bits [9,38])
     */
    @Override
    public int getMMSI();

    /* (non-Javadoc)
     * Generates a String representing the parsed AISMessage 
     * Format:
     * all fields are shown in the order and units as specified by the standard separated by SEPARATORs
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString();

}
