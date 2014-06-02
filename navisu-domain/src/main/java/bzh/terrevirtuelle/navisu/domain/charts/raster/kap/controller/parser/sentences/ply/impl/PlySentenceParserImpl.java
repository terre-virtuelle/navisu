package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.ply.impl;

import java.util.ArrayList;
import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.Token;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

public class PlySentenceParserImpl implements SentenceParser {

	@Override
	public void parse(KAP kap, List<String> sentences) {
	
		List<double[]> plys = new ArrayList<>();
		
		for(String sentence : sentences) {
			String[] splitSentence = sentence.split(",");
			if(splitSentence.length == 3 && Token.isWellFormatted(splitSentence[1]) && Token.isWellFormatted(splitSentence[2])) {
				plys.add(new double[] {Double.parseDouble(splitSentence[1]), Double.parseDouble(splitSentence[2])});
			}
		}
		
		kap.setPLY(plys);
	}
}
