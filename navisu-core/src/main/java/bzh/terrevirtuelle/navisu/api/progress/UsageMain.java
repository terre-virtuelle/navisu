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

        root.setCenter(Job.manager.getDisplay().getDisplayable());

        Job.manager.newJob("Charts Loading", (pHandle) -> {

            try {
                pHandle.progress("Start progressing");
                Thread.sleep(1000);

                pHandle.progress("Comment allez vous ?");
                Thread.sleep(2000);

                pHandle.progress("Ca va bien, et vous ?");
                Thread.sleep(2000);

                pHandle.progress("Bien merci");

            } catch (InterruptedException e) {}
        });

        Job.manager.newJob("Grib Loading", 4, (pHandle) -> {

            try {
                pHandle.progress("Start progressing", 1);
                Thread.sleep(1000);

                pHandle.progress("Comment allez vous ?", 2);
                Thread.sleep(2000);

                pHandle.progress("Ca va bien, et vous ?", 3);
                Thread.sleep(2000);

                pHandle.progress("Bien merci", 4);

            } catch (InterruptedException e) {}
        });

        stage.setTitle("Usage of progress API");
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class);
    }
}
