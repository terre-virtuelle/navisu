package bzh.terrevirtuelle.navisu.api.progress.impl.view;

import bzh.terrevirtuelle.navisu.api.progress.impl.view.impl.JobDisplayImpl;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.impl.JobFXMLDisplayImpl;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 20:11
 */
public interface JobDisplay extends Display<Region> {

    ProgressIndicator progressBar();

    Text titleText();
    Text messageText();

    ImageView closeButton();

    public static final Class<JobFXMLDisplayImpl> DEFAULT_IMPL = JobFXMLDisplayImpl.class;

    public static JobDisplay create() {
        return create(DEFAULT_IMPL);
    }

    public static <T extends JobDisplay> JobDisplay create(Class<T> clz) {

        JobDisplay newInstance = null;

        try {
            newInstance = (JobDisplay) clz.getConstructors()[0].newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newInstance;
    }
}
