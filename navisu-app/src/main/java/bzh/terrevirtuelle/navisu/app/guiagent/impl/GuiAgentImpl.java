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
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.LayerTreeServices;
import bzh.terrevirtuelle.navisu.app.guiagent.layertree.impl.LayerTreeImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.MenuBarServices;
import bzh.terrevirtuelle.navisu.app.guiagent.menu.impl.MenuBarImpl;
import bzh.terrevirtuelle.navisu.app.guiagent.options.OptionsManagerServices;
import bzh.terrevirtuelle.navisu.app.guiagent.options.impl.OptionsManagerImpl;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.capcaval.c3.component.annotation.SubComponent;
import org.capcaval.c3.component.annotation.UsedService;
import org.capcaval.c3.componentmanager.ComponentManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NaVisu
 *
 * @author tibus
 * @date 02/11/2013 11:54
 */
public class GuiAgentImpl implements GuiAgent, GuiAgentServices {

    private static final Logger LOGGER = Logger.getLogger(GuiAgentImpl.class.getName());

    @SubComponent MenuBarImpl menu;
    @UsedService MenuBarServices menuServices;

    @SubComponent LayerTreeImpl layerTree;
    @UsedService LayerTreeServices layerTreeServices;

    @SubComponent GeoViewImpl geoView;
    @UsedService  GeoViewServices geoViewServices;

    @SubComponent I18nImpl i18n;
    @UsedService I18nServices i18nServices;

    @SubComponent OptionsManagerImpl optionsManager;
    @UsedService OptionsManagerServices optionsManagerServices;

    protected int width;
    protected int height;
    
    protected Stage stage;

    @Override
    public void showGui(Stage stage, int width, int height) {

        i18nServices.setLang(I18nLangEnum.FRENCH);

        this.width = width;
        this.height = height;
     
        this.stage = stage;
        
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, this.width, this.height, Color.ALICEBLUE);

        pane.setCenter(this.geoViewServices.getDisplayService().getDisplayable());
        pane.setTop(this.menuServices.getDisplayService().getDisplayable());

        menuServices.createMenu(i18nServices.tr("menubar.menu.file"));
        MenuItem fileMenuItem = new MenuItem(i18nServices.tr("menubar.menu.menuitem.exit"));
        fileMenuItem.setOnAction(e -> {

            ComponentManager.componentManager.stopApplication();
            System.exit(0);
        });
        menuServices.addMenuItem(i18nServices.tr("menubar.menu.file"), fileMenuItem);

        pane.setLeft(this.layerTreeServices.getDisplayService().getDisplayable());

        //pane.setBottom(new ControlsWidgetView().getDisplay().getDisplayable());

        menuServices.createMenu("Options");
        MenuItem preferenceMenuItem = new MenuItem("Preferences");
        preferenceMenuItem.setOnAction(e -> {
            optionsManagerServices.show();
        });
        menuServices.addMenuItem("Options", preferenceMenuItem);

        stage.setTitle("NaVisu");
        stage.setOnCloseRequest(e -> {
            LOGGER.info("Stop Application");
            ComponentManager.componentManager.stopApplication();
        });
        stage.setScene(scene);
        stage.show();
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
