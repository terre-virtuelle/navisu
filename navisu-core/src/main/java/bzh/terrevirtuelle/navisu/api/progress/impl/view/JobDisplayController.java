package bzh.terrevirtuelle.navisu.api.progress.impl.view;

import bzh.terrevirtuelle.navisu.api.common.DisplayController;
import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import javafx.application.Platform;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 15:03
 */
public class JobDisplayController implements ProgressHandle, DisplayController<JobDisplay> {

    protected final JobDisplay view;
    protected int finalWorkUnit, currentWorkUnit;
    protected String title, description;
    protected boolean isIndeterminate;

    protected Runnable exitHandle;

    public JobDisplayController(final String displayName) {
        this.view = JobDisplay.create(JobDisplay.DEFAULT_IMPL);
        this.initialize(displayName);
    }

    public JobDisplayController(final String displayName, int workunit) {
        this.view = JobDisplay.create(JobDisplay.DEFAULT_IMPL);
        this.initialize(displayName);

        this.isIndeterminate = false;
        this.finalWorkUnit = workunit;
        this.currentWorkUnit = 0;
    }

    protected void initialize(final String displayName) {
        this.title = displayName;
        this.isIndeterminate = true;
        this.updateView();

        this.view.closeButton().setOnMouseClicked((e) -> {
            if(this.exitHandle != null) {
                this.exitHandle.run();
            }
        });
    }

    @Override
    public JobDisplay getView() {
        return this.view;
    }

    public void setViewSize(int width, int height) {

        this.view.getDisplayable().setPrefSize(width, height);

        this.view.progressBar().setMinWidth(width/2);
        this.view.progressBar().setMaxWidth(width/2);
    }

    public void setOnExit(Runnable runnable) {
        this.exitHandle = runnable;
    }

    //-----------------------------------------------------------------------------------//
    // ProgressHandle implementation
    //
    @Override
    public void start() {
        this.isIndeterminate = true;
        this.updateView();
    }

    @Override
    public void start(int workunits) {
        this.isIndeterminate = false;
        this.finalWorkUnit = workunits;
        this.currentWorkUnit = 0;
        this.updateView();
    }

    @Override
    public void finish() {
        //TODO send an event to kill the job
    }

    @Override
    public void progress(int workunit) {
        this.currentWorkUnit = workunit;
        this.updateView();
    }

    @Override
    public void progress(String message) {
        this.description = message;
        this.updateView();
    }

    @Override
    public void progress(String message, int workunit) {
        this.currentWorkUnit = workunit;
        this.description = message;
        this.updateView();
    }

    @Override
    public void setDisplayName(String newDisplayName) {
        this.title = newDisplayName;
        this.updateView();
    }

    @Override
    public void suspend(String message) {
        this.isIndeterminate = true; //TODO see if it must be indeterminate
        this.description = message;
        this.updateView();
    }

    @Override
    public void switchToDeterminate(int workunits) {
        this.isIndeterminate = false;
        this.finalWorkUnit = workunits;
        this.updateView();
    }

    @Override
    public void switchToIndeterminate() {
        this.isIndeterminate = true;
        this.updateView();
    }
    //
    //-----------------------------------------------------------------------------------//

    protected void updateView() {

        if(Platform.isFxApplicationThread()) {
            this.updateViewSafe();
        }
        else {
            Platform.runLater(() -> this.updateViewSafe());
        }
    }

    protected void updateViewSafe() {


        this.view.titleText().setText(this.title);
        this.view.messageText().setText(this.description);

        if(this.isIndeterminate) {
            this.view.progressBar().setProgress(-1d);
        }
        else {

            double progress = (double)this.currentWorkUnit / this.finalWorkUnit;
            this.view.progressBar().setProgress(progress);
        }
    }
}
