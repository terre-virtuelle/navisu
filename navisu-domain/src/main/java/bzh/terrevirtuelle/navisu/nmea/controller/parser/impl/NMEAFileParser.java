/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.nmea.controller.parser.impl;

import bzh.terrevirtuelle.navisu.nmea.controller.parser.handler.Handler;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

/**
 *
 * @author Serge Morvan
 */
public class NMEAFileParser {

    private NMEAParser parser;
    private ANTLRInputStream input = null;
    private final Handler handler;

    public NMEAFileParser(Handler handler) {
        this.handler = handler;
    }

    public void parse(String fileName) {
        try {
            input = new ANTLRInputStream(new FileInputStream(fileName));
        } catch (IOException ex) {
            Logger.getLogger(NMEAFileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    protected void init() {
        NMEALexer lexer = new NMEALexer(input);
        lexer.setHandler(handler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new NMEAParser(tokens);
        // Le SwingWorker ne fonctionne que dans l'envbironnement Swing
        try {
            parser.entry();
        } catch (RecognitionException ex) {
            Logger.getLogger(NMEAFileParser.class.getName()).log(Level.SEVERE, null, ex);
        } 
        /*
        ParserSwingWorker swingWorker = new ParserSwingWorker();
        swingWorker.execute();
          */      
    }

    class ParserSwingWorker
            extends SwingWorker<Integer, String> {

        @Override
        protected Integer doInBackground() throws Exception {
            System.out.println("doInBackground");
            parser.entry();
            return 0;
        }
    }
}
