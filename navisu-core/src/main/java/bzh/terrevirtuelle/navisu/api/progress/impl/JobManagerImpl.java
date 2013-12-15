package bzh.terrevirtuelle.navisu.api.progress.impl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.JobManager;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobViewCtrl;

import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:52
 */
public class JobManagerImpl implements JobManager {

    protected List<JobViewCtrl> ctrls;

    public JobManagerImpl() {
        this.ctrls = new ArrayList<>();
    }

    @Override
    public void newJob(Job job) {

    }
}
