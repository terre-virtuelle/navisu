package bzh.terrevirtuelle.navisu.core.formats.kap.model.impl;

import bzh.terrevirtuelle.navisu.core.formats.kap.model.KAP;
import bzh.terrevirtuelle.navisu.core.formats.kap.model.KapFactory;

/**
 *
 * @author jordan
 */
public class KapFactoryImpl implements KapFactory {

    @Override
    public KAP newKap() {
        return new KAPImpl();
    }
}
