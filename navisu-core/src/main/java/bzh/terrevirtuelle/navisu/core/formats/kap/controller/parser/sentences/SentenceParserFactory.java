package bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences;

import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences.impl.SentenceParserFactoryImpl;

/**
 *
 * @author jordan
 */
public interface SentenceParserFactory {

    public static SentenceParserFactory factory = new SentenceParserFactoryImpl();
    
    public SentenceParser newBsbSentenceParser();
    
    public SentenceParser newVerSentenceParser();
    
    public SentenceParser newKnpSentenceParser();
    
    public SentenceParser newPlySentenceParser();
}
