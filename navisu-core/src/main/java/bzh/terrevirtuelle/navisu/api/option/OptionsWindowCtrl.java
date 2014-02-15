package bzh.terrevirtuelle.navisu.api.option;

import bzh.terrevirtuelle.navisu.api.option.annotation.OptionsAnnotationUtils;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.scene.Node;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:03
 */
public class OptionsWindowCtrl extends JFXAbstractDisplay {

    private static final Logger LOG = Logger.getLogger(OptionsWindowCtrl.class.getSimpleName());

    protected OptionsWindow optionsWindow;

    protected List<OptionsPanelCtrl> ctrlList = new ArrayList<>();
    protected Map<OptionsPanelCtrl, OptionsPanel> viewsForCtrlMap = new HashMap<>();
    protected Map<OptionsPanelCtrl, Object> modelsForCtrlMap = new HashMap<>();

    public OptionsWindowCtrl() {

        this.optionsWindow = new OptionsWindow();
    }

    public <T extends OptionsPanelCtrl> T newOptionsPanelCtrl(Class<T> clz) {

        T ctrl = null;

        try {

            ctrl = clz.newInstance();
            this.ctrlList.add(ctrl);

            // Initialize the view
            Class<? extends OptionsPanel> viewType = ctrl.getViewType();
            OptionsPanel view = viewType.newInstance();

            this.viewsForCtrlMap.put(ctrl, view);
            this.optionsWindow.addTab(ctrl.getTitle(), view.getDisplayable());

            // Initialize the model
            Class<?> modelType = ctrl.getModelType();
            Object model = modelType.newInstance();
            this.initModel(model);
            this.modelsForCtrlMap.put(ctrl, model);

            return ctrl;

        } catch (Exception e) {
            String message = "Cannot create options controller [" + clz + "]";
            throw new RuntimeException(e);
        }
    }

    protected void initModel(Object model) {

        for(Field field : model.getClass().getDeclaredFields()) {
            try {
                OptionsAnnotationUtils.initField(model, field);
            } catch (Exception e) {
                LOG.warning("Cannot init field : " + field);
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if(visible) {

            //TODO load models from persistence file

            for(OptionsPanelCtrl ctrl : this.ctrlList) {
                ctrl.load(this.viewsForCtrlMap.get(ctrl), this.modelsForCtrlMap.get(ctrl));
            }
        }
        else {
            for(OptionsPanelCtrl ctrl : this.ctrlList) {
                ctrl.store(this.viewsForCtrlMap.get(ctrl), this.modelsForCtrlMap.get(ctrl));
            }

            //TODO store models in persistence file
        }
    }

    @Override
    public Node getDisplayable() {
        return this.optionsWindow.getDisplayable();
    }
}
