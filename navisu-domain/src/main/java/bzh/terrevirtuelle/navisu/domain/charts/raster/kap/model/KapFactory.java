package bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model;

import bzh.terrevirtuelle.navisu.domain.charts.raster.kap.model.impl.KapFactoryImpl;

/**
 *
 * @author jordan
 */
public interface KapFactory {

    public static KapFactory factory = new KapFactoryImpl();
    
    public KAP newKap();
}
