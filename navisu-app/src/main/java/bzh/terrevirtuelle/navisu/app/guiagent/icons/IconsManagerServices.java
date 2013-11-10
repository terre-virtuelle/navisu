package bzh.terrevirtuelle.navisu.app.guiagent.icons;

import javafx.scene.image.Image;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/11/2013 18:42
 */
public interface IconsManagerServices extends ComponentService {

    Image getIcon(String key);
}
