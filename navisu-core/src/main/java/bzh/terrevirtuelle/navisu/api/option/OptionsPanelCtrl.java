package bzh.terrevirtuelle.navisu.api.option;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 17:22
 */
public interface OptionsPanelCtrl<V extends OptionsPanel, T> {

    void load(V view, T model);

    void store(V view, T oldModel);

    boolean valid(V view);

    String getTitle();

    Class<V> getViewType();
    Class<T> getModelType();
}
