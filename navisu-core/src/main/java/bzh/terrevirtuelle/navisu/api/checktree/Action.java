package bzh.terrevirtuelle.navisu.api.checktree;

/**
 * NaVisu
 *
 * @author tibus
 * @date 11/12/2013 23:20
 */
public interface Action {

    String getName();
    void   setName(String name);

    Callback getCallback();
    void     setCallback(Callback cb);
}
