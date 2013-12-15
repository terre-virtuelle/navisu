package bzh.terrevirtuelle.navisu.api.progress;

import bzh.terrevirtuelle.navisu.api.progress.impl.JobManagerImpl;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:37
 */
public interface JobManager {

    public static final JobManager instance = new JobManagerImpl();

    void newJob(Job job);
}
