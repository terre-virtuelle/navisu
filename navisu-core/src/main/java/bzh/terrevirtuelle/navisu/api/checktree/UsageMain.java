package bzh.terrevirtuelle.navisu.api.checktree;

import bzh.terrevirtuelle.navisu.api.common.Callback;
import bzh.terrevirtuelle.navisu.api.checktree.model.CheckTreeItemModel;
import bzh.terrevirtuelle.navisu.api.checktree.model.TreeItemModel;
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

        CheckTree<TreeItemModel> tree = new CheckTree<>();

        Action[] actionList = new Action[] {
            Action.create("Action 1", (source) -> System.out.println("Do Ac tion 1 source@" + source)),
            Action.create("Action 2", (source) -> System.out.println("Do Action 2 source@" + source)),
            Action.create("Action 3", (source) -> System.out.println("Do Action 3 source@" + source))
        };
        
        // create the root item
        Image img = new Image(getClass().getResourceAsStream("layer_icon.png"));
        TreeItem<TreeItemModel> rootItem = new TreeItem<>(TreeItemModel.create("Layers", img));
        rootItem.setExpanded(true);
        tree.root().getChildren().add(rootItem);
        
        // create children
        Callback<CheckTreeItemModel> cb = (item) -> System.out.println("Do OnSelect[" + item.selected() + "] source@" + item);
        
        for(int i=0; i<10; i++) {
            TreeItemModel item = CheckTreeItemModel.create("Layer " + i, img, false, actionList);
            CheckTreeItemModel.cast(item).setOnSelect(cb);
            rootItem.getChildren().add(new TreeItem<>(item));
        }

        root.setCenter(tree);
        stage.setTitle("Usage of check tree");
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(UsageMain.class);
    }
}
