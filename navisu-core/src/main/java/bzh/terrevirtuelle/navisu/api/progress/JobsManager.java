package bzh.terrevirtuelle.navisu.api.progress;

import bzh.terrevirtuelle.navisu.api.progress.impl.JobsManagerImpl;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:37
 */
public interface JobsManager {

    void newJob(String name, Job job);
    void newJob(String name, int workunit, Job job);


    public static final int DEFAULT_JOB_VIEW_WIDTH = 200;
    public static final int DEFAULT_JOB_VIEW_HEIGHT = 50;
    void setJobViewSize(int width, int height);

    Display<Node> getDisplay();

    public static JobsManager create() {
        return new JobsManagerImpl();
    }
}
