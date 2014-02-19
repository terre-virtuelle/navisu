package bzh.terrevirtuelle.navisu.api.option;

/**
 * NaVisu
 *
 * @author tibus
 * @param <V>
 * @param <T>
 * @date 15/02/2014 17:22
 */
public abstract class OptionsPanelCtrl<V extends OptionsPanel, T> {

    protected ModelChangedEvents<T> modelChangedListener;

    public abstract void load(V view, T model);

    public abstract void store(V view, T model);

    public abstract boolean valid(V view);

    public abstract String getTitle();

    public abstract Class<V> getViewType();
    public abstract Class<T> getModelType();

    public void setOnModelCHangedListener(ModelChangedEvents<T> modelChangedListener) {
        this.modelChangedListener = modelChangedListener;
    }

    protected ModelChangedEvents<T> getModelChangedListener() { return this.modelChangedListener; }
}
