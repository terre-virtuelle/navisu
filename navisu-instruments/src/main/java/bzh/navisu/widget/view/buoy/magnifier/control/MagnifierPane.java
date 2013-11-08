/**
 * (C) 2013 HealthConnect NV. All rights reserved.
 */
package com.javafx.magnifier.control;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoublePropertyBase;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.PerspectiveCameraBuilder;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Scale;
import javafx.stage.Popup;
import javafx.util.Callback;

/**
 * Pane which shows the details of its children in a magnified.
 */
public class MagnifierPane extends StackPane {

	// Configurable properties
	private DoubleProperty radius;
	private DoubleProperty frameWidth;
	private DoubleProperty scaleFactor;
	private DoubleProperty scopeLineWidth;
	private BooleanProperty scopeLinesVisible;

	// Default values
	private final double DEFAULT_RADIUS = 86.0D;
	private final double DEFAULT_FRAME_WIDTH = 5.5D;
	private final double DEFAULT_SCALE_FACTOR = 3.0D;
	private final double DEFAULT_SCOPELINE_WIDTH = 1.5D;
	private final boolean DEFAULT_SCOPELINE_VISIBLE = false;

	private Scene scene;
	private WritableImage writeImg;

	public MagnifierPane() {
		super();
		setCursor(Cursor.CROSSHAIR);
		final ImageView snapView = new ImageView();
		final Callback<SnapshotResult, Void> callBack = new Callback<SnapshotResult, Void>() {
			@Override
			public Void call(SnapshotResult result) {
				return null;
			}
		};
		final Scale scale = new Scale();
		scale.xProperty().bind(scaleFactorProperty());
		scale.yProperty().bind(scaleFactorProperty());
		final SnapshotParameters param = new SnapshotParameters();
		param.setCamera(PerspectiveCameraBuilder.create().fieldOfView(45).build());
		param.setDepthBuffer(true);
		param.setTransform(scale);

		final StackPane mainContent = StackPaneBuilder.create().build();
		final Circle cEdge = CircleBuilder
				.create()
				.style("-fx-fill:radial-gradient(focus-angle 0deg , focus-distance 0% , center 50% 50% , radius 50% , #f0f8ff 93% , #696969 94% , #FaFaFa 97% , #808080);")
				.build();
		cEdge.radiusProperty().bind(new DoubleBinding() {
			{
				bind(radiusProperty(), frameWidthProperty());
			}

			@Override
			protected double computeValue() {
				return getRadius() + getFrameWidth();
			}
		});

		final Circle cClip = CircleBuilder.create().build();
		cClip.radiusProperty().bind(radiusProperty());
		cClip.translateXProperty().bind(radiusProperty());
		cClip.translateYProperty().bind(radiusProperty());

		final Magnifier clippedNode = new Magnifier(radiusProperty(), radiusProperty());
		clippedNode.setClip(cClip);

		final Line vL = LineBuilder.create().stroke(Color.RED).startX(0).startY(0).endX(0).opacity(.5).build();
		vL.strokeWidthProperty().bind(scopeLineWidthProperty());
		vL.visibleProperty().bind(scopeLinesVisibleProperty());
		vL.endYProperty().bind(radiusProperty().multiply(2));

		final Line hL = LineBuilder.create().stroke(Color.RED).startX(0).startY(0).endY(0).opacity(.5).build();
		hL.strokeWidthProperty().bind(scopeLineWidthProperty());
		hL.visibleProperty().bind(scopeLinesVisibleProperty());
		hL.endXProperty().bind(radiusProperty().multiply(2));

		// Adding all parts in a container.
		mainContent.getChildren().addAll(cEdge, clippedNode, vL, hL);

		final Popup viewer = new Popup();
		viewer.getContent().add(mainContent);

		// Adding mask implementation
		final StackPane mask = new StackPane();
		getChildren().add(mask);
		final SimpleBooleanProperty maskFlag = new SimpleBooleanProperty(true);
		getChildren().addListener(new ListChangeListener<Node>() {
			@Override
			public void onChanged(Change<? extends Node> param) {
				if (maskFlag.get()) {
					maskFlag.set(false);
					getChildren().remove(mask);
					getChildren().add(mask);
					maskFlag.set(true);
				}
			}
		});

		addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				viewer.show(MagnifierPane.this, e.getScreenX(), e.getScreenY());
				int w = (int) (MagnifierPane.this.getWidth() * getScaleFactor());
				int h = (int) (MagnifierPane.this.getHeight() * getScaleFactor());
				writeImg = new WritableImage(w, h);

				// Get snapshot image
				MagnifierPane.this.snapshot(callBack, param, writeImg);
				snapView.setImage(writeImg);
				clippedNode.setContent(snapView);
			}
		});

		addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				viewer.hide();
			}
		});

		addEventFilter(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				final double r = getRadius();
				final double s = getScaleFactor();
				if (e.getSceneX() > (scene.getWidth() - (2 * r))) {
					viewer.setX(e.getScreenX() - (2 * r));
				} else {
					viewer.setX(e.getScreenX());
				}

				if (e.getSceneY() > (scene.getHeight() - (2 * r))) {
					viewer.setY(e.getScreenY() - (2 * r));
				} else {
					viewer.setY(e.getScreenY());
				}

				clippedNode.transXProperty().set((e.getX() * s) - r);
				clippedNode.transYProperty().set((e.getY() * s) - r);
			}
		});
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		if (this.scene == null) {
			this.scene = getScene();
		}
	}

	// Getters & Setters
	public final DoubleProperty radiusProperty() {
		if (this.radius == null) {
			this.radius = new DoublePropertyBase(DEFAULT_RADIUS) {
				@Override
				public String getName() {
					return "radius";
				}

				@Override
				public Object getBean() {
					return MagnifierPane.this;
				}
			};
		}
		return this.radius;
	}

	public final void setRadius(Double paramRadius) {
		radiusProperty().setValue(paramRadius);
	}

	public final Double getRadius() {
		return ((this.radius == null) ? DEFAULT_RADIUS : this.radius.getValue());
	}

	public final DoubleProperty frameWidthProperty() {
		if (this.frameWidth == null) {
			this.frameWidth = new DoublePropertyBase(DEFAULT_FRAME_WIDTH) {
				@Override
				public String getName() {
					return "frameWidth";
				}

				@Override
				public Object getBean() {
					return MagnifierPane.this;
				}
			};
		}
		return this.frameWidth;
	}

	public final void setFrameWidth(Double paramFrameWidth) {
		frameWidthProperty().setValue(paramFrameWidth);
	}

	public final Double getFrameWidth() {
		return ((this.frameWidth == null) ? DEFAULT_FRAME_WIDTH : this.frameWidth.getValue());
	}

	public final DoubleProperty scaleFactorProperty() {
		if (this.scaleFactor == null) {
			this.scaleFactor = new DoublePropertyBase(DEFAULT_SCALE_FACTOR) {
				@Override
				public String getName() {
					return "scaleFactor";
				}

				@Override
				public Object getBean() {
					return MagnifierPane.this;
				}
			};
		}
		return this.scaleFactor;
	}

	public final void setScaleFactor(Double paramScaleFactor) {
		scaleFactorProperty().setValue(paramScaleFactor);
	}

	public final Double getScaleFactor() {
		return ((this.scaleFactor == null) ? DEFAULT_SCALE_FACTOR : this.scaleFactor.getValue());
	}

	public final DoubleProperty scopeLineWidthProperty() {
		if (this.scopeLineWidth == null) {
			this.scopeLineWidth = new DoublePropertyBase(DEFAULT_SCOPELINE_WIDTH) {
				@Override
				public String getName() {
					return "scopeLineWidth";
				}

				@Override
				public Object getBean() {
					return MagnifierPane.this;
				}
			};
		}
		return this.scopeLineWidth;
	}

	public final void setScopeLineWidth(Double paramScopeLineWidth) {
		scopeLineWidthProperty().setValue(paramScopeLineWidth);
	}

	public final Double getScopeLineWidth() {
		return ((this.scopeLineWidth == null) ? DEFAULT_SCOPELINE_WIDTH : this.scopeLineWidth.getValue());
	}

	public final BooleanProperty scopeLinesVisibleProperty() {
		if (this.scopeLinesVisible == null) {
			this.scopeLinesVisible = new BooleanPropertyBase(DEFAULT_SCOPELINE_VISIBLE) {

				@Override
				public String getName() {
					return "scopeLinesVisible";
				}

				@Override
				public Object getBean() {
					return MagnifierPane.this;
				}
			};
		}
		return this.scopeLinesVisible;
	}

	public final void setScopeLinesVisible(Boolean paramScopeLinesVisible) {
		scopeLinesVisibleProperty().setValue(paramScopeLinesVisible);
	}

	public final Boolean getScopeLinesVisible() {
		return ((this.scopeLinesVisible == null) ? DEFAULT_SCOPELINE_VISIBLE : this.scopeLinesVisible.getValue());
	}

	/**
	 * Region that holds the clip area.
	 */
	class Magnifier extends Region {
		private Node content;
		private final DoubleProperty width = new SimpleDoubleProperty();
		private final DoubleProperty height = new SimpleDoubleProperty();
		private final Rectangle clip;
		private final SimpleDoubleProperty transX = new SimpleDoubleProperty();
		private final SimpleDoubleProperty transY = new SimpleDoubleProperty();

		public Magnifier(DoubleProperty w, DoubleProperty h) {
			this.width.bind(w.multiply(2));
			this.height.bind(h.multiply(2));
			this.clip = RectangleBuilder.create().build();
			this.clip.widthProperty().bind(this.width);
			this.clip.heightProperty().bind(this.height);
			this.clip.translateXProperty().bind(transX);
			this.clip.translateYProperty().bind(transY);
		}

		public void setContent(Node content) {
			if (this.content != null) {
				this.content.setClip(null);
				this.content.translateXProperty().unbind();
				this.content.translateYProperty().unbind();
				getChildren().clear();
			}
			this.content = content;
			this.content.setClip(this.clip);
			this.content.translateXProperty().bind(transX.multiply(-1));
			this.content.translateYProperty().bind(transY.multiply(-1));
			getChildren().setAll(content);
		}

		@Override
		protected double computeMinWidth(double d) {
			return width.get();
		}

		@Override
		protected double computeMinHeight(double d) {
			return height.get();
		}

		@Override
		protected double computePrefWidth(double d) {
			return width.get();
		}

		@Override
		protected double computePrefHeight(double d) {
			return height.get();
		}

		@Override
		protected double computeMaxWidth(double d) {
			return width.get();
		}

		@Override
		protected double computeMaxHeight(double d) {
			return height.get();
		}

		public SimpleDoubleProperty transXProperty() {
			return transX;
		}

		public SimpleDoubleProperty transYProperty() {
			return transY;
		}
	}
}
