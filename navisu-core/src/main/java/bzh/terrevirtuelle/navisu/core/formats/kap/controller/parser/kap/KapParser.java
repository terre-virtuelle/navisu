package bzh.terrevirtuelle.navisu.core.formats.kap.controller.parser.kap;

import bzh.terrevirtuelle.navisu.core.formats.kap.controller.Parser;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;

/**
 *
 * @author jordan
 */
public interface KapParser extends Parser<KAP>{
    
    public KAP getKap();
}
