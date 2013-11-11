package bzh.terrevirtuelle.navisu.app.charts.impl;

import bzh.terrevirtuelle.navisu.app.charts.ChartsManager;
import bzh.terrevirtuelle.navisu.app.charts.ChartsManagerServices;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParser;
import bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap.KapParserFactory;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/11/2013 12:51
 */
public class ChartsManagerImpl implements ChartsManager, ChartsManagerServices {

    protected final Logger LOGGER = Logger.getLogger(ChartsManagerImpl.class.getName());

    @Override
    public void open(String chartPath) {

        Path path = Paths.get(chartPath);

        if(!Files.exists(path)) {
            LOGGER.warning("Chart " + chartPath + " does not exists.");
            return;
        }

        KapParser parser = KapParserFactory.factory.newBasicKapParser(path);
        KAP kap = parser.parse();

        LOGGER.info("Na=" + kap.getNa());
        LOGGER.info("Nu=" + kap.getNu());
    }
}
