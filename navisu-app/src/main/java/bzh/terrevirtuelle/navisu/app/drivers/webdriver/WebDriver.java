/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver;

import bzh.terrevirtuelle.navisu.api.progress.ProgressHandle;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public interface WebDriver {

    boolean canOpen(String file);

    void open(ProgressHandle progressHandle, String url);

    String getName();

    void load();
}
