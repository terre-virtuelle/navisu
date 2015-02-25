package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.provenance.impl.VDMMessageProvenance;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ESI AIS Parser
 *
 * Copyright 2011/2012 by Pierre van de Laar (Embedded Systems Institute)
 * Copyright 2008 by Brian C. Lane <bcl@brianlane.com>
 * All Rights Reserved
 *
 * @author Pierre van de Laar
 * @author Brian C. Lane
 */
//TODO should we exploit that AIS Message 1 and 2 are identical (both SOTDMA)?
/**
 * AIS Message 2 implementation: Position report.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                               	   6	(   1,   6)
 *  2	repeatIndicator                         	   2	(   7,   8)
 *  3	mmsi                                  	  30	(   9,  38)
 *  4	navigationalStatus                      	   4	(  39,  42)
 *  5	rateOfTurn                              	   8	(  43,  50)
 *  6	speedOverGround                         	  10	(  51,  60)
 *  7	positionAccuracy                        	   1	(  61,  61)
 *  8	longitude                               	  28	(  62,  89)
 *  9	latitude                                	  27	(  90, 116)
 * 10	courseOverGround                        	  12	( 117, 128)
 * 11	trueHeading                             	   9	( 129, 137)
 * 12	timeStamp                               	   6	( 138, 143)
 * 13	specialManoeuvre                        	   2	( 144, 145)
 * 14	spare                                   	   3	( 146, 148)
 * 15	raimFlag                                	   1	( 149, 149)
 * 16	communicationState                      	  19	( 150, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 * </pre>
 */
@XmlRootElement(name = "ais02")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS02 extends AISMessagePositionReportImpl implements AISMessage02 {

    private Sotdma communicationState;

    /**
     * communicationState
     *
     * @return Sotdma value of communicationState (19 bits [150,168])
     */
    public Sotdma getCommunicationState() {
        return communicationState;
    }

    /**
     * AIS02 default constructor
     */
    public AIS02() {
    }

    /**
     * AISMessage 2 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)== 2
     */
    public AIS02(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (getMessageID() == 2);
        communicationState = new Sotdma(COMMUNICATIONSTATE_FROM, content);
    }
}
