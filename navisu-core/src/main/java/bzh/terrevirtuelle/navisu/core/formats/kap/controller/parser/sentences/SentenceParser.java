package bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences;

import java.util.List;

import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;

/**
 *
 * @author jordan
 */
public interface SentenceParser {
    
    public void parse(KAP kap, List<String> sentences);
}
