package bzh.terrevirtuelle.navisu.api.option;

/**
 * NaVisu
 *
 * @author tibus
 * @date 16/02/2014 18:38
 */
public interface ModelChangedEvents<T> {

    void onModelChanged(T model);
}
