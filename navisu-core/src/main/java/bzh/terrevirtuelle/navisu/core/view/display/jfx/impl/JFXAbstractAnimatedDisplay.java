package bzh.terrevirtuelle.navisu.core.view.display.jfx.impl;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXAnimatedDisplay;
import javafx.animation.Transition;

/**
 * NaVisu
 *
 * @author tibus
 * @date 07/11/2013 20:41
 */
public abstract class JFXAbstractAnimatedDisplay extends JFXAbstractDisplay implements JFXAnimatedDisplay {


    protected final Transition showTransition;
    protected final Transition hideTransition;

    protected JFXAbstractAnimatedDisplay() {
        this.showTransition = this.createShowTransition();
        this.hideTransition = this.createHideTransition();


        this.hideTransition.setOnFinished(e -> {
            getDisplayable().setVisible(false);
        });
    }

    protected abstract Transition createShowTransition();
    protected abstract Transition createHideTransition();

    @Override
    public void setVisible(boolean visible) {

        if(visible) {
            this.playShowTransition();
        }

        else {
            this.playHideTransition();
        }
    }

    @Override
    public void playShowTransition() {

        this.getDisplayable().setVisible(true);

        if(this.showTransition != null) {
            this.showTransition.play();
        }
    }

    @Override
    public void playHideTransition() {

        if(this.hideTransition != null) {
            this.hideTransition.play();
        }
        else  {
            this.getDisplayable().setVisible(false);
        }
    }
}
