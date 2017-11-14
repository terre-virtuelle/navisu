 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.urllist;

import bzh.terrevirtuelle.navisu.widgets.impl.Widget2DController;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * @date 6 mars 2015
 * @author Serge Morvan
 */
public class UrlListController
        extends Widget2DController {

    @FXML
    private static final String CSS_STYLE_PATH = Paths.get(System.getProperty("user.dir") + "/css/").toUri().toString();
    protected String viewgroupstyle = "urllistpanel.css";
    public Group urlListPanel;
    @FXML
    public ImageView quit;
    @FXML
    GridPane gridPane;
    @FXML
    public ListView list;
    @FXML
    ScrollPane scrollPane;
    @FXML
    Text title;

    public UrlListController() {
        setMouseTransparent(false);
        load();
    }

    public UrlListController(KeyCode keyCode, KeyCombination.Modifier keyCombination) {

        super(keyCode, keyCombination);
        setMouseTransparent(false);
        load();
    }

    private void load() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UrlListPanel.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        String uri = CSS_STYLE_PATH + viewgroupstyle;
        urlListPanel.getStylesheets().add(uri);

        //gridPane.setStyle("-fx-background-color: #00524e99");
        //scrollPane.setStyle("-fx-background-color: #00524e99");
        quit.setOnMouseClicked((MouseEvent event) -> {
            setVisible(false);
        });
    }
/*
    public ImageView getQuit() {
        return quit;
    }
*/
    public ListView getList() {
        return list;
    }

    public Text getTitle() {
        return title;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setTitle(Text title) {
        this.title = title;
    }

}
