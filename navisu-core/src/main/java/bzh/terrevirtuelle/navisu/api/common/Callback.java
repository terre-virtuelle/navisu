package bzh.terrevirtuelle.navisu.api.common;

/**
 * NaVisu
 *
 * @author tibus
 * @param <T> Type of the source to be returned in the callback
 * @date 11/12/2013 23:29
 */
@FunctionalInterface
public interface Callback<T> {

    void on(T source);

    default void doNothing(T source) { /* Nothing to do */ }
}
