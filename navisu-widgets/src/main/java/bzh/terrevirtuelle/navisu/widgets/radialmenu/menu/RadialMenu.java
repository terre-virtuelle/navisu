package bzh.terrevirtuelle.navisu.widgets.radialmenu.menu;


import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class RadialMenu extends Group {

    private DoubleProperty gap;

    private DoubleProperty length;
    private DoubleProperty innerRadius;
    private DoubleProperty outerRadius;

    private ObservableList<RadialMenuItem> rootItems = FXCollections.observableArrayList();

    public RadialMenu(double innerRadius, double outerRadius, double length, double gap, RadialMenuItem... items) {
        innerRadiusProperty().set(innerRadius);
        outerRadiusProperty().set(outerRadius);
        lengthProperty().set(length);
        gapProperty().set(gap);

        this.rootItems.addListener((InvalidationListener) obs -> updateChildren());

        this.rootItems.addAll(items);

        getChildren().addAll(items);
    }

    private void updateChildren() {
        if(rootItems.size() == 0) return;

        double radialItemLenght = getLength() / rootItems.size();
        for(int i = 0; i < rootItems.size() ; i++) {
            RadialMenuItem radialMenuItem = rootItems.get(i);
            radialMenuItem.setInnerRadius(getInnerRadius());
            radialMenuItem.setOuterRadius(getOuterRadius());
            radialMenuItem.setGap(getGap());
            radialMenuItem.setLength(radialItemLenght);
            radialMenuItem.setStartAngle(i * radialItemLenght);

            if (radialMenuItem instanceof RadialMenuContainer) {
                ((RadialMenuContainer) radialMenuItem).updateChildren();
            }
        }
    }

    /************************************************************/
    public final DoubleProperty gapProperty() {
        if (gap == null) {
            gap = new SimpleDoubleProperty(this, "gap") {
                @Override
                protected void invalidated() {
                    updateChildren();
                }
            };
        }
        return gap;
    }

    public final double getGap() {
        return gapProperty().get();
    }

    public final void setGap(double gap) {
        this.gapProperty().set(gap);
    }
    /************************************************************/
    public final DoubleProperty lengthProperty() {
        if (length == null) {
            length = new SimpleDoubleProperty(this, "length") {
                @Override
                protected void invalidated() {
                    updateChildren();
                }
            };
        }
        return length;
    }

    public final double getLength() {
        return lengthProperty().get();
    }

    public final void setLength(double length) {
        this.lengthProperty().set(length);
    }

    /************************************************************/
    public final DoubleProperty innerRadiusProperty() {
        if (innerRadius == null) {
            innerRadius = new SimpleDoubleProperty(this, "innerRadius") {
                @Override
                protected void invalidated() {
                    updateChildren();
                }
            };
        }
        return innerRadius;
    }

    public final double getInnerRadius() {
        return innerRadiusProperty().get();
    }

    public final void setInnerRadius(double innerRadius) {
        this.innerRadiusProperty().set(innerRadius);
    }

    /************************************************************/
    public final DoubleProperty outerRadiusProperty() {
        if (outerRadius == null) {
            outerRadius = new SimpleDoubleProperty(this, "outerRadius") {
                @Override
                protected void invalidated() {
                    updateChildren();
                }
            };
        }
        return outerRadius;
    }

    public final double getOuterRadius() {
        return outerRadiusProperty().get();
    }

    public final void setOuterRadius(double outerRadius) {
        this.outerRadiusProperty().set(outerRadius);
    }

    /************************************************************/
    public void addRootItem(RadialMenuItem rootItem) {
        rootItems.add(rootItem);
        if(rootItem instanceof RadialMenuContainer)  {

            RadialMenuContainer container = (RadialMenuContainer) rootItem;
            container.getPath().addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
                for(RadialMenuItem item : rootItems) {
                    if(item instanceof RadialMenuContainer) {
                        RadialMenuContainer otherContainer = (RadialMenuContainer) item;
                        if(otherContainer != container)
                            otherContainer.setChildrenVisible(false);
                    }
                }
                e.consume();
            });
        }

        getChildren().add(rootItem);
    }
}