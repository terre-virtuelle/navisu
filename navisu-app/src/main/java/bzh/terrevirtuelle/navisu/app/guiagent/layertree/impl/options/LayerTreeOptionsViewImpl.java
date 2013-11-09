package bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.options;

import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsView;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/**
 * NaVisu
 *
 * @author tibus
 * @date 09/11/2013 01:28
 */
public class LayerTreeOptionsViewImpl extends OptionsView {

    protected Pane container;

    protected Slider opacitySlider;

    public LayerTreeOptionsViewImpl() {

        this.container = new Pane();
        this.opacitySlider = new Slider();
        this.container.getChildren().add(this.opacitySlider);
    }

    @Override
    public Node getDisplayable() {
        return this.container;
    }
}
