/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsLexer;
import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsParser;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

/**
 *
 * @author serge
 * @date Nov 3, 2017
 */
public class AppParser {

    public static void main(String[] args) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("components.log")));
      //  System.out.println(content);
        ANTLRStringStream in = new ANTLRStringStream(content);
        ComponentsLexer lexer = new ComponentsLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComponentsParser parser = new ComponentsParser(tokens);
        parser.entry(); 
    }
}
