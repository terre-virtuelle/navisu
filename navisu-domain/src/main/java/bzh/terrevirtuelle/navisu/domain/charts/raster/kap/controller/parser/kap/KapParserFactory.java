package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap;

import java.io.File;
import java.nio.file.Path;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.impl.KapParserFactoryImpl;

public interface KapParserFactory {

	public static KapParserFactory factory = new KapParserFactoryImpl();
	
	public KapParser newBasicKapParser(File file);
	
	public KapParser newBasicKapParser(Path path);
}
