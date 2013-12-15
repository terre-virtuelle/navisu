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

    public static final JobsManager instance = new JobsManagerImpl();

    void newJob(String name, Job job);
    void newJob(String name, int workunit, Job job);

    Display<Node> getDisplay();
}
