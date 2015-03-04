/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver.impl;

import bzh.terrevirtuelle.navisu.app.drivers.driver.Driver;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManager;
import bzh.terrevirtuelle.navisu.app.drivers.webdriver.WebDriverManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import static bzh.terrevirtuelle.navisu.app.guiagent.utilities.Translator.tr;
import bzh.terrevirtuelle.navisu.core.util.Checker;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public class WebDriverManagerImpl
        implements WebDriverManager, WebDriverManagerServices, ComponentState {

    @UsedService
    MenuManagerServices menuBarServices;
    @UsedService
    GuiAgentServices guiAgentServices;
    protected List<Driver> availableDriverList = new ArrayList<>();
    StackPane root;

    @Override
    public void init() {
        root = guiAgentServices.getRoot();
        URLField url = new URLField(root);
        MenuItem menuItem = new MenuItem(tr("menu.url.load"));
        menuBarServices.addMenuItem(DefaultMenuEnum.URL, menuItem);
        menuItem.setOnAction((e) -> {
            root.getChildren().add(url);
        });
    }

    @Override
    public void registerNewDriver(Driver driver) {
        Checker.notNull(driver, "Driver must not be null.");

        // Hold the driver
        this.availableDriverList.add(driver);
    }

    @Override
    public void componentInitiated() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}

class URLField extends Group {

    public URLField(StackPane root) {
        setTranslateX(-300);
        BorderPane pane = new BorderPane();
        pane.setPrefSize(200, 100);

        TextField textField = new TextField();
        pane.setCenter(textField);
        textField.setPrefWidth(500);
        textField.setStyle("-fx-text-fill:black;-fx-prompt-text-fill:gray;-fx-background-color: skyblue;");
        textField.setOnAction((event) -> {
            System.out.println("Work in progress :-)");
            root.getChildren().remove(this);
        });
        getChildren().add(pane);
    }
}
