package bzh.terrevirtuelle.navisu.api.option;

import bzh.terrevirtuelle.navisu.api.option.annotation.OptionsAnnotationUtils;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.scene.Node;

import javax.xml.bind.*;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
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
    protected Map<OptionsPanelCtrl, Path> pFileForCtrlMap = new HashMap<>();

    protected Path tmpDir;

    public OptionsWindowCtrl() {

        this.optionsWindow = new OptionsWindow();

        this.optionsWindow.onOk(() -> this.handleOnOk());
        this.optionsWindow.onApply(() -> this.handleOnApply());
        this.optionsWindow.onCancel(() -> this.handleOnCancel());

        this.tmpDir = Paths.get(System.getProperty("user.dir"), "tmp");

        if(Files.notExists(this.tmpDir)) {
            try {
                Files.createDirectory(this.tmpDir);
            } catch (IOException e) {
                LOG.warning("Cannot create tmp directory " + this.tmpDir.toString());
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);

        if(visible) {
            this.loadControllers();
        }
        else {
            this.storeControllers();
        }
    }

    @Override
    public Node getDisplayable() {
        return this.optionsWindow.getDisplayable();
    }

    public <T extends OptionsPanelCtrl> T newOptionsPanelCtrl(Class<T> clz) {

        try {

            T ctrl = clz.newInstance();
            this.ctrlList.add(ctrl);

            // Initialize the view
            Class<? extends OptionsPanel> viewType = ctrl.getViewType();
            OptionsPanel view = viewType.newInstance();

            this.viewsForCtrlMap.put(ctrl, view);
            this.optionsWindow.addTab(ctrl.getTitle(), view.getDisplayable());

            // Initialize the model
            Class<?> modelType = ctrl.getModelType();
            Object model = modelType.newInstance();
            this.initModelFromDefaultValues(model);
            this.modelsForCtrlMap.put(ctrl, model);

            // Initialize persistence file
            final Path pFile = Paths.get(this.tmpDir.toString(), ctrl.getClass().getName() + ".xml");
            this.pFileForCtrlMap.put(ctrl, pFile);

            return ctrl;

        } catch (Exception e) {
            //String message = "Cannot create options controller [" + clz + "]";
            throw new RuntimeException(e);
        }
    }

    protected void handleOnCancel() {

    }

    protected void handleOnApply() {
        this.storeControllers();
        this.optionsWindow.getApplyBtn().setDisable(true);
    }

    protected void handleOnOk() {

    }

    /**
     * Initialize model from its annotations
     *
     * @param model
     */
    protected void initModelFromDefaultValues(Object model) {

        for(Field field : model.getClass().getDeclaredFields()) {
            try {
                OptionsAnnotationUtils.initField(model, field);
            } catch (Exception e) {
                LOG.warning("Cannot init field : " + field);
            }
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //  LOADING
    //
    protected void loadControllers() {
        LOG.entering(OptionsWindowCtrl.class.getName(), "loadControllers");

        for(OptionsPanelCtrl ctrl : this.ctrlList) {
            this.loadController(ctrl);
        }

        LOG.exiting(OptionsWindowCtrl.class.getName(), "loadControllers");
    }

    protected void loadController(final OptionsPanelCtrl ctrl) {

        final Path pFile = this.pFileForCtrlMap.get(ctrl);
        Object model = this.modelsForCtrlMap.get(ctrl);

        if(Files.exists(pFile)) {
            model = this.loadModelFromPersistenceFile(pFile, model);
            this.modelsForCtrlMap.put(ctrl, model); // maybe not necessary
        }

        ctrl.load(this.viewsForCtrlMap.get(ctrl), model);
    }

    protected Object loadModelFromPersistenceFile(Path pFile, Object model) {

        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            return unmarshaller.unmarshal(pFile.toFile());

        } catch (Exception e) {
            String mClass = model.getClass().getName();
            String mFile = pFile.toString();
            String msg = "Cannot load model " + mClass + " from persistence file " + mFile;
            LOG.log(Level.SEVERE, msg, e);
        }

        return model;
    }
    //
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    // STORING
    //
    protected void storeControllers() {
        LOG.entering(OptionsWindowCtrl.class.getName(), "storeControllers");

        for(OptionsPanelCtrl ctrl : this.ctrlList) {
            this.storeController(ctrl);
        }

        LOG.exiting(OptionsWindowCtrl.class.getName(), "storeControllers");
    }

    protected void storeController(OptionsPanelCtrl ctrl) {

        final Path pFile = this.pFileForCtrlMap.get(ctrl);
        final Object model = this.modelsForCtrlMap.get(ctrl);

        ctrl.store(this.viewsForCtrlMap.get(ctrl), model);

        this.storeModelToPersistenceFile(pFile, model);
    }

    protected void storeModelToPersistenceFile(Path pFile, Object model) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(model.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.marshal(model, pFile.toFile());

        } catch (Exception e) {
            String mClass = model.getClass().getName();
            String mFile = pFile.toString();
            String msg = "Cannot store model " + mClass + " to persistence file " + mFile;
            LOG.log(Level.SEVERE, msg, e);
        }
    }
    //
    //------------------------------------------------------------------------------------------------------------------
}
