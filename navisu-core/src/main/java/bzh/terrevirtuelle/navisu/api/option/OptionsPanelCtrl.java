package bzh.terrevirtuelle.navisu.api.option;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 17:22
 */
public interface OptionsPanelCtrl<T> {

    void load(T model);

    T store(T oldModel);

    boolean valid(T model);

    OptionsPanel getView();
}
