package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.impl;

import java.io.File;
import java.nio.file.Path;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.KapParserFactory;

public class KapParserFactoryImpl implements KapParserFactory {

	@Override
	public KapParser newBasicKapParser(File file) {
		return new BasicKapParserImpl(file);
	}

	@Override
	public KapParser newBasicKapParser(Path path) {
		return new BasicKapParserImpl(path);
	}
}
