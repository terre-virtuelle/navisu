package bzh.terrevirtuelle.navisu.widgets.radialmenu.menu;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;

public class RadialMenuContainer extends RadialMenuItem {

    private ObservableList<RadialMenuItem> items = FXCollections.observableArrayList();
    private BooleanProperty isChildrenVisible;

    public RadialMenuContainer() {

        setChildrenVisible(false);

        getPath().addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            setChildrenVisible(!isChildrenVisible());
            event.consume();
        });

        items.addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                update();
            }
        });
    }

    protected void updateChildren() {
        if (items.size() == 0) {
            return;
        }
        //TODO Add root menu length to compute the radial item length
        double radialItemLenght = 360 / items.size();

        for (int i = 0; i < items.size(); i++) {

            RadialMenuItem radialMenuItem = items.get(i);

            double parentInnerRadius = radialMenuItem.getParentItem().getInnerRadius();
            double parentOuterRadius = radialMenuItem.getParentItem().getOuterRadius();
            double parentArcLenght = radialMenuItem.getParentItem().getLength();

            double newInnerRadius = parentOuterRadius;
            double newOuterRadius = parentOuterRadius + (parentOuterRadius - parentInnerRadius);

            double ratio = parentOuterRadius / newOuterRadius;
            double newArcLenght = parentArcLenght * ratio;
            //double newArcLenght = radialItemLenght;
            //System.out.println("@@@@: " + newArcLenght);
            double startAngle = radialMenuItem.getParentItem().getStartAngle(); //+ ((newArcLenght - parentArcLenght) / 2);

            radialMenuItem.setInnerRadius(newInnerRadius);
            radialMenuItem.setOuterRadius(newOuterRadius);
            radialMenuItem.setStartAngle(i * newArcLenght + startAngle);
            radialMenuItem.setLength(newArcLenght);
            radialMenuItem.setGap(radialMenuItem.getParentItem().getGap());
            radialMenuItem.setVisible(isChildrenVisible());

            if (radialMenuItem instanceof RadialMenuContainer) {
                ((RadialMenuContainer) radialMenuItem).updateChildren();
            }
        }
    }

    public void addItem(RadialMenuItem item) {
        item.setParentItem(this);
        items.add(item);

        getChildren().add(item);
    }

    public ObservableList<RadialMenuItem> getItems() {
        return items;
    }

    /**
     * *********************************************************
     */
    public final BooleanProperty isChildrenVisibleProperty() {
        if (isChildrenVisible == null) {
            isChildrenVisible = new SimpleBooleanProperty(this, "isChildrenVisible") {
                @Override
                protected void invalidated() {
                    for (RadialMenuItem item : items) {
                        item.setVisible(get());
                    }
                }
            };
        }
        return isChildrenVisible;
    }

    public final boolean isChildrenVisible() {
        return isChildrenVisibleProperty().get();
    }

    public final void setChildrenVisible(boolean isChildrenVisible) {
        this.isChildrenVisibleProperty().set(isChildrenVisible);
    }

//	private int computeLevel(int level) {
//		
//		if(this instanceof RadialMenuItem) {
//			return level;
//		}
//		
//		if(getParentItem() == null) {
//			return level;
//		}
//		
//		return ((RadialMenuContainer)getParentItem()).computeLevel(level + 1);
//	}
}
