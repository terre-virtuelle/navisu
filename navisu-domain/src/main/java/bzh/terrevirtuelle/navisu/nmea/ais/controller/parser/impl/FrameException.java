/******************************************************************************
 * (c) Copyright 2008, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 04/2008.
 *
 * Original Designers : ETIENNE, RAY
 *
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.nmea.ais.controller.parser.impl;


/**
 *
 * @author Morvan
 */
public class FrameException extends Exception {

	private static final long serialVersionUID = 1L;

    /**
     *
     */
    public FrameException() {
		
		super ();
	}

    /**
     *
     * @param message
     */
    public FrameException(String message) {
		
		super (message);
	}

    /**
     *
     * @param event
     */
    public FrameException(Throwable event) {
		
		super (event);
	}

    /**
     *
     * @param message
     * @param event
     */
    public FrameException(String message, Throwable event) {
		
		super (message, event);
	}
	
} /** end FrameException */
