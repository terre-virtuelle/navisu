package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsWindow;
import bzh.terrevirtuelle.navisu.api.option.OptionsWindowCtrl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 16:42
 */
public class UsageMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        OptionsWindowCtrl optionWindow = new OptionsWindowCtrl();
        root.setCenter(optionWindow.getView().getDisplayable());

        stage.setWidth(700);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class, args);
    }
}
