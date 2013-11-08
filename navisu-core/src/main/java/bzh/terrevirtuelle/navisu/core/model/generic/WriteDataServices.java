package bzh.terrevirtuelle.navisu.core.model.generic;

/**
 *
 * @author Thibault Pensec <thibault.pensec at gmail.com>
 * @author Jordan Mens <jordan.mens at gmail.com>
 */
public interface WriteDataServices<T> extends ReadDataServices<T> {

    void create(int id, T data);

    void update(int id, T data);

    T delete(int id);
}
