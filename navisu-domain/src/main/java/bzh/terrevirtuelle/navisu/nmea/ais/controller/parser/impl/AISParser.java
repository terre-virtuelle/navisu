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
 * Original Designers : RAY, STROH, ALESSIO, SCHERMESSER, BAUMERT
 *
 * Modified : Serge Morvan Enib
 *****************************************************************************
 */
package bzh.terrevirtuelle.navisu.nmea.ais.controller.parser.impl;

import bzh.terrevirtuelle.navisu.nmea.model.AISFrame;
import bzh.terrevirtuelle.navisu.nmea.model.AISMessage;
import bzh.terrevirtuelle.navisu.nmea.model.AIS1;
import bzh.terrevirtuelle.navisu.nmea.model.AIS11;
import bzh.terrevirtuelle.navisu.nmea.model.AIS14;
import bzh.terrevirtuelle.navisu.nmea.model.AIS18;
import bzh.terrevirtuelle.navisu.nmea.model.AIS19;
import bzh.terrevirtuelle.navisu.nmea.model.AIS3;
import bzh.terrevirtuelle.navisu.nmea.model.AIS4;
import bzh.terrevirtuelle.navisu.nmea.model.AIS5;
import bzh.terrevirtuelle.navisu.nmea.model.AIS9;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.nmea.model.AIS2;
import bzh.terrevirtuelle.navisu.nmea.model.Sentences;
import java.util.List;

/**
 *
 * @author Morvan
 */
public class AISParser {

    /**
     * gestion des trames de type 5
     */
    private static AIS5 message5;
    private static boolean isFirstFrame = true;
    private final List<String> entries = null;
    private static Sentences sentences;
    private static Handler handler;
    static int i = 0;

    public AISParser() {
    }

    public AISParser(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public static void setHandler(Handler h) {
        handler = h;
    }

    public static void setSentences(Sentences s) {
        sentences = s;
    }

    /**
     *
     * @return
     */
    public FrameFlags getFrameDescriptor() {
        return new AISFrameFlags();
    }

    /**
     * La m�thode parse interpr�te une trame AIS et la transmet au handler
     *
     * @param ligne correspond � une trame AIS
     *
     */
    public void parse(byte[] ligne) {
        parse(new String(ligne));
    }

    /**
     *
     * @param ligne
     */
    public static void parse(String ligne) {
        System.out.println("ligne : " + ligne);
        int whatType = -1;
        try {
            if (ligne.startsWith("!AIVDM")) {
                AISFrame trameNMEA = new AISFrame();
                trameNMEA.fill(ligne);
                AISMessage messageSuper = new AISMessage();
                messageSuper.fill(ligne);

                if (isFirstFrame) {
                    whatType = messageSuper.whatType();
                    if (whatType == 1) {
                        AIS1 message = new AIS1();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    }
                    if (whatType == 2) {
                        AIS2 message = new AIS2();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 3) {
                        AIS3 message = new AIS3();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 4) {
                        AIS4 message = new AIS4();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 5) {
                        message5 = new AIS5();
                        message5.fill(ligne);
                        isFirstFrame = false;
                    } else if (whatType == 9) {
                        AIS9 message = new AIS9();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 11) {
                        AIS11 message = new AIS11();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 14) {
                        AIS14 message = new AIS14();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 18) {
                        AIS18 message = new AIS18();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 19) {
                        AIS19 message = new AIS19();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 0) {
                        // System.err.println("(AISPARSER) Unsupported Message");
                    } else {
                        // System.out.println("(AISPARSER) Message of Type " + whatType + " Detected"); // 10, 20, 21, 24
                    }
                } else {
                    /**
                     * d�codage de la deux�me partie d'une trame 5, une telle
                     * trame n'a pas d'identifiant
                     */
                    System.out.println("message5 : " + message5);

                    String patternStr = ",";
                    String[] champs = ligne.split(patternStr);
                    String messageSuite = champs[5];
                    String incompleteMessage = message5.messageAis;
                    message5.messageAis = incompleteMessage.concat(messageSuite);
                    message5.ConcatAisBinary(ligne);
                    message5.checksumPadding(champs[6]);
                    message5.decodeFrame();
                    handler.doIt(message5);

                    isFirstFrame = true;
                }
            }
            //  System.out.println("(AISPARSER) Message of Type " + whatType + " Detected");
        } catch (Exception e) {
            // System.err.println("(AISPARSER) Trame " + ligne + " invalide !!! Type : " + whatType);
            //e.printStackTrace();
            isFirstFrame = true; // annulation de la lecture de la trame 5
        }
    }
}
/**
 * end AISParser
 */
