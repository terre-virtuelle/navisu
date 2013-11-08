package bzh.terrevirtuelle.navisu.core.model.generic;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface ReadDataServices<T> {

    T read(int id);

    boolean contains(int id);

    int size();
}
