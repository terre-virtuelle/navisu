package bzh.terrevirtuelle.navisu.widgets.dock;

import javafx.geometry.Orientation;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.ImageView;

/**
 * NaVisu
 *
 * @author tibus
 * @date 08/03/2014 17:08
 */
public class Dock extends Group {

    public static final Orientation DEFAULT_ORIENTATION = Orientation.HORIZONTAL;

    public static final double DEFAULT_PADDING = 10.;

    protected List<DockItem> itemList;
    protected Orientation orientation;
   // protected ImageView basedock;

    {
        this.itemList = new ArrayList<>();
        this.orientation = DEFAULT_ORIENTATION;

    }

    public Dock(DockItem... items) {

        this.itemList.addAll(Arrays.asList(items));
      //  basedock = new ImageView("dock3.png");
      //  getChildren().add(basedock);
        this.updateDock();
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
        this.updateDock();
    }

    protected void updateDock() {

        this.getChildren().clear();

        DockItem item;
        for (int i = 0; i < this.itemList.size(); i++) {

            item = this.itemList.get(i);

            if (this.orientation == Orientation.HORIZONTAL) {
                item.getDisplayNode().setTranslateY(0);
                item.getDisplayNode().setTranslateX(i * item.getSize() + DEFAULT_PADDING);
                
            } else {
                item.getDisplayNode().setTranslateX(0);
                item.getDisplayNode().setTranslateY(i * item.getSize());
            }

            this.getChildren().add(item.getDisplayNode());
        }
      //  getChildren().add(basedock);
    }
}
