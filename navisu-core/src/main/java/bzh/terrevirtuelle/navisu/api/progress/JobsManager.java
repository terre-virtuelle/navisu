package bzh.terrevirtuelle.navisu.api.progress;

import bzh.terrevirtuelle.navisu.api.progress.impl.JobsManagerImpl;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:37
 */
public interface JobsManager {

    public static final JobsManager instance = new JobsManagerImpl();

    void newJob(Job job);
}
