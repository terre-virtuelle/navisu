/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.architecture.app;

import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsLexer;
import bzh.terrevirtuelle.navisu.architecture.impl.controller.parser.ComponentsParser;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.Handler;
import bzh.terrevirtuelle.navisu.architecture.impl.handler.PrintComponentHandler;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.netbeans.api.visual.vmd.VMDGraphScene;

/**
 *
 * @author serge
 * @date Nov 6, 2017
 */
public class AppComponent {

    public static void main(String[] args) throws Exception {
        VMDGraphScene scene = new VMDGraphScene();
        String content = new String(Files.readAllBytes(Paths.get("components.log")));
       // Handler handler = new NullComponentHandler();
        Handler handler = new PrintComponentHandler();
        ANTLRStringStream in = new ANTLRStringStream(content);
        ComponentsLexer lexer = new ComponentsLexer(in);
        lexer.setScene(scene);
        lexer.setHandler(handler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ComponentsParser parser = new ComponentsParser(tokens);
        parser.entry();
    }
}
