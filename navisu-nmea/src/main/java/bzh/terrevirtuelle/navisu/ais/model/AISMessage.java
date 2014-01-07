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


public class AISMessage implements Message, Comparable<AISMessage>{

    /**
     * MMSI Number
     */
    public int MMSI;
    /**
     *
     */
    protected int padding;
    /**
     *
     */
    protected String checksum;
    /**
     *
     */
    protected int nbreTrames;
    /**
     *
     */
    protected int positionTrame;
    /**
     *
     */
    public String messageAis;
    /**
     *
     */
    protected AISFrameBinary messageAisBinary;

    /**
     *
     */
    public void decodeFrame() {
    }

    /**
     *
     * @param ligne
     */
    public void ConcatAisBinary(String ligne) {
    }

    /**
     *
     */
    @Override
    public void displayFrame() {
         System.out.println(this.toString());
    }

    /**
     *
     * @param checkPad
     */
    public void checksumPadding(String checkPad) {
        // transforme le 0*7F en checksum et padding
        checkPad = checkPad.replace("*", " ");
        // l'�toile n'est pas accept�e comme Pattern
        String patternStr = " ";
        // donc on remplace par un espace ...
        String[] champs = checkPad.split(patternStr);
        padding = Integer.parseInt(champs[0]);
        checksum = champs[1];
    }

    /**
     *
     * @param line
     */
    public void fill(String line) {

        //System.err.println(line);

        try {

            String patternStr = ",";
            String[] champs = line.split(patternStr);

            if (champs[0].equals("!AIVDM")) {
                nbreTrames = Integer.parseInt(champs[1]);
                positionTrame = Integer.parseInt(champs[2]);
                messageAis = champs[5];
                checksumPadding(champs[6]);
                String messageBinaire = "";
                for (int i = 0; i < this.messageAis.length(); i++) {
                    char c = messageAis.charAt(i);
                    int cInt = (int) c + 40;
                    if (cInt > 128) {
                        cInt = cInt + 32;
                    } else {
                        cInt = cInt + 40;
                    }
                    String charBinaire = Integer.toBinaryString(cInt);
                    charBinaire = charBinaire.substring(charBinaire.length() - 6, charBinaire.length());
                    messageBinaire = messageBinaire.concat(charBinaire);
                }
                messageBinaire = messageBinaire.substring(0, messageBinaire.length() - this.padding - 1);
                messageAisBinary = new AISFrameBinary(messageBinaire);
            }

        } catch (Exception e) {
            System.err.println("(AISMessage) TRAME INVALIDE : " + line);
            messageAis = "";
        }
    }

    /**
     * whatType : return the type of an AIS message
     *
     * @return
     */
    public int whatType() {

        // System.out.println(messageAisBinary.binaryToInt(0,6));
        if (!messageAis.equals("")) {
            return messageAisBinary.binaryToInt(0, 6);
        } else {
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public int getMMSI() {
        return MMSI;
    }

    /**
     *
     * @param MMSI
     */
    public void setMMSI(int MMSI) {
        this.MMSI = MMSI;
    }

    
   

    /**
     *
     * @return
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     *
     * @param checksum
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    /**
     *
     * @return
     */
    public String getMessageAis() {
        return messageAis;
    }

    /**
     *
     * @param messageAis
     */
    public void setMessageAis(String messageAis) {
        this.messageAis = messageAis;
    }

    /**
     *
     * @return
     */
    public AISFrameBinary getMessageAisBinary() {
        return messageAisBinary;
    }

    /**
     *
     * @param messageAisBinary
     */
    public void setMessageAisBinary(AISFrameBinary messageAisBinary) {
        this.messageAisBinary = messageAisBinary;
    }

    /**
     *
     * @return
     */
    public int getNbreTrames() {
        return nbreTrames;
    }

    /**
     *
     * @param nbreTrames
     */
    public void setNbreTrames(int nbreTrames) {
        this.nbreTrames = nbreTrames;
    }

    /**
     *
     * @return
     */
    public int getPadding() {
        return padding;
    }

    /**
     *
     * @param padding
     */
    public void setPadding(int padding) {
        this.padding = padding;
    }

    /**
     *
     * @return
     */
    public int getPositionTrame() {
        return positionTrame;
    }

    /**
     *
     * @param positionTrame
     */
    public void setPositionTrame(int positionTrame) {
        this.positionTrame = positionTrame;
    }

    public int compareTo(AISMessage o) {
        return this.MMSI - o.MMSI;
    }
}
/** end AISMessage */
