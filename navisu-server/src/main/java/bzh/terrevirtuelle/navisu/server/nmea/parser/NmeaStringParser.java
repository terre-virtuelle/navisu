/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.server.nmea.parser;

import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.handler.Handler;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl.NMEALexer;
import bzh.terrevirtuelle.navisu.domain.nmea.controller.parser.impl.NMEAParser;
import bzh.terrevirtuelle.navisu.domain.nmea.model.Sentences;
import java.util.logging.Logger;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author Serge
 */
public class NmeaStringParser {

    private NMEAParser parser;
    private NMEALexer lexer;
    private ANTLRStringStream input;
    private Handler handler;
  //  private Handler aisHandler;
    private CommonTokenStream tokens;
    private Sentences sentences;
    protected static final Logger LOGGER = Logger.getLogger(NmeaStringParser.class.getName());
   
    ;
    public NmeaStringParser(Sentences sentences) {
        this.sentences = sentences;
    }

    public void parse(final String s) {
        String source = new StringBuilder(s).toString().trim();
        /*
         if ((source.startsWith("{") && source.endsWith("}")) // Gpsd well formatted
         || source.startsWith("!") // AIS
         || source.startsWith("$") // NMEA0183
         || source.startsWith("PGN")) { // N2K
         */
       // System.out.println(source);
        input = new ANTLRStringStream(source);
        handler = new NmeaHandler(sentences);
       // aisHandler = new NmeaHandler(sentences);
        lexer = new NMEALexer(input);
        lexer.setHandler(handler);
       // lexer.setAISHandler(aisHandler);
        parser = new NMEAParser(new CommonTokenStream(lexer));
        try {
            parser.entry();
        } catch (RecognitionException ex) {
            //  LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        //  }

    }
}
