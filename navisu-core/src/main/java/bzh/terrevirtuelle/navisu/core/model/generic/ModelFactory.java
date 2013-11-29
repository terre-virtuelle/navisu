package bzh.terrevirtuelle.navisu.core.model.generic;

import bzh.terrevirtuelle.navisu.core.util.ICloneable;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface ModelFactory {

    <T extends ICloneable> Model<T> newModel(Class<T> clz);
}
