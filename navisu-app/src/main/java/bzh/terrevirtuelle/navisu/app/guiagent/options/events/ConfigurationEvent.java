/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.app.guiagent.options.events;

import bzh.terrevirtuelle.navisu.app.guiagent.options.domain.Option;
import org.capcaval.c3.component.ComponentEvent;

/**
 *
 * @author serge
 * @date Jun 24, 2017
 */
public interface ConfigurationEvent
        extends ComponentEvent {
    
    /**
     *
     * @param <T>
     * @param option
     */
    public <T extends Option> void notifyConfMessageChanged(T option);

}
