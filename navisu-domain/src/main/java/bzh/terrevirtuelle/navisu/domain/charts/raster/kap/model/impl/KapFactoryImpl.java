package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.impl;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KAP;
import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.KapFactory;

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
