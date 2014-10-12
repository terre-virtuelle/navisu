package jfxtras.labs.samples;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.WeakHashMap;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Priority;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.scene.layout.GridPane;
import jfxtras.scene.layout.VBox;

/**
 */
abstract public class SampleBase extends fxsampler.SampleBase {
	
	@Override
	public String getSampleSourceURL() {
		String s = "https://raw.github.com/JFXtras/jfxtras-labs-samples/8.0/src/main/java/" + this.getClass().getName().replace(".", "/") + ".java";
		//System.out.println(s);
		return s;
	}

	/**
	 * Create a TextArea that automatically saves its contents as a CSS files and adds that to the stage.
	 * 
	 * @param stage
	 * @return 
	 */
	protected TextArea createTextAreaForCSS(Stage stage, ObservableList<String> examples) {
		try {
			// clear any existing
			stage.getScene().getStylesheets().clear();
			
			// the CSS file
			File lFile = File.createTempFile(this.getClass().getSimpleName(), ".css");
			lFile.deleteOnExit();

			// text field
			final TextArea lTextArea = new TextArea();			
			lTextArea.setTooltip(new Tooltip(examples.size() + " example(s) available under double click"));
			lTextArea.focusedProperty().addListener( (observable) -> {
				applyCSSInTextArea(stage, lFile, lTextArea);
			});
			
			// bind a popup
			lTextArea.setOnMouseClicked( (evt) -> {
				// only if the right mouse button is pressed
				if (evt.getClickCount() < 2) {
					return;
				}

				// in a popup
				final Popup lPopup = new Popup();
				lPopup.setAutoFix(true);
				lPopup.setAutoHide(true);
				lPopup.setHideOnEscape(true);

				// container
				VBox lVBox = new VBox();

				// the list to show
				final ListView<String> lListView = new ListView<>(examples);			
				lListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
				lListView.setTooltip(new Tooltip("Double click or multiselect and use apply"));
				lListView.setOnMouseClicked( (eventHandler) -> {
					if (eventHandler.getClickCount() >= 2) {
						setCSSTextInTextArea(lListView, lTextArea);
						lPopup.hide(); // TODO: does this introduce a memory leak?
						applyCSSInTextArea(stage, lFile, lTextArea);
					}
				});
				lVBox.add(lListView, new VBox.C().vgrow(Priority.ALWAYS));

				// button
				Button lApplyButton = new Button("Apply");
				lVBox.add(lApplyButton, new VBox.C().vgrow(Priority.ALWAYS));
				lApplyButton.setOnAction( (eventHandler) -> {
					setCSSTextInTextArea(lListView, lTextArea);
					lPopup.hide(); // TODO: does this introduce a memory leak?
					applyCSSInTextArea(stage, lFile, lTextArea);
				});

				// show
				lPopup.getContent().add(lVBox);
				lPopup.show(lTextArea, jfxtras.util.NodeUtil.screenX(lTextArea), jfxtras.util.NodeUtil.screenY(lTextArea));
			});

			// done
			return lTextArea;
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void applyCSSInTextArea(Stage stage, File file, TextArea textArea) {
		stage.getScene().getStylesheets().remove(file.toURI().toString());
		try ( 
			FileWriter lFileWriter = new FileWriter(file, false); 
		){
			lFileWriter.write(textArea.getText());
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		stage.getScene().getStylesheets().add(file.toURI().toString());
	}
	
	private void setCSSTextInTextArea(ListView<String> listView, TextArea textArea) {
		String lStyleSheet = "";
		for (String s : listView.getSelectionModel().getSelectedItems()) {
			if (lStyleSheet.length() > 0) {
				lStyleSheet += "\n";
			}
			lStyleSheet += s;
		}
		textArea.setText( textArea.getText() + (textArea.getText().length() == 0 ? "" : "\n") + lStyleSheet );
	}
	
	
	protected <T> int addObservableListManagementControlsToGridPane(String label, String tooltip, GridPane gridPane, int rowIdx, ObservableList<T> observableList, StringToTypeConverter<T> stringConverter) {
		Map<T, String> map = new WeakHashMap<>();

		Label lLabel = new Label(label);
		gridPane.add(lLabel, new GridPane.C().row(rowIdx).col(0).halignment(HPos.RIGHT).valignment(VPos.TOP));

		// text field
		final TextField lTextField = new TextField();
		lTextField.setTooltip(new Tooltip(tooltip));
		gridPane.add(lTextField, new GridPane.C().row(rowIdx).col(1).hgrow(Priority.ALWAYS));
		
		// add button
		{
			Button lButton = new Button("add");
			gridPane.add(lButton, new GridPane.C().row(rowIdx).col(2).hgrow(Priority.SOMETIMES));
			lButton.onActionProperty().set( (actionEvent) -> {
				String s = lTextField.getText();
				T lT = stringConverter.fromString(s);
				observableList.add( lT );
				map.put(lT, s);
				lTextField.clear();
			});
		}
		rowIdx++;

		// listview
		final ListView<T> lListView = new ListView<>();
		lListView.setItems(observableList);
		lListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<T>() {
			@Override
			public String toString(T o) {
				return o == null ? "" : map.get(o);
			}

			@Override
			public T fromString(String s) {
					return null;  //never used
			}
		}));
		gridPane.add(lListView, new GridPane.C().row(rowIdx).col(1).hgrow(Priority.ALWAYS));
		
		// remove button
		{
			Button lButton = new Button("remove");
			gridPane.add(lButton, new GridPane.C().row(rowIdx).col(2).valignment(VPos.TOP).hgrow(Priority.SOMETIMES));
			lButton.onActionProperty().set( (actionEvent) -> {
				T lT = lListView.getSelectionModel().getSelectedItem();
				if (lT != null) {
					observableList.remove(lT);
					map.remove(lT);
				}
			});
		}
		
		// done
		return rowIdx + 2;
	}
	
	@FunctionalInterface
	protected interface StringToTypeConverter<T> {
		public T fromString(String s);
	}

	protected <T> int addObservableListManagementControlsToGridPane(String label, String tooltip, GridPane gridPane, int rowIdx, ObservableList<T> observableList, Control textField, ControlToTypeConverter<T> controlConverter, TypeToStringConverter<T> toStringConverter) {
		Map<T, String> map = new WeakHashMap<>();

		Label lLabel = new Label(label);
		gridPane.add(lLabel, new GridPane.C().row(rowIdx).col(0).halignment(HPos.RIGHT).valignment(VPos.TOP));

		// text field
		textField.setTooltip(new Tooltip(tooltip));
		gridPane.add(textField, new GridPane.C().row(rowIdx).col(1).hgrow(Priority.ALWAYS));
		
		// add button
		{
			Button lButton = new Button("add");
			gridPane.add(lButton, new GridPane.C().row(rowIdx).col(2).hgrow(Priority.SOMETIMES));
			lButton.onActionProperty().set( (actionEvent) -> {
				T lT = controlConverter.fromControl(textField);
				if (lT != null) {
					observableList.add( lT );
					map.put(lT, toStringConverter.toString(lT));
				}
			});
		}
		rowIdx++;

		// listview
		final ListView<T> lListView = new ListView<>();
		lListView.setItems(observableList);
		lListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<T>() {
			@Override
			public String toString(T o) {
				return o == null ? "" : map.get(o);
			}

			@Override
			public T fromString(String s) {
					return null;  //never used
			}
		}));
		gridPane.add(lListView, new GridPane.C().row(rowIdx).col(1).hgrow(Priority.ALWAYS));
		
		// remove button
		{
			Button lButton = new Button("remove");
			gridPane.add(lButton, new GridPane.C().row(rowIdx).col(2).valignment(VPos.TOP).hgrow(Priority.SOMETIMES));
			lButton.onActionProperty().set( (actionEvent) -> {
				T lT = lListView.getSelectionModel().getSelectedItem();
				if (lT != null) {
					observableList.remove(lT);
					map.remove(lT);
				}
			});
		}
		
		// done
		return rowIdx + 2;
	}
	
	@FunctionalInterface
	protected interface ControlToTypeConverter<T> {
		public T fromControl(Control s);
	}
	
	@FunctionalInterface
	protected interface TypeToStringConverter<T> {
		public String toString(T t);
	}
}
