package bzh.terrevirtuelle.navisu.core.model.generic;

import bzh.terrevirtuelle.navisu.core.model.generic.impl.ModelFactoryImpl;
import bzh.terrevirtuelle.navisu.core.util.ICloneable;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface Model<T extends ICloneable> {

    public static final ModelFactory factory = new ModelFactoryImpl();

    ReadDataServices<T> getReadDataServices();

    WriteDataServices<T> getWriteDataServices();

    ModelEvents.ModelEventsSubscribe getEventsSubscribe();
}
