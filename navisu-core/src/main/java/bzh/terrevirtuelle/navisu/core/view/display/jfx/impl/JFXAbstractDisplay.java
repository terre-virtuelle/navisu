package bzh.terrevirtuelle.navisu.core.view.display.jfx.impl;

import bzh.terrevirtuelle.navisu.core.view.display.jfx.JFXDisplay;
import javafx.scene.Node;
import javafx.scene.layout.Region;

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

    @Override
    public void setMaxHeight(double maxHeight) {

        if(Region.class.isAssignableFrom(this.getDisplayable().getClass())) {
            ((Region)this.getDisplayable()).setMaxHeight(maxHeight);
        }
    }

    @Override
    public double getMaxHeight() {

        double maxHeight = Double.MAX_VALUE;

        if(Region.class.isAssignableFrom(this.getDisplayable().getClass())) {
            maxHeight = ((Region)this.getDisplayable()).getMaxHeight();
        }

        return maxHeight;
    }

    @Override
    public void setMaxWidth(double maxWidth) {

        if(Region.class.isAssignableFrom(this.getDisplayable().getClass())) {
            ((Region)this.getDisplayable()).setMaxWidth(maxWidth);
        }
    }

    @Override
    public double getMaxWidth() {
        double maxWidth = Double.MAX_VALUE;

        if(Region.class.isAssignableFrom(this.getDisplayable().getClass())) {
            maxWidth = ((Region)this.getDisplayable()).getMaxWidth();
        }

        return maxWidth;
    }
}
