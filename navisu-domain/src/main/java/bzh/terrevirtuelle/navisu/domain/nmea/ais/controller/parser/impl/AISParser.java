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
package bzh.terrevirtuelle.navisu.domain.nmea.ais.controller.parser.impl;

import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISFrame;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AISMessage;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS01;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS11;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS14;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS18;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS19;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS03;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS04;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS05;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS09;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.model.ais.AIS02;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import java.util.List;

/**
 *
 * @author Morvan
 */
public class AISParser {

    /**
     * gestion des trames de type 5
     */
    private static AIS05 message5;
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
      //  System.out.println("ligne : " + ligne);
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
                        AIS01 message = new AIS01();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    }
                    if (whatType == 2) {
                        AIS02 message = new AIS02();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 3) {
                        AIS03 message = new AIS03();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 4) {
                        AIS04 message = new AIS04();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 5) {
                        message5 = new AIS05();
                        message5.fill(ligne);
                        isFirstFrame = false;
                    } else if (whatType == 9) {
                        AIS09 message = new AIS09();
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
                    

                    String patternStr = ",";
                    String[] champs = ligne.split(patternStr);
                    String messageSuite = champs[5];
                    String incompleteMessage = message5.messageAis;
                    message5.messageAis = incompleteMessage.concat(messageSuite);
                    message5.ConcatAisBinary(ligne);
                    message5.checksumPadding(champs[6]);
                    message5.decodeFrame();
                    //System.out.println("message5 : " + message5 + "partie 2");
                    handler.doIt(message5);

                    isFirstFrame = true;
                }
            }
             //System.out.println("(AISPARSER) Message of Type " + whatType + " Detected");
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
