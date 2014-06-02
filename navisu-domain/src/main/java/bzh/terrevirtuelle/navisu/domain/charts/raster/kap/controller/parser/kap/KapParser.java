package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.parser.kap;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.controller.Parser;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;

/**
 *
 * @author jordan
 */
public interface KapParser extends Parser<KAP>{
    
    public KAP getKap();
}
