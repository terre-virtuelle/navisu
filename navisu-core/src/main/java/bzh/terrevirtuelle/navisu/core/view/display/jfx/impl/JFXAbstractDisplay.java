package bzh.terrevirtuelle.navisu.core.view.display.jfx.impl;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXDisplay;
import javafx.scene.Node;

/**
 * NaVisu
 *
 * @author tibus
 * @date 07/11/2013 20:41
 */
public abstract class JFXAbstractDisplay implements JFXDisplay {

    @Override
    public void setVisible(boolean visible) {
        this.getDisplayable().setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return this.getDisplayable().isVisible();
    }
}
