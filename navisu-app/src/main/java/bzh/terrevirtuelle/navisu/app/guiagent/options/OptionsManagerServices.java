package bzh.terrevirtuelle.navisu.app.guiagent.options;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.scene.Node;
import org.capcaval.c3.component.ComponentService;

import java.util.List;

/**
 * User: Jordan
 * Date: 08/11/2013
 */
public interface OptionsManagerServices extends ComponentService {

    void show();

    void hide();

    List<OptionsController> getOptionsControllers();
}
