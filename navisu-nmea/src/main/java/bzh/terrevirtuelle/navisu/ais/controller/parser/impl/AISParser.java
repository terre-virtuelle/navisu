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
 * Original Designers : RAY, STROH, ALESSIO, SCHERMESSER, BAUMERT
 * 
 * Modified : Serge Morvan Enib
 ******************************************************************************/
package bzh.terrevirtuelle.navisu.ais.controller.parser.impl;


import bzh.terrevirtuelle.navisu.ais.model.AISFrame;
import bzh.terrevirtuelle.navisu.ais.model.AISMessage;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType1;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType11;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType14;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType18;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType19;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType3;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType4;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType5;
import bzh.terrevirtuelle.navisu.ais.model.AISMessageType9;
import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;
import java.util.List;

/**
 *
 * @author Morvan
 */

public class AISParser
        extends Parser {

    /** gestion des trames de type 5 */
    private AISMessageType5 message5;
    private static boolean isFirstFrame = true;
    private final List<String> entries = null;
    private Handler handler;
    static int i = 0;

    public AISParser() {
    }

    public AISParser(Handler handler) {
        this.handler = handler;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
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
    @Override
    public void parse(String ligne) {
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
                        AISMessageType1 message = new AISMessageType1();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 3) {
                        AISMessageType3 message = new AISMessageType3();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 4) {
                        AISMessageType4 message = new AISMessageType4();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 5) {
                        message5 = new AISMessageType5();
                        message5.fill(ligne);
                        isFirstFrame = false;
                    } else if (whatType == 9) {
                        AISMessageType9 message = new AISMessageType9();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 11) {
                        AISMessageType11 message = new AISMessageType11();
                        message.fill(ligne);
                        message.decodeFrame();
                       handler.doIt(message);
                    } else if (whatType == 14) {
                        AISMessageType14 message = new AISMessageType14();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 18) {
                        AISMessageType18 message = new AISMessageType18();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 19) {
                        AISMessageType19 message = new AISMessageType19();
                        message.fill(ligne);
                        message.decodeFrame();
                        handler.doIt(message);
                    } else if (whatType == 0) {
                         System.err.println("(AISPARSER) Unsupported Message");
                    } else {
                        // System.out.println("(AISPARSER) Message of Type " + whatType + " Detected"); // 10, 20, 21, 24
                    }
                } else {
                    /** d�codage de la deux�me partie d'une trame 5, une telle trame n'a pas d'identifiant */
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
/** end AISParser */
