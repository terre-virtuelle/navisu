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
 * Original Designers : RAY, STROH, ALESSIO
 * Modified : Serge Morvan Enib 09/2009
 *
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.ais.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Ship static and voyage related data
 * 
 */
public class AISMessageType5 extends AISMessageType135 {

    /**
     * decodeFrame : decode AIS message of type 5
     *
     */
    @Override
    public void decodeFrame() {
        if (messageAisBinary.BinaryFrame.length() == 425) {
            MMSI = messageAisBinary.binaryToInt(8, 38);
            IMO = messageAisBinary.binaryToInt(40, 70);
            CallSign = messageAisBinary.binaryToString(70, 112);
            name = messageAisBinary.binaryToString(112, 232);
            shipType = messageAisBinary.binaryToInt(232, 240);
            destination = messageAisBinary.binaryToString(302, 422);
            draught = (float) (0.1 * messageAisBinary.binaryToInt(294, 302));
            electronicPositionDevice = messageAisBinary.binaryToInt(270, 274);
            length = (messageAisBinary.binaryToInt(240, 249) + messageAisBinary.binaryToInt(249, 258));
            width = (messageAisBinary.binaryToInt(258, 264) + messageAisBinary.binaryToInt(264, 270));
            month = messageAisBinary.binaryToInt(274, 278);
            day = messageAisBinary.binaryToInt(278, 283);
            hour = messageAisBinary.binaryToInt(283, 288);
            minute = messageAisBinary.binaryToInt(288, 294);
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
        messageAisBinary = new AISFrameBinary(messageBinaire);
    }

    /**
     * displayFrame : print AIS message of type 5
     *
     */
    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("hh:mm dd-MM");
        StringBuilder sb = new StringBuilder();
        String s = "(MESSAGEAISTYPE5) MMSI=" + MMSI
                + ", NAME=" + name
                + ", TYPE=" + shipType
                + ", LENGTH=" + length
                + ", WIDTH=" + width
                + ", DRAUGHT=" + draught;
        sb.append(s);
        if (ETA != null) {
            s = ", ETA=" + dateFormat.format(ETA.getTime());
        } else {
            s = ", ETA= ";
        }
        sb.append(s);
        s = ", DEST=" + destination;
        sb.append(s);
        return sb.toString();
    }
}

/** end AISMessageType5 */
