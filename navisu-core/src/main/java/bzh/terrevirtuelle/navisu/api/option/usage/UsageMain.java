package bzh.terrevirtuelle.navisu.api.option.usage;

import bzh.terrevirtuelle.navisu.api.option.OptionsWindowCtrl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 15/02/2014 16:42
 */
public class UsageMain extends Application {

    public static final Logger LOG = Logger.getLogger(UsageMain.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root);

        final OptionsWindowCtrl optionWindow = new OptionsWindowCtrl();
        root.setCenter(optionWindow.getDisplayable());

        final TestOptionsPanelCtrl testOptionsPanelCtrl = optionWindow.newOptionsPanelCtrl(TestOptionsPanelCtrl.class);
        testOptionsPanelCtrl.setOnModelCHangedListener((model) -> {
            LOG.info("Model changed: " + model);
        });

        optionWindow.setOnCloseListener(() -> stage.close());
        optionWindow.setVisible(true);

        stage.setOnCloseRequest(e -> optionWindow.setVisible(false));

        stage.setWidth(700);
        stage.setHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(UsageMain.class, args);
    }
}
