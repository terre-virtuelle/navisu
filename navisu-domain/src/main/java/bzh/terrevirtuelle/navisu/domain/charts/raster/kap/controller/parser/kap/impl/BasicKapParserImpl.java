package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.AbstractKAPParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParserFactory;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.Token;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KapFactory;

/**
 *
 * @author jordan
 */
public class BasicKapParserImpl extends AbstractKAPParser implements KapParser {

	protected KAP kap;
	
	protected SentenceParser bsbParser;
	protected SentenceParser verParser;
	protected SentenceParser knpParser;
	protected SentenceParser plyParser;
	
	public BasicKapParserImpl() {
		super();
		this.init();
	}
	
    public BasicKapParserImpl(File file) {
		super(file);
		this.kap = KapFactory.factory.newKap();
		this.init();
	}
    
    public BasicKapParserImpl(Path filepath) {
    	super(filepath);
    	this.kap = KapFactory.factory.newKap();
    	this.init();
	}
    
    private void init() {
    	this.verParser = SentenceParserFactory.factory.newVerSentenceParser();
    	this.bsbParser = SentenceParserFactory.factory.newBsbSentenceParser();
    	this.knpParser = SentenceParserFactory.factory.newKnpSentenceParser();
    	this.plyParser = SentenceParserFactory.factory.newPlySentenceParser();
    }

    @Override
    public KAP parse() { 
    	Iterator<String> keys = this.getKeysFromSentencesIterator();
    	while(keys.hasNext()) {
    		String key = keys.next();
    		List<String> sentences = this.sentences.get(key);
    		
    		if(key.equalsIgnoreCase(Token.VER)) {
    			this.verParser.parse(this.kap, sentences);
    		}

    		if(key.equalsIgnoreCase(Token.BSB)) {
    			this.bsbParser.parse(this.kap, sentences);
    		}
    		
    		if(key.equalsIgnoreCase(Token.KNP)) {
    			this.knpParser.parse(this.kap, sentences);
    		}
    		
    		if(key.equalsIgnoreCase(Token.PLY)) {
    			this.plyParser.parse(this.kap, sentences);
    		}
    	}
    	
        return this.kap;
    }

    @Override
    public KAP getKap() {
        return this.kap;
    }
}
