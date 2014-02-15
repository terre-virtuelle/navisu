package bzh.terrevirtuelle.navisu.api.option;

import java.util.HashMap;
import java.util.Map;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:03
 */
public class OptionsWindowCtrl {

    protected OptionsWindow optionsWindow;

    protected Map<OptionsPanelCtrl, OptionsPanel> viewsForCtrlMap = new HashMap<>();
    protected Map<OptionsPanelCtrl, Object> modelsForCtrlMap = new HashMap<>();

    public OptionsWindowCtrl() {

        this.optionsWindow = new OptionsWindow();
    }

    public <T extends OptionsPanelCtrl> T newOptionsPanelCtrl(Class<T> clz) {

        T ctrl = null;

        try {

            ctrl = clz.newInstance();

            // Initialize the view
            Class<? extends OptionsPanel> viewType = ctrl.getViewType();
            OptionsPanel view = viewType.newInstance();

            this.viewsForCtrlMap.put(ctrl, view);
            this.optionsWindow.addTab(ctrl.getTitle(), view.getDisplayable());

            // Initialize the model
            Class<?> modelType = ctrl.getModelType();
            this.modelsForCtrlMap.put(ctrl, modelType.newInstance());

            return ctrl;

        } catch (Exception e) {
            String message = "Cannot create options controller [" + clz + "]";
            throw new RuntimeException(e);
        }
    }

    public OptionsWindow getView() {
        return this.optionsWindow;
    }
}
