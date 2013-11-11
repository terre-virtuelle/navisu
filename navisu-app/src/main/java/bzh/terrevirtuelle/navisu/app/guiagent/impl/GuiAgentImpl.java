/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bzh.terrevirtuelle.navisu.app.guiagent.impl;

import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgent;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.GeoViewServices;
import bzh.terrevirtuelle.navisu.app.guiagent.geoview.impl.GeoViewImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nLangEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.I18nServices;
import bzh.terrevirtuelle.navisu.app.guiagent.i18n.impl.I18nImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.icons.IconsManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.icons.impl.IconsManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.LayerTreeImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.DefaultMenuEnum;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.impl.MenuManagerImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.OptionsManagerImpl;
import bzh.terrevirtuelle.navisu.core.view.display.Display;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.capcaval.c3.component.annotation.SubComponent;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class GuiAgentImpl implements GuiAgent, GuiAgentServices {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentImpl.class.getName());

    @SubComponent I18nImpl i18n;
    @UsedService I18nServices i18nServices;

    @SubComponent OptionsManagerImpl optionsManager;
    @UsedService OptionsManagerServices optionsManagerServices;

    @SubComponent MenuManagerImpl menu;
    @UsedService MenuManagerServices menuServices;

    @SubComponent LayerTreeImpl layerTree;
    @UsedService LayerTreeServices layerTreeServices;

    @SubComponent GeoViewImpl geoView;
    @UsedService  GeoViewServices geoViewServices;

    @SubComponent IconsManagerImpl iconsManager;
    @UsedService IconsManagerServices iconsServices;

    protected int width;
    protected int height;
    
    protected Stage stage;

    @Override
    public void showGui(Stage stage, int width, int height) {

        this.width = width;
        this.height = height;
     
        this.stage = stage;

        BorderPane borderPane = new BorderPane();

        Scene scene = new Scene(borderPane, this.width, this.height, Color.ALICEBLUE);

        StackPane root = new StackPane();
        borderPane.setCenter(root);

        root.getChildren().add(this.geoViewServices.getDisplayService().getDisplayable());

        ToolBar toolBar = new ToolBar();
        StackPane.setAlignment(toolBar.getDisplayable(), Pos.TOP_LEFT);
        root.getChildren().add(toolBar.getDisplayable());

        Display<Node> layerTreeDisplay = this.layerTreeServices.getDisplayService();
        //StackPane.setAlignment(layerTreeDisplay.getDisplayable(), Pos.CENTER_LEFT);
        layerTreeDisplay.setMaxWidth(250);
        //layerTreeDisplay.getDisplayable().setTranslateY(toolBar.getMaxHeight());
        //root.getChildren().add(layerTreeDisplay.getDisplayable());
        borderPane.setLeft(layerTreeDisplay.getDisplayable());

        toolBar.addAction(this.iconsServices.getIcon("app.exit"), (e) -> {

            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });

        toolBar.addAction(this.iconsServices.getIcon("app.options"), (e) -> {

            optionsManagerServices.show();
        });

        borderPane.setTop(this.initializeMenuBar(this.menuServices));

        //pane.setBottom(new ControlsWidgetView().getDisplay().getDisplayable());

        stage.setTitle("NaVisu");
        stage.setOnCloseRequest(e -> {
            LOGGER.info("Stop Application");
            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });
        stage.setScene(scene);
        stage.show();
    }

    protected Node initializeMenuBar(final MenuManagerServices menuServices) {

        MenuItem fileMenuItem = new MenuItem(i18nServices.tr("menu.file.exit"));
        fileMenuItem.setOnAction(e -> {

            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });
        menuServices.addMenuItem(DefaultMenuEnum.FILE, fileMenuItem);

        MenuItem preferenceMenuItem = new MenuItem(i18nServices.tr("menu.edit.preferences"));
        preferenceMenuItem.setOnAction(e -> {
            optionsManagerServices.show();
        });
        menuServices.addMenuItem(DefaultMenuEnum.EDIT, preferenceMenuItem);

        return menuServices.getDisplayService().getDisplayable();
    }

    @Override
    public boolean isFullScreen() {
        return this.stage.isFullScreen();
    }

    @Override
    public void setFullScreen(boolean fullScreen) {
        this.stage.setFullScreen(true);
    }
}
