/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.webview.impl;

import bzh.terrevirtuelle.navisu.instruments.webview.impl.controller.WebViewController;
import bzh.terrevirtuelle.navisu.app.drivers.instrumentdriver.InstrumentDriver;
import bzh.terrevirtuelle.navisu.app.guiagent.GuiAgentServices;
import bzh.terrevirtuelle.navisu.instruments.webview.WebView;
import bzh.terrevirtuelle.navisu.instruments.webview.WebViewServices;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import org.capcaval.c3.component.ComponentState;
import org.capcaval.c3.component.annotation.UsedService;

/**
 * NaVisu
 *
 * @date 27 juil. 2015
 * @author Serge Morvan
 */
public class WebViewImpl
        implements WebView, WebViewServices, InstrumentDriver, ComponentState {

    private final String KEY_NAME = "WebView";
    @UsedService
    GuiAgentServices guiAgentServices;
    private WebViewController controller;

    @Override
    public void componentInitiated() {
        controller = new WebViewController(this, KeyCode.W, KeyCombination.CONTROL_DOWN);
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
    public void on(String... files) {
        guiAgentServices.getScene().addEventFilter(KeyEvent.KEY_RELEASED, controller);
        guiAgentServices.getRoot().getChildren().add(controller); //Par defaut le radar n'est pas visible Ctrl-A
        controller.setVisible(true);
    }

    @Override
    public void off() {
    }

    @Override
    public void componentStarted() {
    }

    @Override
    public void componentStopped() {
    }

}
