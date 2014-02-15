package bzh.terrevirtuelle.navisu.api.option.mapping;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 17:40
 */
public interface StringMapper<T> {

    String map(T value);
    T    unmap(String value);
}
