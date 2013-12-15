package bzh.terrevirtuelle.navisu.api.progress.impl;

import bzh.terrevirtuelle.navisu.api.progress.Job;
import bzh.terrevirtuelle.navisu.api.progress.JobsManager;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobViewCtrl;

import java.util.ArrayList;
import java.util.List;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:52
 */
public class JobsManagerImpl implements JobsManager {

    protected List<JobViewCtrl> ctrls;

    public JobsManagerImpl() {
        this.ctrls = new ArrayList<>();
    }

    @Override
    public void newJob(Job job) {

    }
}
