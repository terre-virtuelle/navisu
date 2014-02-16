package bzh.terrevirtuelle.navisu.api.option;

import bzh.terrevirtuelle.navisu.api.option.annotation.OptionsAnnotationUtils;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.scene.Node;

import javax.xml.bind.*;
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

    protected Runnable onCloseListener;

    public OptionsWindowCtrl() {

        this.optionsWindow = new OptionsWindow();

        this.optionsWindow.onOk(() -> this.handleOnOk());
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

        if(visible) {
            this.loadControllers();
        }
        else {
            boolean isValid = this.storeControllers();
            if(!isValid) {
                LOG.severe("ERROR");
                return;
            }
        }

        super.setVisible(visible);
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
        this.onCloseListener.run();
    }

    protected void handleOnOk() {

        boolean isValid = this.storeControllers();
        if(!isValid) {
            return;
        }

        this.onCloseListener.run();
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
    protected boolean storeControllers() {
        LOG.entering(OptionsWindowCtrl.class.getName(), "storeControllers");

        for(OptionsPanelCtrl ctrl : this.ctrlList) {
            if(!this.storeController(ctrl)) {
                return false;
            }
        }

        LOG.exiting(OptionsWindowCtrl.class.getName(), "storeControllers");

        return true;
    }

    protected boolean storeController(OptionsPanelCtrl ctrl) {

        final Path pFile = this.pFileForCtrlMap.get(ctrl);
        final Object model = this.modelsForCtrlMap.get(ctrl);
        final OptionsPanel view = this.viewsForCtrlMap.get(ctrl);

        if(ctrl.valid(view)) {
            ctrl.store(view, model);
        } else {
           return false;
        }

        this.storeModelToPersistenceFile(pFile, model);

        final ModelChangedEvents modelChangedListener = ctrl.getModelChangedListener();
        if(modelChangedListener != null) {
            modelChangedListener.onModelChanged(model);
        }

        return true;
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


    public void setOnCloseListener(Runnable onCloseListener) {
        this.onCloseListener = onCloseListener;
    }
}
