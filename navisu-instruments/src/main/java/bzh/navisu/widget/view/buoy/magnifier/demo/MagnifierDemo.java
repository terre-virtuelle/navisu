package com.javafx.magnifier.demo;

import java.text.DecimalFormat;

import com.javafx.magnifier.control.MagnifierPane;
import javafx.application.Application;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.RadioButtonBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Demo class for the MagnifierPane control.
 * @author Sai.Dandem
 *
 */
public class MagnifierDemo extends Application {
	private BorderPane root;
	private DoubleProperty radius = new SimpleDoubleProperty();
	private DoubleProperty frameWidth = new SimpleDoubleProperty();
	private DoubleProperty scaleFactor = new SimpleDoubleProperty();
	private DoubleProperty scopeLineWidth = new SimpleDoubleProperty();
	private BooleanProperty scopeLinesVisible = new SimpleBooleanProperty();

	final DecimalFormat df = new DecimalFormat("##.#");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Magnifier");
		stage.setWidth(1000);
		stage.setHeight(700);
		root = BorderPaneBuilder.create().padding(new Insets(10)).build();
		Scene scene = new Scene(root, Color.CORNSILK);
		stage.setScene(scene);
		stage.show();
		configurePropertyPane();
		configure();
	}

	private void configurePropertyPane() {
		GridPane gp = GridPaneBuilder.create().vgap(10).hgap(10).padding(new Insets(0, 15, 0, 15)).build();
		final Slider rSlider = new Slider(50, 150, 86);
		radius.bind(rSlider.valueProperty());
		Label rL = new Label();
		rL.textProperty().bind(new StringBinding() {
			{
				bind(rSlider.valueProperty());
			}

			@Override
			protected String computeValue() {
				return df.format(rSlider.getValue()) + "px";
			}
		});

		final Slider fmSlider = new Slider(3, 10, 5.5);
		frameWidth.bind(fmSlider.valueProperty());
		Label fmL = new Label();
		fmL.textProperty().bind(new StringBinding() {
			{
				bind(fmSlider.valueProperty());
			}

			@Override
			protected String computeValue() {
				return df.format(fmSlider.getValue()) + "px";
			}
		});

		final Slider sfSlider = new Slider(1, 8, 3);
		scaleFactor.bind(sfSlider.valueProperty());
		Label sfL = new Label();
		sfL.textProperty().bind(new StringBinding() {
			{
				bind(sfSlider.valueProperty());
			}

			@Override
			protected String computeValue() {
				return df.format(sfSlider.getValue()) + "";
			}
		});

		CheckBox slVisibleCB = new CheckBox();
		scopeLinesVisible.bind(slVisibleCB.selectedProperty());

		final Slider sllider = new Slider(1, 4, 1.5);
		sllider.disableProperty().bind(slVisibleCB.selectedProperty().not());

		scopeLineWidth.bind(sllider.valueProperty());
		Label slL = new Label();
		slL.textProperty().bind(new StringBinding() {
			{
				bind(sllider.valueProperty());
			}

			@Override
			protected String computeValue() {
				return df.format(sllider.getValue()) + "px";
			}
		});

		gp.add(LabelBuilder.create().text("Configurable Properties :").style("-fx-font-weight:bold;-fx-font-size:18px;").build(), 0, 0, 4,
				1);
		gp.addRow(1, new Label("Radius "), new Label(":"), rSlider, rL);
		gp.addRow(2, new Label("Frame Width "), new Label(":"), fmSlider, fmL);
		gp.addRow(3, new Label("Scale Factor "), new Label(":"), sfSlider, sfL);
		gp.addRow(4, new Label("Scope Lines Visible "), new Label(":"), slVisibleCB,
				StackPaneBuilder.create().minWidth(50).children(new Label("")).build());
		gp.addRow(5, new Label("Scope Line Width "), new Label(":"), sllider, slL);
		root.setRight(gp);
	}

	private void configure() {
		TabPane tabPane = new TabPane();

		Tab tab1 = new Tab("Sample 1");
		final ImageView sample1 = new ImageView(new Image(MagnifierDemo.class.getResourceAsStream("/images/rocket-blueprint.jpg")));
		sample1.setFitHeight(620);
		sample1.setFitWidth(420);
		configureSample(tab1, sample1);

		Tab tab2 = new Tab("Sample 2");
		final ImageView sample2 = new ImageView(new Image(MagnifierDemo.class.getResourceAsStream("/images/pic.jpg")));
		sample2.setFitHeight(500);
		sample2.setFitWidth(500);
		configureSample(tab2, sample2);

		Tab tab3 = new Tab("Sample 3");
		VBox vb = VBoxBuilder
				.create()
				.alignment(Pos.CENTER)
				.spacing(10)
				.children(CheckBoxBuilder.create().selected(true).build(), RadioButtonBuilder.create().selected(true).build(),
						ButtonBuilder.create().text("Magnifier").build()).build();
		configureSample(tab3, vb);

		tabPane.getTabs().addAll(tab1, tab2, tab3);
		root.setCenter(tabPane);
	}

	private void configureSample(Tab tab, Node sample) {
		MagnifierPane p = new MagnifierPane();
		p.radiusProperty().bind(radius);
		p.frameWidthProperty().bind(frameWidth);
		p.scaleFactorProperty().bind(scaleFactor);
		p.scopeLineWidthProperty().bind(scopeLineWidth);
		p.scopeLinesVisibleProperty().bind(scopeLinesVisible);
		p.getChildren().add(sample);
		StackPane c = StackPaneBuilder.create().padding(new Insets(20)).build();
		c.getChildren().add(p);
		tab.setContent(c);
	}

}
