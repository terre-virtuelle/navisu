/******************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 05/2007.
 *
 * Original Designers : RAY
 *
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.domain.nmea.controller.ais.parser.impl;

import java.util.Date;



/**
 * Define AIS Frame Charateristics
 *
 */ 

public class AISFrameFlags extends FrameFlags {
	
	private static final byte AIS_FRAME_START_FLAG = '!'; 
	private static final byte AIS_FRAME_END_FLAG   = 0x0A; //0x0A;
	
    /**
     *
     * @return
     */
    public byte startFlag() {
		
		return AIS_FRAME_START_FLAG;
	}
	
    /**
     *
     * @return
     */
    public byte endFlag() {
		
		return AIS_FRAME_END_FLAG;
	}

    /**
     *
     * @param Frame
     * @return
     */
    public boolean isException(String Frame) {
		
		return Frame.startsWith("!AIVDO");
	}

    /**
     *
     * @param Frame
     * @return
     */
    public String FormattedOutput(String Frame) {
		
		// il faut retirer le retour chariot final de la Frame
		if(Frame.charAt(Frame.length())==AIS_FRAME_END_FLAG){
			Frame = Frame.substring(0, Frame.length()-1);
		}
		
		return Frame+" :@: "+new Date().toString();
	}
	 
} /** class AISFrameFlags */
