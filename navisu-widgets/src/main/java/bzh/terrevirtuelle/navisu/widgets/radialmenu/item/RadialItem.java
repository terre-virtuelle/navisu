package bzh.terrevirtuelle.navisu.widgets.radialmenu.item;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * NaVisu
 *
 * @author Jordan Mens
 */
public class RadialItem extends Group {

    protected static final int ANGLE_TO_START_AT_ZERO_DEGREE = -90;

    private DoubleProperty innerRadius;
    private DoubleProperty outerRadius;
    private DoubleProperty length;
    private DoubleProperty startAngle;
    private DoubleProperty gap;

    private ObjectProperty<ImageView> image;

    protected Path path = new Path();
    protected MoveTo moveTo = new MoveTo();
    protected ArcTo arcToInner = new ArcTo();
    protected ArcTo arcTo = new ArcTo();
    protected LineTo lineTo = new LineTo();
    protected LineTo lineTo2 = new LineTo();

    public RadialItem() {

        this.path.setId("radialItem");

        this.path.getElements().add(moveTo);
        this.path.getElements().add(lineTo);
        this.path.getElements().add(arcTo);
        this.path.getElements().add(lineTo2);
        this.path.getElements().add(arcToInner);

        getChildren().add(this.path);
    }

    public RadialItem(EventHandler<MouseEvent> callback) {

        this.path.setId("radialItem");

        this.path.getElements().add(moveTo);
        this.path.getElements().add(lineTo);
        this.path.getElements().add(arcTo);
        this.path.getElements().add(lineTo2);
        this.path.getElements().add(arcToInner);
        this.path.setOnMouseClicked(callback);
        getChildren().add(this.path);
    }

    public RadialItem(double innerRadius, double outerRadius,
            double arcLengthAngle, double startAngle) {

        this();

        innerRadiusProperty().set(innerRadius);
        outerRadiusProperty().set(outerRadius);
        lengthProperty().set(arcLengthAngle);
        startAngleProperty().set(startAngle);
    }

    protected void update() {

        //Arc lenght angle
        double arcAngleLengthInRadians = Math.toRadians(getLength());

        //Angle rotation of the item
        double startAngleInRadians = Math.toRadians(getStartAngle() + ANGLE_TO_START_AT_ZERO_DEGREE);

        moveTo.setX(getInnerRadius() * Math.cos(startAngleInRadians));
        moveTo.setY(getInnerRadius() * Math.sin(startAngleInRadians));

        lineTo.setX(getOuterRadius() * Math.cos(startAngleInRadians));
        lineTo.setY(getOuterRadius() * Math.sin(startAngleInRadians));

        arcTo.setX(getOuterRadius() * Math.cos(startAngleInRadians + arcAngleLengthInRadians - Math.asin(getGap() / getOuterRadius())));
        arcTo.setY(getOuterRadius() * Math.sin(startAngleInRadians + arcAngleLengthInRadians - Math.asin(getGap() / getOuterRadius())));
        arcTo.setRadiusX(getOuterRadius());
        arcTo.setRadiusY(getOuterRadius());
        arcTo.setSweepFlag(true);
        arcTo.setLargeArcFlag(getLength() > 180);

        lineTo2.setX(getInnerRadius() * Math.cos(startAngleInRadians + arcAngleLengthInRadians - Math.asin(getGap() / getInnerRadius())));
        lineTo2.setY(getInnerRadius() * Math.sin(startAngleInRadians + arcAngleLengthInRadians - Math.asin(getGap() / getInnerRadius())));

        arcToInner.setX(getInnerRadius() * Math.cos(startAngleInRadians));
        arcToInner.setY(getInnerRadius() * Math.sin(startAngleInRadians));
        arcToInner.setRadiusX(getInnerRadius());
        arcToInner.setRadiusY(getInnerRadius());
        arcToInner.setSweepFlag(false);
        arcToInner.setLargeArcFlag(getLength() > 180);

        double centerRadius = (getOuterRadius() + getInnerRadius()) / 2;
        Point2D centerOfPath = new Point2D(centerRadius * Math.cos(startAngleInRadians + arcAngleLengthInRadians / 2),
                centerRadius * Math.sin(startAngleInRadians + arcAngleLengthInRadians / 2));

        if (this.getImage() != null) {
            double width = this.getImage().getBoundsInLocal().getWidth();
            double height = this.getImage().getBoundsInLocal().getHeight();
            this.getImage().relocate(centerOfPath.getX() - width / 2, centerOfPath.getY() - height / 2);
        }
    }

    /**
     * *********************************************************
     */
    public final DoubleProperty innerRadiusProperty() {
        if (innerRadius == null) {
            innerRadius = new SimpleDoubleProperty(this, "innerRadius") {
                @Override
                protected void invalidated() {
                    update();
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

    /**
     * *********************************************************
     */
    public final DoubleProperty outerRadiusProperty() {
        if (outerRadius == null) {
            outerRadius = new SimpleDoubleProperty(this, "outerRadius") {
                @Override
                protected void invalidated() {
                    update();
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

    /**
     * *********************************************************
     */
    public final DoubleProperty lengthProperty() {
        if (length == null) {
            length = new SimpleDoubleProperty(this, "length") {
                @Override
                protected void invalidated() {
                    update();
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

    /**
     * *********************************************************
     */
    public final DoubleProperty startAngleProperty() {
        if (startAngle == null) {
            startAngle = new SimpleDoubleProperty(this, "startAngle") {
                @Override
                protected void invalidated() {
                    update();
                }
            };
        }
        return startAngle;
    }

    public final double getStartAngle() {
        return startAngleProperty().get();
    }

    public final void setStartAngle(double startAngle) {
        this.startAngleProperty().set(startAngle);
    }

    /**
     * *********************************************************
     */
    public final DoubleProperty gapProperty() {
        if (gap == null) {
            gap = new SimpleDoubleProperty(this, "gap") {
                @Override
                protected void invalidated() {
                    update();
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

    /**
     * *********************************************************
     */
    public final ObjectProperty<ImageView> imageProperty() {
        if (image == null) {
            image = new SimpleObjectProperty<ImageView>(this, "image") {
                @Override
                protected void invalidated() {
                    if (getChildren().contains(get())) {
                        getChildren().remove(get());
                    }
                    getChildren().add(get());
                    get().setMouseTransparent(true);
                    update();
                }
            };
        }
        return image;
    }

    public final ImageView getImage() {
        return imageProperty().get();
    }

    public final void setImage(ImageView image) {
        this.imageProperty().set(image);
    }

    public Path getPath() {
        return path;
    }
}
