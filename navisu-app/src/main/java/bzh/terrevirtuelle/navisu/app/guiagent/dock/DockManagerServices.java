package bzh.terrevirtuelle.navisu.app.guiagent.dock;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @date 22 mars 2015
 * @author Serge Morvan
 */
public interface DockManagerServices extends ComponentService {

    void init(StackPane root, Scene scene, int height, int width);

    void makeDock();
}
