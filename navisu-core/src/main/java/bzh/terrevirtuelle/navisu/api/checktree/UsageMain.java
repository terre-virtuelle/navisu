package bzh.terrevirtuelle.navisu.api.checktree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
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

        CheckTree tree = new CheckTree();

        TreeItem<CheckTreeItem.CheckTreeItemImpl> rootItem = new TreeItem<>();
        tree.setRoot(rootItem);
        tree.setShowRoot(false);

        CheckTreeItem.CheckTreeItemImpl item = new CheckTreeItem.CheckTreeItemImpl("hello", true);
        item.getActions().add(new Action() {
            @Override
            public String getName() {
                return "Action01";
            }

            @Override
            public void setName(String name) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public Callback getCallback() {
                return new Callback() {
                    @Override
                    public void on() {
                        System.out.println("Hello !!");
                    }
                };
            }

            @Override
            public void setCallback(Callback cb) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        rootItem.getChildren().add(new TreeItem<>(item));

        root.setCenter(tree);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class);
    }
}
