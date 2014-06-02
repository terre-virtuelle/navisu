package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.impl;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.SentenceParserFactory;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.bsb.impl.BsbSentenceParserImpl;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.knp.impl.KnpSentenceParserImpl;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.ply.impl.PlySentenceParserImpl;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences.ver.impl.VerSentenceParserImpl;

/**
 *
 * @author jordan
 */
public class SentenceParserFactoryImpl implements SentenceParserFactory {

    @Override
    public SentenceParser newBsbSentenceParser() {
        return new BsbSentenceParserImpl();
    }

	@Override
	public SentenceParser newVerSentenceParser() {
		return new VerSentenceParserImpl();
	}

	@Override
	public SentenceParser newKnpSentenceParser() {
		return new KnpSentenceParserImpl();
	}

	@Override
	public SentenceParser newPlySentenceParser() {
		return new PlySentenceParserImpl();
	}   
}
