package bzh.terrevirtuelle.navisu.widgets.radialmenu.app;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenu;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuContainer;
import bzh.terrevirtuelle.navisu.widgets.radialmenu.menu.RadialMenuItem;

public class RadialItemMain extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		Group g = new Group();
		Scene scene = new Scene(g, 800, 600);
		
		RadialMenuContainer root1 = new RadialMenuContainer();
		RadialMenuContainer subRoot11 = new RadialMenuContainer();
		RadialMenuContainer subRoot12 = new RadialMenuContainer();
		RadialMenuContainer subRoot13 = new RadialMenuContainer();
		RadialMenuItem subSubRoot1item1 = new RadialMenuItem();
		RadialMenuItem subSubRoot1item12 = new RadialMenuItem();
		RadialMenuItem subSubRoot1item13 = new RadialMenuItem();
		RadialMenuItem subSubRoot1item14 = new RadialMenuItem();
		subRoot11.addItem(subSubRoot1item1);
		subRoot11.addItem(subSubRoot1item12);
		subRoot11.addItem(subSubRoot1item13);
		subRoot11.addItem(subSubRoot1item14);
		root1.addItem(subRoot11);
		root1.addItem(subRoot12);
		root1.addItem(subRoot13);
		
		RadialMenuContainer root2 = new RadialMenuContainer();
		RadialMenuContainer subRoot2 = new RadialMenuContainer();
		RadialMenuItem subSubRoot1item2 = new RadialMenuItem();
		subRoot2.addItem(subSubRoot1item2);
		root2.addItem(subRoot2);
		
		RadialMenuContainer root3 = new RadialMenuContainer();
		RadialMenuContainer root4 = new RadialMenuContainer();
		
		RadialMenu rMenu = new RadialMenu(60, 90, 360, 5);
		rMenu.addRootItem(root1);
		rMenu.addRootItem(root2);
		rMenu.addRootItem(root3);
		rMenu.addRootItem(root4);
		
		g.getChildren().add(rMenu);
		rMenu.setLayoutX(400);
		rMenu.setLayoutY(300);
		
		scene.setOnMouseClicked(e -> {			
			rMenu.setLayoutX(e.getX());
			rMenu.setLayoutY(e.getY());
		});
		
		stage.setScene(scene);
		stage.show();
	}
}
