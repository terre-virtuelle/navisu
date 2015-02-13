/******************************************************************************
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib
 * 
 ******************************************************************************/

package bzh.terrevirtuelle.navisu.domain.nmea.model.ais;

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
	
    private String binaryFrame;

    /**
     * Get the value of binaryFrame
     *
     * @return the value of binaryFrame
     */
    public String getBinaryFrame() {
        return binaryFrame;
    }

    /**
     * Set the value of binaryFrame
     *
     * @param binaryFrame new value of binaryFrame
     */
    public void setBinaryFrame(String binaryFrame) {
        this.binaryFrame = binaryFrame;
    }

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
