/**
 * Original Designers : RAY, STROH, ALESSIO 
 * Modified : Serge Morvan Enib
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.domain.nmea.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
@XmlAccessorType(XmlAccessType.FIELD)
public class AISMessage
        extends NMEA
        implements Message, Comparable<AISMessage> {

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
 @XmlTransient
    protected String messageAisBinary;

    public AISMessage() {
    }

    public AISMessage(int MMSI, int padding, String checksum, int nbreTrames, int positionTrame, String messageAis, String messageAisBinary) {
        this.MMSI = MMSI;
        this.padding = padding;
        this.checksum = checksum;
        this.nbreTrames = nbreTrames;
        this.positionTrame = positionTrame;
        this.messageAis = messageAis;
        this.messageAisBinary = messageAisBinary;
    }

    public AISMessage(int MMSI, String device) {
        super(device);
        this.MMSI = MMSI;
    }

    @Override
    public String getSentence() {
        return getMessageAis();
    }

    

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
                messageAisBinary = messageBinaire;
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
            return binaryToInt(messageAisBinary, 0, 6);
        } else {
            return -1;
        }
    }
/**
     * binaryToInt : convert a binary string into int
     *
     * @param str
     * @param begin The starting bit in binaryFrame
     * @param end The ending bit in binaryFrame
     * @return int The int value of of a subset of the string binaryFrame
     */
    protected int binaryToInt(String str, int begin, int end) {

        return PosBinStringToInt( str.substring(begin, end));
    }

    /**
     * complementToInt : convert a string in two's complement binary format into
     * int
     *
     * @param str
     * @param begin The starting bit in binaryFrame
     * @param end The ending bit in binaryFrame
     * @return int The int value of of a subset of the string binaryFrame
     */
    protected int complementToInt(String str,int begin, int end) {

        String word = str.substring(begin, end);
        if (word.charAt(0) == '1') {
            return (-NegBinStringToInt(word));
        } else {
            return PosBinStringToInt(word);
        }
    }

    /**
     * PosBinStringToInt : convert a string in two's complement binary format
     * into int
     *
     * @param word The string that represent a positive value in two's
     * complement binary format
     * @return int The int value of the string
     */
    private int PosBinStringToInt(String word) {

        int result = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            int puiss = 1;
            for (int j = 1; j < word.length() - i; j++) {
                puiss *= 2;
            }
            if ((int) word.charAt(i) == 49) {
                result += puiss;
            }
        }
        return result;
    }

    /**
     * NegBinStringToInt : convert a string in two's complement binary format
     * into int
     *
     * @param word The string that represent a negative value in two's
     * complement binary format
     * @return int The int value of the string
     */
    private int NegBinStringToInt(String word) {

        int result;

        result = 0;
        for (int i = word.length() - 1; i >= 1; i--) {
            int puiss = 1;
            for (int j = 1; j < word.length() - i; j++) {
                puiss *= 2;
            }
            if ((int) word.charAt(i) == 48) {
                result += puiss;
            }
        }
        result++;
        return result;
    }

    /**
     * binaryToString : convert a binary string in real 'ASCII' string
     *
     * @param str
     * @param begin The starting bit in binaryFrame
     * @param end The ending bit in binaryFrame
     * @return String The string converted
     */
    protected String binaryToString(String str, int begin, int end) {

        int spacecount;
        int ASCII;
        String line;
        String result;

        spacecount = 0;
        line = str.substring(begin, end);
        result = new String();

        for (int i = 0; i < line.length(); i = i + 6) {
            String word = line.substring(i, i + 6);
            ASCII = PosBinStringToInt(word);
            if (ASCII < 31) {
                ASCII = ASCII + 64;
            }
            if (ASCII == 64) {
                ASCII = 32;
                spacecount++;
            } else {
                spacecount = 0;
            }
            if (spacecount <= 1) {
                result = result.concat("" + ((char) ASCII));
            }
        }
        return result;
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
    @Override
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
    public String getMessageAisBinary() {
        return messageAisBinary;
    }

    /**
     *
     * @param messageAisBinary
     */
    public void setMessageAisBinary(String messageAisBinary) {
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

    @Override
    public int compareTo(AISMessage o) {
        return this.MMSI - o.MMSI;
    }
    
}
/**
 * end AISMessage
 */
