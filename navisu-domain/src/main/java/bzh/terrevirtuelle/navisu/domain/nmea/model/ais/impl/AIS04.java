package bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.impl.utils.Sixbit;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage04;
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

/**
 * AIS Message 4 implementation: Base Station Report.
 * <pre>
 * Field Nr     Field Name                          NrOf Bits   (from, to )
 * ------------------------------------------------------------------------
 *  1	messageID                        		       	   6	(   1,   6)
 *  2	repeatIndicator                         		   2	(   7,   8)
 *  3	mmsi                                  		  30	(   9,  38)
 *  4	utcYear                        		         	  14	(  39,  52)
 *  5	utcMonth                            	    	   4	(  53,  56)
 *  6	utcDay                                  		   5	(  57,  61)
 *  7	utcHour                                 		   5	(  62,  66)
 *  8	utcMinute                              		 	   6	(  67,  72)
 *  9	utcSecond                          	    	 	   6	(  73,  78)
 * 10	positionAccuracy                    	    	   1	(  79,  79)
 * 11	longitude                               		  28	(  80, 107)
 * 12	latitude                              		  	  27	( 108, 134)
 * 13	typeOfElectronicPositionFixingDevice    		   4	( 135, 138)
 * 14	transmissionControlForLongRangeBroadcastMessage	   1	( 139, 139)
 * 15	spare                                   	 	   9	( 140, 148)
 * 16	raimFlag                              		  	   1	( 149, 149)
 * 17	communicationState                      		  19	( 150, 168)
 *                                                      ---- +
 *                           (maximum) number of bits    168
 * </pre>
 */
@XmlRootElement(name = "ais04")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS04
        extends AISMessageUTCReportImpl
        implements AISMessage04 {

    /**
     * AIS0 4default constructor
     */
    public AIS04() {
    }

    /**
     * AISMessage04 constructor
     *
     * @param content AIS content
     * @param prov the provenance of the message
     * @precondition validLength(content.length()) &&
     * AISMessageBase.getMessageId(content)== 4
     */
    public AIS04(Sixbit content, VDMMessageProvenance prov) {
        super(content, prov);
        assert (getMessageID() == 4);
    }
    
}
