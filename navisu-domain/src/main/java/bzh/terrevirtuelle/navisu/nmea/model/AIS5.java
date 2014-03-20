/**
 * ****************************************************************************
 * (c) Copyright 2007, IRENav. All rights reserved. Property of ECOLE NAVALE
 *
 * For Unrestricted Internal Use Only Unauthorized reproduction and/or
 * distribution is strictly prohibited. This product is protected under
 * copyright law and trade secret law as an unpublished Work.
 *
 * Modified in 05/2007.
 *
 * Original Designers : RAY, STROH, ALESSIO Modified : Serge Morvan Enib 09/2009
 *
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.nmea.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Ship static and voyage related data
 *
 */
@XmlRootElement(name = "ais5")
@XmlAccessorType(XmlAccessType.FIELD)
public class AIS5 extends AIS135 {

    public AIS5() {
    }

    public AIS5(int MMSI, String device, int imo, String shipname, int shipType, float width, float length, float draught, String callsign, Calendar ETA, String destination) {
        super(MMSI, device, imo, shipname, shipType, width, length, draught, callsign, ETA, destination);
    }

   

    /**
     * decodeFrame : decode AIS message of type 5
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.length() == 425) {
            MMSI = binaryToInt(messageAisBinary, 8, 38);
            imo = binaryToInt(messageAisBinary, 40, 70);
            callsign = binaryToString(messageAisBinary, 70, 112);
            shipname = binaryToString(messageAisBinary, 112, 232);
            shipType = binaryToInt(messageAisBinary, 232, 240);
            destination = binaryToString(messageAisBinary, 302, 422);
            draught = (float) (0.1 * binaryToInt(messageAisBinary, 294, 302));
            electronicPositionDevice = binaryToInt(messageAisBinary, 270, 274);
            length = binaryToInt(messageAisBinary, 240, 249) + binaryToInt(messageAisBinary, 249, 258);
            width = binaryToInt(messageAisBinary, 258, 264) + binaryToInt(messageAisBinary, 264, 270);
            month = binaryToInt(messageAisBinary, 274, 278);
            day = binaryToInt(messageAisBinary, 278, 283);
            hour = binaryToInt(messageAisBinary, 283, 288);
            minute = binaryToInt(messageAisBinary, 288, 294);
            ETA = new GregorianCalendar(year, month, day, hour, minute);
        }
    }

    /**
     * ConcatAisBinary : aggregate the two parts of a type 5 frame
     *
     * @param ligne
     */
    @Override
    public void ConcatAisBinary(String ligne) {
        String messageBinaire = "";
        for (int i = 0; i < messageAis.length(); i++) {
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
        /* remove padding bits at the end the message */
        messageBinaire = messageBinaire.substring(0, messageBinaire.length() - this.padding - 1);
        messageAisBinary = messageBinaire;
        toString();
    }

    /**
     * displayFrame : print AIS message of type 5
     *
     * @return 
     */
    @Override
    public String toString() {
      //  DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM");
        StringBuilder sb = new StringBuilder();
        String s = "AIS5{MMSI=" + MMSI
                + ", NAME=" + shipname
                + ", TYPE=" + shipType
                + ", LENGTH=" + length
                + ", WIDTH=" + width
                + ", DRAUGHT=" + draught;
        sb.append(s);
        if (ETA != null) {
            s = ", ETA=" + ETA.getTime();
        } else {
            s = ", ETA= ";
        }
        sb.append(s);
        s = ", DEST=" + destination + "}";
        sb.append(s);
        return sb.toString();
    }
}

/**
 * end AISMessageType5
 */
