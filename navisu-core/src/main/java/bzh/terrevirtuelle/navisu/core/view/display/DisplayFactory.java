package bzh.terrevirtuelle.navisu.core.view.display;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractDisplay;
import javafx.scene.Node;

/**
 * NaVisu
 *
 * @author tibus
 * @date 07/11/2013 21:18
 */
public interface DisplayFactory {

    public static final DisplayFactory impl = new DisplayFactoryImpl();

    Display<Node> newDisplayNode(final Node node);

    static class DisplayFactoryImpl implements DisplayFactory {

        @Override
        public Display<Node> newDisplayNode(final Node node) {

            return new JFXAbstractDisplay() {

                @Override
                public Node getDisplayable() {
                    return node;
                }
            };
        }
    }
}
