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


    public JobsManagerImpl() {
        this.ctrls = new ArrayList<>();
        this.container = new VBox();
    }

    @Override
    public void newJob(final String name, final Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobViewCtrl jobViewCtrl = new JobViewCtrl(name);
            ctrls.add(jobViewCtrl);

            Platform.runLater(() -> container.getChildren().add(jobViewCtrl.getDisplayable()));
            job.run(jobViewCtrl);
        });
    }

    @Override
    public void newJob(String name, int workunit, Job job) {
        Executors.newSingleThreadExecutor().execute(() -> {

            JobViewCtrl jobViewCtrl = new JobViewCtrl(name, workunit);
            ctrls.add(jobViewCtrl);

            Platform.runLater(() -> container.getChildren().add(jobViewCtrl.getDisplayable()));
            job.run(jobViewCtrl);
        });
    }

    @Override
    public Display<Node> getDisplay() {
        return Display.factory.newDisplayNode(this.container);
    }
}
