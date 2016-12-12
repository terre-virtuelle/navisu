package bzh.terrevirtuelle.navisu.api.progress.impl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobDisplayController;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:52
 */
public class JobsManagerImpl implements JobsManager {

    protected List<JobDisplayController> ctrls;

    protected HBox container;
    protected Text text;
    protected ProgressIndicator progress;
    protected static final String TSTYLE = "-fx-fill: whitesmoke;";
    protected Stage jobsDialog;
    protected VBox jobsContainer;
    protected int jobViewWidth = DEFAULT_JOB_VIEW_WIDTH, jobViewHeight = DEFAULT_JOB_VIEW_HEIGHT;

    public JobsManagerImpl() {
        this.ctrls = new ArrayList<>();

        this.text = new Text("");
        text.setStyle(TSTYLE);
        this.text.setOnMousePressed(e -> {
            if (this.ctrls.size() > 0) {
                jobsDialog.show();
            }
        });
        this.text.setOnMouseReleased(e -> jobsDialog.hide());
        this.progress = new ProgressIndicator(-1d);
        this.progress.setPrefSize(16, 16);
        this.progress.setTranslateX(-5.0);

        this.container = new HBox(this.text, this.progress);
        this.container.setSpacing(5);
        this.container.setAlignment(Pos.CENTER);
        this.updateContainer();

        this.jobsDialog = new Stage();
        this.jobsDialog.initModality(Modality.WINDOW_MODAL);
        this.jobsDialog.initStyle(StageStyle.UNDECORATED);
        this.jobsContainer = new VBox();
        this.jobsDialog.setScene(new Scene(this.jobsContainer, Color.TRANSPARENT));
    }

    @Override
    public void newJob(final String name, final Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobDisplayController jobViewCtrl = new JobDisplayController(name);
            playJob(jobViewCtrl, job);
        });
    }

    @Override
    public void newJob(final String name, final Job... jobs) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (Job j : jobs) {
            es.execute(() -> {

                JobDisplayController jobViewCtrl = new JobDisplayController(name);
                playJob(jobViewCtrl, j);
            });
        }
    }

    @Override
    public void newJob(String name, int workunit, Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobDisplayController jobViewCtrl = new JobDisplayController(name, workunit);
            playJob(jobViewCtrl, job);
        });
    }

    protected void playJob(final JobDisplayController jobViewCtrl, Job job) {

        jobViewCtrl.setViewSize(jobViewWidth, jobViewHeight);
        jobViewCtrl.setOnExit(() -> {

            stopJob(jobViewCtrl);
        });

        ctrls.add(jobViewCtrl);
        updateContainer();

        // display the job view
        Platform.runLater(() -> jobsContainer.getChildren().add(jobViewCtrl.getView().getDisplayable()));

        // play the job
        job.run(jobViewCtrl);

        // remove the job view
        Platform.runLater(() -> stopJob(jobViewCtrl));
    }

    protected void stopJob(JobDisplayController jobViewCtrl) {
        this.jobsContainer.getChildren().removeAll(jobViewCtrl.getView().getDisplayable());
        this.ctrls.remove(jobViewCtrl);
        updateContainer();
    }

    protected void updateContainer() {

        final int nbJobs = this.ctrls.size();

        Platform.runLater(() -> {
            //this.text.setText(nbJobs + " job" + (nbJobs > 1 ? "s" : "") + " processes...");
            this.text.setText((nbJobs > 0 ? "Loading... " : ""));
            this.progress.setVisible(nbJobs > 0);
        });
    }

    @Override
    public void setJobViewSize(int width, int height) {
        this.jobViewWidth = width;
        this.jobViewHeight = height;
    }

    @Override
    public Display<Node> getDisplay() {
        return Display.factory.newDisplayNode(this.container);
    }
}
