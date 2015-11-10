/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.navigation.routeeditor.impl;

import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.charts.vector.s57.charts.S57ChartServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoViewer;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.RoutePhotoViewerServices;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RoutePhotoEditorController;
import bzh.terrevirtuelle.navisu.navigation.routeeditor.impl.controller.RoutePhotoViewerController;
import bzh.terrevirtuelle.navisu.photos.exif.ExifComponentServices;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 26 août 2015
 * @author Serge Morvan
 */
public class RoutePhotoViewerImpl
        implements RoutePhotoViewer, RoutePhotoViewerServices, InstrumentDriver, ComponentState {

    @UsedService
    GuiAgentServices guiAgentServices;
    private final String KEY_NAME = "RoutePhotoViewer";
    private RoutePhotoViewerController routePhotoViewerController;

    @Override
    public void componentInitiated() {
    }

    private void init() {
        routePhotoViewerController = new RoutePhotoViewerController(this, KeyCode.P, KeyCombination.CONTROL_DOWN);
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, routePhotoViewerController);
        guiAgentServices.getRoot().getChildren().add(routePhotoViewerController);
        routePhotoViewerController.setVisible(true);
    }

    @Override
    public void on(String... files) {
        if (routePhotoViewerController == null) {
            init();
        }
    }

    @Override
    public boolean canOpen(String category) {
        return category.equals(KEY_NAME);
    }

    @Override
    public InstrumentDriver getDriver() {
        return this;
    }

    @Override
    public void off() {

        guiAgentServices.getScene().removeEventFilter(KeyEvent.KEY_RELEASED, routePhotoViewerController);
        guiAgentServices.getRoot().getChildren().remove(routePhotoViewerController);
        routePhotoViewerController.setVisible(false);
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

    public GuiAgentServices getGuiAgentServices() {
        return guiAgentServices;
    }

    @Override
    public void load(Image image) {
        if (routePhotoViewerController == null) {
            init();
        }
        routePhotoViewerController.load(image);
    }
}
