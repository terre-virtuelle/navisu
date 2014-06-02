package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.sentences;

import java.util.List;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

/**
 *
 * @author jordan
 */
public interface SentenceParser {
    
    public void parse(KAP kap, List<String> sentences);
}
