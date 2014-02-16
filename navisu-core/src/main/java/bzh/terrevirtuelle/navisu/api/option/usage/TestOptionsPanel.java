package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 18:12
 */
public class TestOptionsPanel extends OptionsPanel {

    private BorderPane container = new BorderPane();

    private final TextField intValueTextField;
    private final Label intErrorLabel;

    private final TextField doubleValueTextField;
    private final Label doubleErrorLabel;

    private final TextField stringValueTextField;

    public TestOptionsPanel() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Panneau d'options");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        // INT
        Label intValueLabel = new Label("Int value:");
        grid.add(intValueLabel, 0, 1);
        intValueTextField = new TextField();
        grid.add(intValueTextField, 1, 1);
        intErrorLabel = new Label("X");
        intErrorLabel.setTextFill(Color.RED);
        intErrorLabel.setVisible(false);
        grid.add(intErrorLabel, 2, 1);

        // DOUBLE
        Label doubleValueLabel = new Label("Double value:");
        grid.add(doubleValueLabel, 0, 2);
        doubleValueTextField = new TextField();
        grid.add(doubleValueTextField, 1, 2);
        doubleErrorLabel = new Label("X");
        doubleErrorLabel.setTextFill(Color.RED);
        doubleErrorLabel.setVisible(false);
        grid.add(doubleErrorLabel, 2, 2);

        // STRING
        Label stringValueLabel = new Label("String value:");
        grid.add(stringValueLabel, 0, 3);
        stringValueTextField = new TextField();
        grid.add(stringValueTextField, 1, 3);

        container.setCenter(grid);
    }

    public TextField getIntValueTextField() {
        return intValueTextField;
    }

    public Label getIntErrorLabel() {
        return intErrorLabel;
    }

    public TextField getDoubleValueTextField() {
        return doubleValueTextField;
    }

    public Label getDoubleErrorLabel() {
        return doubleErrorLabel;
    }

    public TextField getStringValueTextField() {
        return stringValueTextField;
    }

    @Override
    public Node getDisplayable() {
        return this.container;
    }
}
