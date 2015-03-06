/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.drivers.webdriver;

import org.capcaval.c3.component.ComponentService;

/**
 * @date 4 mars 2015
 * @author Serge Morvan
 */
public interface WebDriverManagerServices
        extends ComponentService {

    void init();

    void registerNewDriver(WebDriver driver);
}
