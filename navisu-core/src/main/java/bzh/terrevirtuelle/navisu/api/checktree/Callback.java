package bzh.terrevirtuelle.navisu.api.checktree;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/12/2013 23:29
 */
@FunctionalInterface
public interface Callback {

    void on();

    default void doNothing() { /* Nothing to do */ }
}
