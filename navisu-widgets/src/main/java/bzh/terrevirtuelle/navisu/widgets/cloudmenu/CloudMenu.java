/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.widgets.cloudmenu;

import bzh.terrevirtuelle.navisu.widgets.Widget;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @author Serge Morvan
 * @date 18 nov. 2014 NaVisu project
 */
public class CloudMenu
        extends Widget {
    
    public CloudMenu() {
        createScene();
       // setOpacity(0.0);
    }
    
    protected final void createScene() {
        final WebView browser = new WebView();
       // browser.setOpacity(0.1);
        final WebEngine webEngine = browser.getEngine();
        browser.setMouseTransparent(false);
        webEngine.load(CloudMenu.class.getResource("index.html").toExternalForm());
        getChildren().add(browser);
    }
}
