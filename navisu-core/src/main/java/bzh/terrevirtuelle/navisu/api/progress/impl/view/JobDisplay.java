package bzh.terrevirtuelle.navisu.api.progress.impl.view;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXAnimatedDisplay;
import bzh.terrevirtuelle.navisu.core.view.display.jfx.impl.JFXAbstractAnimatedDisplay;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 14:55
 */
public class JobDisplay extends JFXAbstractAnimatedDisplay {

    @Override
    protected Transition createShowTransition() {

        final FadeTransition transition = new FadeTransition(Duration.millis(1000), this.getDisplayable());

        transition.setFromValue(0d);
        transition.setToValue(1d);
        transition.setCycleCount(1);

        return transition;
    }

    @Override
    protected Transition createHideTransition() {

        final FadeTransition transition = new FadeTransition(Duration.millis(1000), this.getDisplayable());

        transition.setFromValue(1d);
        transition.setToValue(0d);
        transition.setCycleCount(1);

        return transition;
    }

    @Override
    public Node getDisplayable() {
        return null;
    }
}
