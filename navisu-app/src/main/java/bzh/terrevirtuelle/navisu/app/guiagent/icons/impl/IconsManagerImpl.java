package bzh.terrevirtuelle.navisu.app.guiagent.icons.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.icons.IconsManager;
import bzh.terrevirtuelle.navisu.app.guiagent.icons.IconsManagerServices;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ResourceBundle;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/11/2013 18:43
 */
public class IconsManagerImpl implements IconsManager, IconsManagerServices {

    protected static final ResourceBundle BUNDLE = ResourceBundle.getBundle("icons.icons");

    @Override
    public Image getIcon(String key) {

        String icon = BUNDLE.getString(key);
        String iconsRootPath = IconsManagerImpl.class.getResource("/icons").toExternalForm();

        Image iconImage = new Image(iconsRootPath + File.separator + icon);

        return iconImage;
    }
}
