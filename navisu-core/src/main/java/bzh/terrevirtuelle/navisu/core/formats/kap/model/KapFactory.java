package bzh.terrevirtuelle.navisu.core.formats.kap.model;

import bzh.terrevirtuelle.navisu.core.formats.kap.model.impl.KapFactoryImpl;

/**
 *
 * @author jordan
 */
public interface KapFactory {

    public static KapFactory factory = new KapFactoryImpl();
    
    public KAP newKap();
}
