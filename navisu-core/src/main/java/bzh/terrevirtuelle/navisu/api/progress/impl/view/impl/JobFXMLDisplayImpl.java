package bzh.terrevirtuelle.navisu.api.progress.impl.view.impl;

import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobDisplay;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/12/2013 20:40
 */
public class JobFXMLDisplayImpl implements JobDisplay {

    private static final Logger LOGGER = Logger.getLogger(JobFXMLDisplayImpl.class.getName());

    protected AnchorPane root;
    protected FXMLController ctrl;

    public JobFXMLDisplayImpl() {

        final FXMLLoader loader = new FXMLLoader();
        final InputStream in = this.getClass().getResourceAsStream("Job.fxml");

        try {
            loader.setRoot(this.root);
            this.ctrl = new FXMLController();
            loader.setController(this.ctrl);
            this.root = loader.load(in);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
    }

    protected class FXMLController {
        @FXML Text messageText;
        @FXML Text titleText;
        @FXML ProgressIndicator progressBar;
        @FXML ImageView closeIcon;
    }

    @Override
    public ProgressIndicator progressBar() {
        return this.ctrl.progressBar;
    }

    @Override
    public Text titleText() {
        return this.ctrl.titleText;
    }

    @Override
    public Text messageText() {
        return this.ctrl.messageText;
    }

    @Override
    public ImageView closeButton() {
        return this.ctrl.closeIcon;
    }

    @Override
    public void setVisible(boolean visible) {
        this.root.setVisible(visible);
    }

    @Override
    public boolean isVisible() {
        return this.root.isVisible();
    }

    @Override
    public Region getDisplayable() {
        return this.root;
    }
}
