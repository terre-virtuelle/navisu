/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.ais.controller.parser.impl;

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
 * Original Designers : RAY, ETIENNE
 * Modified : Serge Morvan ENIB
 ******************************************************************************/
public abstract class Parser {

    /**
     *
     * @param message
     */
    public  abstract void parse(String message);

}
