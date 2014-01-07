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
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib
 * 
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.ais.model;

/**
 *
 * @author Morvan
 */
public class AISFrame {

    /**
     *
     */
    protected String trameNMEA;
    /**
     *
     */
    protected String checksum;
	
    /**
     *
     * @param line
     */
    public void fill(String line) {
		
		// remplit les attributs selon la ligne lue
		line = line.replace("*", "* ");
		
		// l'�toile n'est pas accept�e comme Pattern donc on remplace par un espace
		String patternStr = " ";
		String[] champs = line.split(patternStr);
		this.trameNMEA = champs[0];
		this.checksum = champs[1];
	}
	
    /**
     *
     * @return
     */
    public boolean isChecksumValid() {
		
		int sum = 0;
		for (int i=1;i<trameNMEA.length();i++){
			int terme = (int)trameNMEA.charAt(i);
			sum = sum ^ terme;
		}
		return (checksum.equals(Integer.toHexString(sum)));		
	}

} /** end AISFrame */
