package bzh.terrevirtuelle.navisu.core.model.generic.impl;

import bzh.terrevirtuelle.navisu.core.model.generic.Model;
import bzh.terrevirtuelle.navisu.core.model.generic.ModelFactory;
import bzh.terrevirtuelle.navisu.core.util.ICloneable;

/**
 * Created with IntelliJ IDEA.
 * User: tibus
 * Date: 17/10/13
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class ModelFactoryImpl implements ModelFactory {

    @Override
    public <T extends ICloneable> Model<T> newModel(Class<T> clz) {
        return new ModelImpl<>();
    }
}
