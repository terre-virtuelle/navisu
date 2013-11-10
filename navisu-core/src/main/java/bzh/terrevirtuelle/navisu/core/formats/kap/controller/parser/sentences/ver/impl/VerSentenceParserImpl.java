package bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences.ver.impl;

import java.util.List;

import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.sentences.Token;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;

/**
 *
 * @author jordan
 */
public class VerSentenceParserImpl implements SentenceParser {
    

	@Override
	public void parse(KAP kap, List<String> sentences) {
		
		assert(sentences.size() == 1);
		
		String version = sentences.get(0);
		
		if(Token.isWellFormatted(version)) {
			kap.setVersion(version);
		}
	} 
}
