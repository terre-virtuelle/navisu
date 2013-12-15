package bzh.terrevirtuelle.navisu.api.progress.impl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobViewCtrl;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:52
 */
public class JobsManagerImpl implements JobsManager {

    protected List<JobViewCtrl> ctrls;
    protected VBox container;
    protected int jobViewWidth = DEFAULT_JOB_VIEW_WIDTH, jobViewHeight = DEFAULT_JOB_VIEW_HEIGHT;


    public JobsManagerImpl() {
        this.ctrls = new ArrayList<>();
        this.container = new VBox();
    }

    @Override
    public void newJob(final String name, final Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobViewCtrl jobViewCtrl = new JobViewCtrl(name);
            playJob(jobViewCtrl, job);
        });
    }

    @Override
    public void newJob(String name, int workunit, Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobViewCtrl jobViewCtrl = new JobViewCtrl(name, workunit);
            playJob(jobViewCtrl, job);
        });
    }

    protected void playJob(final JobViewCtrl jobViewCtrl, Job job) {

        jobViewCtrl.setViewSize(jobViewWidth, jobViewHeight);
        jobViewCtrl.setOnExit(() -> {

            stopJob(jobViewCtrl);
        });
        ctrls.add(jobViewCtrl);

        // display the job view
        Platform.runLater(() -> container.getChildren().add(jobViewCtrl.getDisplayable()));

        // play the job
        job.run(jobViewCtrl);

        // remove the job view
        Platform.runLater(() -> stopJob(jobViewCtrl));
    }

    protected void stopJob(JobViewCtrl jobViewCtrl) {
        this.container.getChildren().removeAll(jobViewCtrl.getDisplayable());
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
