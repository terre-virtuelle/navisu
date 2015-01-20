package bzh.terrevirtuelle.navisu.app.guiagent.layertree;

import bzh.terrevirtuelle.navisu.core.view.display.Display;
import bzh.terrevirtuelle.navisu.core.view.geoview.layer.GeoLayer;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.CheckBoxTreeItem;
import org.capcaval.c3.component.ComponentService;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/11/2013 23:17
 */
public interface LayerTreeServices extends ComponentService {

    void createGroup(String groupName, GeoLayer<?>... layers);

    CheckBoxTreeItem<GeoLayer> createNode(CheckBoxTreeItem<GeoLayer> root, String nodeName, String iconName);

    void addGeoLayer(String groupName, GeoLayer<?>... layers);

    Display<Node> getDisplayService();
    
    List<CheckBoxTreeItem<GeoLayer>> getCheckBoxTreeItems();
    
    CheckBoxTreeItem<GeoLayer> search(String name);
}
