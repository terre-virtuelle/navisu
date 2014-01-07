/******************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved.
 * Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only
 * Unauthorized reproduction and/or distribution is strictly prohibited.
 * This product is protected under copyright law and trade secret law as an
 * unpublished Work.
 *
 * Modified in 03/2007.
 *
 * Original Designers : RAY
 *
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.ais.controller.parser.impl;

/**
 * La classe abstraite d�fini le format d'une trame avec un marquer de d�but, un marqueur de fin et la possibilit� de d�finir des exceptions de traitement
 *
 */ 

public abstract class FrameFlags {
	
    /**
     *
     * @return
     */
    public abstract byte startFlag();
	
    /**
     *
     * @return
     */
    public abstract byte endFlag();

    /**
     *
     * @param Frame
     * @return
     */
    public abstract boolean isException(String Frame);
	
    /**
     *
     * @param Frame
     * @return
     */
    public abstract String FormattedOutput(String Frame);
	
} /** class FrameFlags */

