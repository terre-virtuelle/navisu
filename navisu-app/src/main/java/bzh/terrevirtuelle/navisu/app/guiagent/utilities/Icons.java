package bzh.terrevirtuelle.navisu.app.guiagent.utilities;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ResourceBundle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 12/11/2013 19:37
 */
public class Icons {

    protected static final String BUNDLE_LOCATION = "icons.icons";

    protected ResourceBundle bundle;

    private static Icons instance = null;

    protected Icons() {
        this.bundle = ResourceBundle.getBundle(BUNDLE_LOCATION);
    }

    protected Image getIcon(String key) {

        String icon = this.bundle.getString(key);
        String iconsRootPath = Icons.class.getResource("/icons").toExternalForm();

        Image iconImage = new Image(iconsRootPath + File.separator + icon);

        return iconImage;
    }

    public static Image icon(String key) {

        if(instance == null) {
            instance = new Icons();
        }

        return instance.getIcon(key);
    }
}
