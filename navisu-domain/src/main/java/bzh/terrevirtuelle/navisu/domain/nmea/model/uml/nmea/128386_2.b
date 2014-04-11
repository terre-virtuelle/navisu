class AISFrame
!!!131714.java!!!	getBinaryFrame() : String
        return binaryFrame;
!!!131842.java!!!	setBinaryFrame(in binaryFrame : String) : void
        this.binaryFrame = binaryFrame;
!!!131970.java!!!	fill(in line : String) : void
		
		// remplit les attributs selon la ligne lue
		line = line.replace("*", "* ");
		
		// l'�toile n'est pas accept�e comme Pattern donc on remplace par un espace
		String patternStr = " ";
		String[] champs = line.split(patternStr);
		this.trameNMEA = champs[0];
		this.checksum = champs[1];
!!!132098.java!!!	isChecksumValid() : boolean
		
		int sum = 0;
		for (int i=1;i<trameNMEA.length();i++){
			int terme = (int)trameNMEA.charAt(i);
			sum = sum ^ terme;
		}
		return (checksum.equals(Integer.toHexString(sum)));		
