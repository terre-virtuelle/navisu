package bzh.terrevirtuelle.navisu.api.progress;

import bzh.terrevirtuelle.navisu.api.checktree.Action;
import bzh.terrevirtuelle.navisu.api.checktree.CheckTree;
import bzh.terrevirtuelle.navisu.api.checktree.model.CheckTreeItemModel;
import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
import bzh.terrevirtuelle.navisu.api.common.Callback;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobView;
import bzh.terrevirtuelle.navisu.api.progress.impl.view.JobViewCtrl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * NaVisu
 *
 * @author tibus
 * @date 10/12/2013 21:23
 */
public class UsageMain extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.setScene(scene);

        Job.manager.newJob((pHandle) -> {

        });

        JobViewCtrl jobViewCtrl = new JobViewCtrl("Charts loading", 700);
        root.setCenter(jobViewCtrl.getDisplayable());

        jobViewCtrl.progress("Un message de progression", 350);

        stage.setTitle("Usage of progress API");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class);
    }
}
