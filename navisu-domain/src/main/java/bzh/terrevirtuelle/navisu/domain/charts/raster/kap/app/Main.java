package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.app;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap.KapParserFactory;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;


/**
 *
 * @author jordan
 */


public class Main {

    public static void main(String[] args) throws URISyntaxException {

        URL url = Main.class.getResource("101.KAP");
        if(url == null) return;

    	File kapFile = new File(url.toURI());
    	
    	KapParser parser = KapParserFactory.factory.newBasicKapParser(kapFile);
    	KAP kap = parser.parse();
    	
    	System.out.println(kap.getNu());    	
    	System.out.println(kap.getNa());
    }
}
