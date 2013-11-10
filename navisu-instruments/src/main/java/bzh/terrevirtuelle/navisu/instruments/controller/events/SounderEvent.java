/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.instruments.controller.events;

import java.util.EventObject;
import java.util.Map;

/**
 *
 * @author Serge Morvan
 */
public class SounderEvent
        extends EventObject {

    private Map<String, String> data;

    public SounderEvent(Object o, Map<String, String> data) {
        super(o);
        this.data = data;
    }

    public Map<String, String> getData() {
        return data;
    }
    
}
