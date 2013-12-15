package bzh.terrevirtuelle.navisu.api.progress.impl.view;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.scene.Node;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 15:03
 */
public class JobViewCtrl extends JFXAbstractDisplay implements ProgressHandle {

    protected final JobView view;

    public JobViewCtrl(final String displayName) {
        this.view = new JobView(displayName);
    }

    @Override
    public Node getDisplayable() {
        return this.view;
    }

    //-----------------------------------------------------------------------------------//
    // ProgressHandle implementation
    //
    @Override
    public void finish() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void progress(int workunit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void progress(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void progress(String message, int workunit) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDisplayName(String newDisplayName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start(int workunits) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start(int workunits, long estimate) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void suspend(String message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void switchToDeterminate(int workunits) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void switchToIndeterminate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    //
    //-----------------------------------------------------------------------------------//
}
