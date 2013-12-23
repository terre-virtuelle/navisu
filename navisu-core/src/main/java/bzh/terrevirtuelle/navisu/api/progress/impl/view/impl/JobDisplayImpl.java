package bzh.terrevirtuelle.navisu.api.progress.impl.view.impl;

import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobDisplay;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 15:03
 */
public class JobDisplayImpl implements JobDisplay {

    public final ProgressIndicator progressBar;
    public final Text titleText;
    public final Text messageText;
    public final ImageView closeIcon;

    protected final GridPane container;

    public JobDisplayImpl() {

        // Initialize the progress bar
        this.progressBar = new ProgressBar();

        // Initialize the titleText text
        this.titleText = new Text("no-titleText");
        this.titleText.setFill(Color.BLACK);
        this.titleText.setFont(Font.font("Arial", FontWeight.BOLD, 12d));

        // Initialize the description text
        this.messageText = new Text("description-text");
        this.messageText.setFill(Color.DARKGRAY);
        this.messageText.setFont(Font.font("Arial", FontPosture.ITALIC, 10d));

        // Initialize the close icon
        this.closeIcon = new ImageView(new Image(getClass().getResourceAsStream("close.png")));

        // Initialize the jobsContainer
        this.container = new GridPane();
        this.container.setAlignment(Pos.CENTER);

        this.container.setHgap(10);
        this.container.setVgap(10);
        this.container.setPadding(new Insets(5, 10, 5, 10));

        this.container.add(this.titleText, 0, 0); // node, col, line
        this.container.add(this.messageText, 0, 1);

        GridPane.setValignment(this.closeIcon, VPos.TOP);
        GridPane.setHalignment(this.closeIcon, HPos.RIGHT);
        this.container.add(this.closeIcon, 1, 0);

        this.container.add(this.progressBar, 1, 1);

        // Create the background
        this.container.setStyle("-fx-border-color: darkgray;");
    }

    @Override
    public void setVisible(boolean visible) {
        this.container.setVisible(true);
    }

    @Override
    public boolean isVisible() {
        return this.container.isVisible();
    }

    @Override
    public Region getDisplayable() {
        return this.container;
    }

    @Override
    public ProgressIndicator progressBar() {
        return this.progressBar;
    }

    @Override
    public Text titleText() {
        return this.titleText;
    }

    @Override
    public Text messageText() {
        return this.messageText;
    }

    @Override
    public ImageView closeButton() {
        return this.closeIcon;
    }
}









