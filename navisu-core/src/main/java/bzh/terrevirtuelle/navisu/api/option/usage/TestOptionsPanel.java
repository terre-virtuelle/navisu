package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsPanel;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:12
 */
public class TestOptionsPanel extends OptionsPanel {

    private BorderPane container = new BorderPane();


    @Override
    public Node getDisplayable() {
        return this.container;
    }
}
